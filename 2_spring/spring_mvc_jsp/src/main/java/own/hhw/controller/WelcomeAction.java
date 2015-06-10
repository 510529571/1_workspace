package own.hhw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 15-1-5
 * Time: обнГ3:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class WelcomeAction {
    @RequestMapping(value = "/viewjsp", method = RequestMethod.GET)
    public String viewjsp(Model model) {
        model.addAttribute("name","huhanwei");
        return "view/viewjsp.jsp";
    }
}
