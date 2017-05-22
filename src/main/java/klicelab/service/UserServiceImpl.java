package klicelab.service;

import klicelab.model.User;
import klicelab.persistence.DbException;
import klicelab.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by klice on 2017/5/18.
 */
@Service
public class UserServiceImpl implements UserService {

    UserMapper mapper;

    public UserServiceImpl(@Autowired UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean userNameExist(String userName) {
        return mapper.getByName(userName) != null;
    }

    @Override
    public User getById(Integer id) {
        return mapper.getUser(id);
    }

    @Override
    public void register(User user) throws DbException {
        mapper.register(user);
    }
}
