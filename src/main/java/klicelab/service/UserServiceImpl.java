package klicelab.service;

import klicelab.model.User;
import klicelab.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by klice on 2017/5/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Override
    public boolean userNameExist(String userName) {
        return  mapper.getByName(userName) != null;
    }

    @Override
    public User getById(Integer id) {
        return mapper.getUser(id);
    }

    @Override
    public void register(User user) {
        mapper.register(user);
    }
}
