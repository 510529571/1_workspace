package pattern23;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: обнГ4:30
 */
public interface Aggregate {
    public void add(Object obj);

    public void remove(Object obj);

    public Iterator iterator();
}
