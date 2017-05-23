package klicelab.web;

import klicelab.model.LoginRequest;
import klicelab.model.Session;
import klicelab.model.User;
import klicelab.model.UserLoginResult;
import klicelab.persistence.DbException;
import klicelab.service.SessionService;
import klicelab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by hasee on 2017/4/26.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;
    SessionService sessionService;

    public UserController(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(LoginRequest loginRequest, Model model, HttpServletRequest httpServletRequest) {
        UserLoginResult userLoginResult = userService.login(loginRequest);
        if (userLoginResult != UserLoginResult.Success) {
            model.addAttribute("error", userLoginResult.toString());
            return "login";
        }

        User user = userService.getByName(loginRequest.getName());
        String sessionId = httpServletRequest.getSession().getId();
        newSession(sessionId,user);

        String backUrl = loginRequest.getBackUrl();
        if (backUrl == null || backUrl.isEmpty())
            return "redirect:index";
        else
            return "redirect:" + backUrl;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Validated User user, Errors errors, HttpServletRequest httpServletRequest) throws DbException {
        if (errors.hasErrors()) {
            return "register";
        }
        if (userService.userNameExist(user.getName())) {
            /**
             * http://docs.spring.io/spring/docs/3.0.x/javadoc-api/org/springframework/validation/Errors.html
             * errors.rejectValue(fieldName,errorCode,defaultMessage)
             */
            errors.rejectValue("name", "duplicateName", "用户名已存在");
            return "register";
        }
        userService.register(user);

        String sessionId = httpServletRequest.getSession().getId();
        newSession(sessionId,user);
        return "redirect:index";
    }

    private void newSession(String sessionId, User user){
        Session session = new Session();
        session.setId(sessionId);
        session.setCreatedTime(new Date());
        session.setUserId(user.getId());
        sessionService.save(session);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession(false);
        if (httpSession == null)
            return "redirect:register";
        Session session = sessionService.get(httpSession.getId());
        if (session == null)
            return "redirect:register";

        User user = userService.getById(session.getUserId());
        model.addAttribute(user);
        return "index";
    }
}
