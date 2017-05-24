package klicelab.persistence;

import klicelab.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by hasee on 2017/4/26.
 */
@Repository
public class UserMapperImpl implements UserMapper {
    @Autowired
    SqlSessionFactory sessionFactory;

    @Override
    public User getUser(int id) {
        SqlSession session = sessionFactory.openSession();
        try {
            return session.getMapper(UserMapper.class).getUser(id);
        }finally {
            session.close();
        }
    }

    @Override
    public void register(User user) throws DbException {
        SqlSession session = sessionFactory.openSession();//make session autocommit
        try{
            session.getMapper(UserMapper.class).register(user);
            session.commit();
        }
        catch (Exception ex){
            throw new DbException();
        }
        finally {
            session.close();
        }
    }

    @Override
    public User getByName(String name) {
        SqlSession session = sessionFactory.openSession();
        try{
            return session.getMapper(UserMapper.class).getByName(name);
        }finally {
            session.close();
        }
    }
}
