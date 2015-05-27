package pattern23.before;

/**
 * User: hanwei
 * Date: 15-5-22
 * Time: ÏÂÎç3:35
 */
public class ColleagueB extends AbstractColleague {

    public void setNumber(int number, AbstractColleague coll) {
        this.number = number;
        coll.setNumber(number / 100);
    }
}