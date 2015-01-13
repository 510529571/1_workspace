package step3;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-8-25
 * Time: обнГ5:19
 * To change this template use File | Settings | File Templates.
 */
public class Finery extends Person {
    protected Person person;

    public void decorate(Person person){
        this.person=person;
    }

    public void Show(){
        person.Show();
    }

}
