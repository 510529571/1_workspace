package pattern23.before;

/**
 * User: hanwei
 * Date: 15-5-22
 * Time: ����3:35
 */
public class Client {
    public static void main(String[] args) {

        AbstractColleague collA = new ColleagueA();
        AbstractColleague collB = new ColleagueB();

        System.out.println("==========����AӰ��B==========");
        collA.setNumber(1288, collB);
        System.out.println("collA��numberֵ��" + collA.getNumber());
        System.out.println("collB��numberֵ��" + collB.getNumber());

        System.out.println("==========����BӰ��A==========");
        collB.setNumber(87635, collA);
        System.out.println("collB��numberֵ��" + collB.getNumber());
        System.out.println("collA��numberֵ��" + collA.getNumber());
    }
}