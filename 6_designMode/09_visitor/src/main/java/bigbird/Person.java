package bigbird;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ����10:11
 */
public interface Person {
    public String getGender();
    public void accept(Action visitor);
}
