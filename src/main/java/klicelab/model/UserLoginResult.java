package klicelab.model;

/**
 * Created by klice on 2017/5/23.
 */
public enum UserLoginResult {
    Success("成功"), UserNotFound("用户错误"), InvalidPassword("密码错误");

    String message;

    UserLoginResult(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
