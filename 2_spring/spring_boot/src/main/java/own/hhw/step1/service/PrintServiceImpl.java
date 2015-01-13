package own.hhw.step1.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import own.hhw.step1.dao.PrintDao;
import own.hhw.step1.dao.PrintDaoImpl;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-5
 * Time: œ¬ŒÁ3:19
 * To change this template use File | Settings | File Templates.
 */
@Scope()
@Service
public class PrintServiceImpl implements PrintService{
    @Resource
    public PrintDao printDaoImpl;

    public String print() {

        return printDaoImpl.print("Œ“ «Àß∏Á£°");
    }


    public void setPrintDaoImpl(PrintDao printDaoImpl) {
        this.printDaoImpl = printDaoImpl;
    }
}
