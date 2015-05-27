package pattern23.terminalexpression;

import pattern23.Context;
import pattern23.Expression;

/**
 * User: hanwei
 * Date: 15-5-26
 * Time: ����3:54
 */
public class Variable extends Expression {
    public int interpret(Context con) {
        //thisΪ����interpret������Variable����
        return con.LookupValue(this);
    }
}
