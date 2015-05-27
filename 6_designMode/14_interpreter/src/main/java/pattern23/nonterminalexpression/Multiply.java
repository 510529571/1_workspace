package pattern23.nonterminalexpression;

import pattern23.Context;
import pattern23.Expression;

/**
 * User: hanwei
 * Date: 15-5-26
 * Time: обнГ3:57
 */
public class Multiply extends Expression {

    private Expression left, right;

    public Multiply(Expression left, Expression right)

    {

        this.left = left;

        this.right = right;

    }

    public int interpret(Context con)

    {

        return left.interpret(con) * right.interpret(con);

    }

}