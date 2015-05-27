package bigbird;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ÉÏÎç10:16
 */
public class Man implements Person {
    @Override
    public String getGender() {
        return "ÄĞÈË";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void accept(Action visitor) {
        visitor.getManConclusion(this);
    }
}
