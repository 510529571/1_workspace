package pattern23;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: ионГ10:56
 */
public class ConcreteCommand extends Command {
    private Receiver receiver;
    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }
    public void execute() {
        this.receiver.doSomething();
    }
}