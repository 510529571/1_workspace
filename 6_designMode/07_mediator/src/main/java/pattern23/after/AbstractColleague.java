package pattern23.after;

/**
 * User: hanwei
 * Date: 15-5-22
 * Time: ����3:36
 */
public abstract class AbstractColleague {
    protected int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //ע������Ĳ���������ͬ���࣬����һ���н���
    public abstract void setNumber(int number, AbstractMediator am);
}