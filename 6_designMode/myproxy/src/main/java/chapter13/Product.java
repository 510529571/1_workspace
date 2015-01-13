package chapter13;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-10-30
 * Time: обнГ3:54
 * To change this template use File | Settings | File Templates.
 */
public class Product {
    List<String> parts = new ArrayList<String>();

    public void add(String part) {
        parts.add(part);
    }

    public void show() {
        for (String s : parts) {
            System.out.println(s);
        }
    }
}
