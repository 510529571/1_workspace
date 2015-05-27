package pattern23.after;

/**
 * User: hanwei
 * Date: 15-5-22
 * Time: ÏÂÎç3:36
 */
public class ColleagueA extends AbstractColleague {

    public void setNumber(int number, AbstractMediator am) {
        this.number = number;
        am.AaffectB();
    }
}