package klicelab.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by klice on 2017/5/22.
 * 统一处理异常
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handleException(Exception ex, Model model){
        model.addAttribute("exception",ex);
        return "error";
    }
}
