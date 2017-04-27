package klicelab.web;

import klicelab.model.User;
import klicelab.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
    public String Register(@Valid User user, Errors errors) {
        if(errors.hasErrors())
            return "Register";
        userMapper.register(user);
        return "redirect:index/"+user.getId();
    }

    @RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
    public String Index(@PathVariable int id, Model model){
        User user = userMapper.getUser(id);
        model.addAttribute(user);
        return "Index";
    }
}
