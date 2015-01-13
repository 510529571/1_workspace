package chapter6;

import chapter6.finery.*;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-27
 * Time: ÏÂÎç4:50
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Person person=new Person("Ð¡²Ë");

        BigTrouser bigTrouser=new BigTrouser();
        Sneakers sneakers=new Sneakers();
        TShirts tShirts=new TShirts();

        bigTrouser.decorate(person);
        sneakers.decorate(bigTrouser);
        tShirts.decorate(sneakers);

        tShirts.show();

        System.out.println("=============");

        LeatherShoes leatherShoes=new LeatherShoes();
        Suit suit=new Suit();
        Tie tie=new Tie();

        leatherShoes.decorate(person);
        suit.decorate(leatherShoes);
        tie.decorate(suit);

        tie.show();

    }
}
