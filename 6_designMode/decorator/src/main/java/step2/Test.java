package step2;


/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-8-25
 * Time: ����5:12
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        Person xc = new Person("С��");


        System.out.println("\n��һ��װ�磺");

        Finery dtx = new TShirts();
        Finery kk = new BigTrouser();
        Finery pqx = new Sneakers();

        dtx.Show();
        kk.Show();
        pqx.Show();
        xc.Show();

        System.out.println("\n�ڶ���װ�磺");

        Finery xz = new Suit();
        Finery ld = new Tie();
        Finery px = new LeatherShoes();

        xz.Show();
        ld.Show();
        px.Show();
        xc.Show();

    }
}
