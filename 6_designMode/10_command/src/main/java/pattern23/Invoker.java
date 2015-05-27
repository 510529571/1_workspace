package pattern23;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: ионГ10:56
 */
public class Invoker {
    private Command command;
    public void setCommand(Command command) {
        this.command = command;
    }
    public void action(){
        this.command.execute();
    }
}