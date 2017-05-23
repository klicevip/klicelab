package klicelab.model;

/**
 * Created by klice on 2017/5/23.
 * 登录请求
 */
public class LoginRequest {

    String name;

    String password;

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    String backUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
