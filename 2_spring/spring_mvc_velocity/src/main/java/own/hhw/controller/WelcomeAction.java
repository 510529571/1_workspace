package own.hhw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 15-1-5
 * Time: ÏÂÎç3:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class WelcomeAction {
    @RequestMapping(value = "/viewvm", method = RequestMethod.GET)
    public String viewvm(Model model) {
        model.addAttribute("name","huhanwei");
        return "viewvm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("name","huhanwei");
        return "module1/page/login/login";
    }
}
