package web;

import auto.Soldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import web.bean.User;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by mazhibin on 16/7/27
 */
@Controller
public class HomeController {

//    @Autowired
//    private Soldier soldier;

    @RequestMapping({"/","/home"})
    public String home(Model model){

//        model.addAttribute("name",soldier.attack());
        return "home";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestBody @Valid User user){
        return "hello " + user.getName();
    }

    @ExceptionHandler
    public String exceptionHandler(Exception exception){
        System.out.println(exception.getLocalizedMessage());
        return "fuck";
    }
}
