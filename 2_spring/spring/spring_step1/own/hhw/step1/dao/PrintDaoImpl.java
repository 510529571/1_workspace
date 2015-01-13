package own.hhw.step1.dao;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-6-5
 * Time: обнГ3:18
 * To change this template use File | Settings | File Templates.
 */
public class PrintDaoImpl implements PrintDao {
    private String name;

    @Override
    public String print(String message) {
        return "["+name+"]print:"+message;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setName(String name) {
        this.name = name;
    }
}
