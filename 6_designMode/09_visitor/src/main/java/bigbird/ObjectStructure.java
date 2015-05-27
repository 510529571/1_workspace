package bigbird;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ионГ10:41
 */
public class ObjectStructure {
    private List<Person> personList=new ArrayList<Person>();

    public void add(Person person){
        personList.add(person);
    }
    public void remove(Person person){
        personList.remove(person);
    }

    public void display(Action visitor){
        for(Person person:personList){
            person.accept(visitor);
        }
    }
}
