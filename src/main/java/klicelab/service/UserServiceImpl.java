package klicelab.service;

import klicelab.model.LoginRequest;
import klicelab.model.User;
import klicelab.model.UserLoginResult;
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
    public User getByName(String name) {
        return mapper.getByName(name);
    }

    @Override
    public void register(User user) throws DbException {
        mapper.register(user);
    }

    @Override
    public UserLoginResult login(LoginRequest loginRequest) {
        User user = mapper.getByName(loginRequest.getName());
        if (user == null)
            return UserLoginResult.UserNotFound;
        if (!user.getPassword().equals(loginRequest.getPassword()))
            return UserLoginResult.InvalidPassword;
        return UserLoginResult.Success;
    }
}
