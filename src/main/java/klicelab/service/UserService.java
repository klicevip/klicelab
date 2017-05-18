package klicelab.service;

import klicelab.model.User;

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
     * 注册用户
     * @param user
     */
    void register(User user);
}
