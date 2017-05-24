package klicelab.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by hasee on 2017/4/26.
 */
public class User {
    Integer id;

    String email;

    @NotBlank(message = "用户名不能为空")//字符串不能使用NotNull，空字符串可以通过验证
    String name;

    @NotBlank(message = "密码不能为空")
    @Size(min = 4, message = "密码至少要4位")
    String password;

    Date createTime;

    public User(){
        setCreateTime(new Date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
