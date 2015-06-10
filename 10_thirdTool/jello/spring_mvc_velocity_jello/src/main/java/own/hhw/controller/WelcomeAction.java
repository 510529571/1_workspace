package own.hhw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 15-1-5
 * Time: ÏÂÎç3:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class WelcomeAction extends BaseAction{
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(Model model) {
        model.addAttribute("name","huhanwei");
        return "module1/page/login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model) {
        model.addAttribute("name","huhanwei");
        return "module1/page/index/index";
    }

    @RequestMapping(value = "/login/checkUser", method = RequestMethod.POST)
    public String checkUser(ModelMap model,HttpServletResponse response) throws IOException {
        model.addAttribute("flag", 0);
        return getJsonView(model,response);
    }

    @RequestMapping(value = "/login/verifyCode", method = RequestMethod.GET)
    public String verifyCode(ModelMap model,HttpServletResponse response) throws IOException {
        model.addAttribute("errno",0);
        return getJsonView(model,response);
    }
}
