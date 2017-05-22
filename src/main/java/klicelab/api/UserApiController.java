package klicelab.api;

import klicelab.model.User;
import klicelab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by klice on 2017/5/22.
 * 用户接口
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    UserService userService;

    @RequestMapping("/index/{id}")
    public User index(@PathVariable int id) {
        return userService.getById(id);
    }
}
