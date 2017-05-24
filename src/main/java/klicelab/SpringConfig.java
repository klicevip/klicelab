package klicelab;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import klicelab.service.SessionService;
import klicelab.web.filters.SessionFilter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hasee on 2017/4/25.
 */
@org.springframework.context.annotation.Configuration
public class SpringConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;

//        TransactionFactory transactionFactory = new JdbcTransactionFactory();
//        Environment environment = new Environment("development", transactionFactory, dataSource);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMappers("klicelab");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
//        return sqlSessionFactory;
    }

    @Bean
    public DataSource dataSource(org.springframework.core.env.Environment environment) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl("jdbc:MySQL://localhost:3306/klicelab?serverTimezone=UTC");
        dataSource.setUser("root");
        dataSource.setPassword("111111");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setMinPoolSize(4);
        dataSource.setMaxPoolSize(50);
        return dataSource;
    }

//    @Bean
//    public SessionFilter sessionFilter(SessionService sessionService){
//        return new SessionFilter(sessionService);
//    }

    @Bean
    public FilterRegistrationBean sessionFilterRegistration(SessionFilter sessionFilter){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(sessionFilter);
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/user/index");
        return registrationBean;
    }
}
