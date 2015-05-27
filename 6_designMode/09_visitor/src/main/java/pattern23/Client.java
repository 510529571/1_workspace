package pattern23;

import java.util.List;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ионГ10:03
 */
public class Client {
    public static void main(String[] args){
        List<Element> list = ObjectStruture.getList();
        for(Element e: list){
            e.accept(new Visitor());
        }
    }
}
