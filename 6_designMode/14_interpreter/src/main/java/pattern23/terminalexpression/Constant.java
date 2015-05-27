package pattern23.terminalexpression;

import pattern23.Context;
import pattern23.Expression;

/**
 * User: hanwei
 * Date: 15-5-26
 * Time: обнГ3:53
 */
public class Constant extends Expression {
    private int i;

    public Constant(int i)

    {

        this.i = i;

    }

    public int interpret(Context con)

    {

        return i;

    }

}