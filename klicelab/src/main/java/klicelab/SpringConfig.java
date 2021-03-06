package klicelab;

import klicelab.web.filters.SessionFilter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hasee on 2017/4/25.
 */
@org.springframework.context.annotation.Configuration
public class SpringConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
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

//    @Bean
//    public DataSource dataSource(org.springframework.core.env.Environment environment) throws PropertyVetoException {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        dataSource.setJdbcUrl("jdbc:MySQL://localhost:3306/klicelab?serverTimezone=UTC");
//        dataSource.setUser("root");
//        dataSource.setPassword("111111");
//        dataSource.setDriverClass("com.mysql.jdbc.Driver");
//        dataSource.setMinPoolSize(4);
//        dataSource.setMaxPoolSize(50);
//        return dataSource;
//    }

//    @Bean
//    public SessionFilter sessionFilter(SessionService sessionService){
//        return new SessionFilter(sessionService);
//    }

    @Bean
    public FilterRegistrationBean sessionFilterRegistration(SessionFilter sessionFilter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(sessionFilter);
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/user/index");
        return registrationBean;
    }

    @Bean("sendRegisterEmailJobExecutor")
    public TaskExecutor sendRegisterEmailJobExecutor() {
        return createTaskExecutor("email");
    }

    @Bean("experimentJobExecutor")
    public TaskExecutor experimentJobExecutor() {
        return createTaskExecutor("experiment");
    }

    TaskExecutor createTaskExecutor(String threadGroupName) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setKeepAliveSeconds(1);
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(10);
        executor.setThreadGroupName(threadGroupName);
        return executor;
    }

    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(5);
    }
}
