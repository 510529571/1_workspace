package pattern23.terminalexpression;

import pattern23.Context;
import pattern23.Expression;

/**
 * User: hanwei
 * Date: 15-5-26
 * Time: 下午3:54
 */
public class Variable extends Expression {
    public int interpret(Context con) {
        //this为调用interpret方法的Variable对象
        return con.LookupValue(this);
    }
}
