package pattern23;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: ионГ10:56
 */
public class ConcreteCommand2 extends Command {
    private Receiver receiver;
    public ConcreteCommand2(Receiver receiver){
        this.receiver = receiver;
    }
    public void execute() {
        this.receiver.doSomething2();
    }
}