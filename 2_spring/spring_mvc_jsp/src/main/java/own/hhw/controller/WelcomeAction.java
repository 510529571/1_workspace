package own.hhw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 15-1-5
 * Time: ÏÂÎç3:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class WelcomeAction {
    @RequestMapping(value = "/viewjsp", method = RequestMethod.GET)
    public String viewjsp(Model model) {
        model.addAttribute("name","huhanwei");
        return "view/viewjsp.jsp";
    }

    @RequestMapping(value = "/viewjsp", method = RequestMethod.GET)
    public String web1(HttpServletRequest request, HttpSession session,Model model) {
        model.addAttribute("name","huhanwei");
        return "view/viewjsp.jsp";
    }

    @RequestMapping(value = "/viewjsp", method = RequestMethod.GET)
    public String web2(HttpServletRequest request, HttpSession session,Model model) {
        model.addAttribute("name","huhanwei");
        return "view/viewjsp.jsp";
    }
}
