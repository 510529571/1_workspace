package own.hhw.step1.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import own.hhw.step1.service.PrintService;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 15-1-5
 * Time: ÏÂÎç3:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class PrintAction {
    @Resource
    private PrintService printService;

    private Log log= LogFactory.getLog(PrintAction.class);

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String print(Model model) {
        printService.print();
        return "view/print.jsp";
    }

    public PrintService getPrintService() {
        return printService;
    }

    public void setPrintService(PrintService printService) {
        this.printService = printService;
    }
}
