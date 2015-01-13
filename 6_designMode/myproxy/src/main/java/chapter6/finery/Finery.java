package chapter6.finery;

import chapter6.Person;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-27
 * Time: обнГ4:46
 * To change this template use File | Settings | File Templates.
 */
public class Finery extends Person {
    protected Person person;

    public void decorate(Person person) {
        this.person = person;
    }

    @Override
    public void show() {
        super.show();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
