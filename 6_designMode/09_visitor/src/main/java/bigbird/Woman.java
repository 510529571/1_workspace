package bigbird;

/**
 * User: hanwei
 * Date: 15-5-25
 * Time: ����10:16
 */
public class Woman implements Person {

    @Override
    public String getGender() {
        return "Ů��";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void accept(Action visitor) {
        visitor.getWomanConclusion(this);
    }
}
