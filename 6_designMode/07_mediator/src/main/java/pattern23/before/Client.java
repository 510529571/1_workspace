package pattern23.before;

/**
 * User: hanwei
 * Date: 15-5-22
 * Time: 下午3:35
 */
public class Client {
    public static void main(String[] args) {

        AbstractColleague collA = new ColleagueA();
        AbstractColleague collB = new ColleagueB();

        System.out.println("==========设置A影响B==========");
        collA.setNumber(1288, collB);
        System.out.println("collA的number值：" + collA.getNumber());
        System.out.println("collB的number值：" + collB.getNumber());

        System.out.println("==========设置B影响A==========");
        collB.setNumber(87635, collA);
        System.out.println("collB的number值：" + collB.getNumber());
        System.out.println("collA的number值：" + collA.getNumber());
    }
}