package pattern23.before;

/**
 * User: hanwei
 * Date: 15-5-22
 * Time: ����3:34
 */
public abstract class AbstractColleague {
    protected int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //���󷽷����޸�����ʱͬʱ�޸Ĺ�������
    public abstract void setNumber(int number, AbstractColleague coll);
}