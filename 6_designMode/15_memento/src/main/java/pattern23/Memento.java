package pattern23;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: ÏÂÎç4:29
 */
public class Memento {
    private String state = "";
    public Memento(String state){
        this.state = state;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
