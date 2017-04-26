package klicelab.web;

import klicelab.model.User;
import klicelab.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hasee on 2017/4/26.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String Register() {
        return "Register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String Register(User user) {
        userMapper.register(user);
        return "redirect:Index";
    }
}
