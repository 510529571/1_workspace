package own.hhw.lang.constructor;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-17
 * Time: ÏÂÎç3:36
 * To change this template use File | Settings | File Templates.
 */
public class Son extends Father implements Student {
    public Son(Human human) {
        super(human);
    }

    public static void main(String[] args) {
        XiaoWang xiaoWang = new XiaoWang();
        Son son = new Son(xiaoWang);

        xiaoWang.setName("xiaowan");
        xiaoWang.setGrade("1");

        System.out.println(son.getName());
        System.out.println(son.getGrade());

    }

    private Student _getHuman() {
        return (Student) super.getHuman();
    }

    @Override
    public String getGrade() {
        return _getHuman().getGrade();
    }

    @Override
    public void setGrade(String grade) {
        _getHuman().setGrade(grade);
    }
}
