package web;

import auto.Soldier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
