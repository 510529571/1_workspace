package own.hhw.lang.fangxing;

/**
 * User: hanwei
 * Date: 15-7-7
 * Time: обнГ5:08
 */
public class Proxy<T> {
    private T t;

    public Proxy(T t) {
        this.t = t;
    }

    public T getObject() {
        return t;
    }
}
