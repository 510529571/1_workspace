package own.hhw.lang.constructor;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-17
 * Time: обнГ4:24
 * To change this template use File | Settings | File Templates.
 */
public class XiaoWang implements Student {
    private String name;
    private String grade;

    @Override
    public String getName() {
        return name;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
