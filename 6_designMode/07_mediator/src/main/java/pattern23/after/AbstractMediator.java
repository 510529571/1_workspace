package pattern23.after;

/**
 * User: hanwei
 * Date: 15-5-22
 * Time: обнГ3:36
 */
public abstract class AbstractMediator {
    protected AbstractColleague A;
    protected AbstractColleague B;

    public AbstractMediator(AbstractColleague a, AbstractColleague b) {
        A = a;
        B = b;
    }

    public abstract void AaffectB();

    public abstract void BaffectA();

}