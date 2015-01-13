package step1;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-8-25
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
public class Person {
    private String name;
    public Person(String name)
    {
        this.name = name;
    }

    public static void main(String[] args) {
        Person xc = new Person("小菜");

        System.out.println("\n第一种装扮：");

        xc.WearTShirts();
        xc.WearBigTrouser();
        xc.WearSneakers();
        xc.Show();

        System.out.println("\n第二种装扮：");

        xc.WearSuit();
        xc.WearTie();
        xc.WearLeatherShoes();
        xc.Show();

    }

    public void WearTShirts()
    {
        System.out.println("大T恤 ");
    }

    public void WearBigTrouser()
    {
        System.out.println("垮裤 ");
    }

    public void WearSneakers()
    {
        System.out.println("破球鞋 ");
    }

    public void WearSuit()
    {
        System.out.println("西装 ");
    }

    public void WearTie()
    {
        System.out.println("领带 ");
    }

    public void WearLeatherShoes()
    {
        System.out.println("皮鞋 ");
    }

    public void Show()
    {
        System.out.println("装扮的");
    }
}
