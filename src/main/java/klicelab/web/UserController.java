package klicelab.web;

import klicelab.model.User;
import klicelab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hasee on 2017/4/26.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String Register(Model model) {
        model.addAttribute("user",new User());
        return "Register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String Register(@Validated User user, Errors errors) {
        if(errors.hasErrors()){
            return "Register";
        }
        if(userService.userNameExist(user.getName())){
            /**
             * http://docs.spring.io/spring/docs/3.0.x/javadoc-api/org/springframework/validation/Errors.html
             * errors.rejectValue(fieldName,errorCode,defaultMessage)
             */
            errors.rejectValue("name","duplicateName","用户名已存在");
            return "Register";
        }
        userService.register(user);
        return "redirect:index/"+user.getId();
    }

    @RequestMapping(value = "/index/{id}", method = RequestMethod.GET)
    public String Index(@PathVariable int id, Model model){
        User user = userService.getById(id);
        model.addAttribute(user);
        return "Index";
    }
}
