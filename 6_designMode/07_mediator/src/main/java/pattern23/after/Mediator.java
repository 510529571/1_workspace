package pattern23.after;

/**
 * User: hanwei
 * Date: 15-5-22
 * Time: ����3:36
 */
public class Mediator extends AbstractMediator {

    public Mediator(AbstractColleague a, AbstractColleague b) {
        super(a, b);
    }

    //����A��B��Ӱ��
    public void AaffectB() {
        int number = A.getNumber();
        B.setNumber(number*100);
    }

    //����B��A��Ӱ��
    public void BaffectA() {
        int number = B.getNumber();
        A.setNumber(number/100);
    }
}