package own.hhw.lang.constructor;

import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-9-17
 * Time: обнГ3:36
 * To change this template use File | Settings | File Templates.
 */
public class Father implements Human {
    private Human human;

    public Father(Human human) {
        this.human = human;
    }

    public Human getHuman(){
        return human;
    }

    public java.lang.String getName() {
        return human.getName();
    }

    public void setName(String name) {
        human.setName(name);
    }
}
