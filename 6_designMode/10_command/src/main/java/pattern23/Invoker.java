package pattern23;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hanwei
 * Date: 15-5-19
 * Time: ионГ10:56
 */
public class Invoker {
    private List<Command> command=new ArrayList<Command>();

    public void addCommand(Command command) {
        this.command.add(command);
    }

    public void action() {
        for (Command cmd : command) {
            cmd.execute();
        }
    }
}