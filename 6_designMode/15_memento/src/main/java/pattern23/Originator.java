package pattern23;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: ÏÂÎç4:29
 */
public class Originator {
    private String state = "";

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Memento createMemento(){
        return new Memento(this.state);
    }
    public void restoreMemento(Memento memento){
        this.setState(memento.getState());
    }
}