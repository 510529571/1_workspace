package step1;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-8-25
 * Time: ����4:58
 * To change this template use File | Settings | File Templates.
 */
public class Person {
    private String name;
    public Person(String name)
    {
        this.name = name;
    }

    public static void main(String[] args) {
        Person xc = new Person("С��");

        System.out.println("\n��һ��װ�磺");

        xc.WearTShirts();
        xc.WearBigTrouser();
        xc.WearSneakers();
        xc.Show();

        System.out.println("\n�ڶ���װ�磺");

        xc.WearSuit();
        xc.WearTie();
        xc.WearLeatherShoes();
        xc.Show();

    }

    public void WearTShirts()
    {
        System.out.println("��T�� ");
    }

    public void WearBigTrouser()
    {
        System.out.println("��� ");
    }

    public void WearSneakers()
    {
        System.out.println("����Ь ");
    }

    public void WearSuit()
    {
        System.out.println("��װ ");
    }

    public void WearTie()
    {
        System.out.println("��� ");
    }

    public void WearLeatherShoes()
    {
        System.out.println("ƤЬ ");
    }

    public void Show()
    {
        System.out.println("װ���");
    }
}
