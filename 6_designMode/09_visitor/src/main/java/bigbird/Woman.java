package bigbird;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: …œŒÁ10:16
 */
public class Woman implements Person {

    @Override
    public String getGender() {
        return "≈Æ»À";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void accept(Action visitor) {
        visitor.getWomanConclusion(this);
    }
}
