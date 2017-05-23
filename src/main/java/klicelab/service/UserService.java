package klicelab.service;

import klicelab.model.LoginRequest;
import klicelab.model.User;
import klicelab.model.UserLoginResult;
import klicelab.persistence.DbException;

/**
 * Created by klice on 2017/5/18.
 * 用户服务
 */
public interface UserService {
    /**
     * 检查用户名是否已存在
     * @param userName
     * 用户名
     * @return
     */
    boolean userNameExist(String userName);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 根据用户名查询
     * @param name 用户名
     * @return 用户
     */
    User getByName(String name);

    /**
     * 注册用户
     * @param user
     */
    void register(User user) throws DbException;

    /**
     * 登录
     * @param loginRequest 登录请求
     * @return 登录结果
     */
    UserLoginResult login(LoginRequest loginRequest);
}
