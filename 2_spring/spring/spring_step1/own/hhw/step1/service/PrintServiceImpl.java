package own.hhw.step1.service;

import own.hhw.step1.dao.PrintDao;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-5
 * Time: œ¬ŒÁ3:19
 * To change this template use File | Settings | File Templates.
 */
public class PrintServiceImpl {
    public PrintDao printDao;

    public void print() {
        System.out.println(printDao.print("Œ“ «Àß∏Á£°"));
    }

    public void setPrintDao(PrintDao printDao) {
        this.printDao = printDao;
    }
}
