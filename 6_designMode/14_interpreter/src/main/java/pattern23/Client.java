package pattern23;

import pattern23.nonterminalexpression.Add;
import pattern23.nonterminalexpression.Division;
import pattern23.nonterminalexpression.Multiply;
import pattern23.nonterminalexpression.Subtract;
import pattern23.terminalexpression.Constant;
import pattern23.terminalexpression.Variable;

/**
 * User: hanwei
 * Date: 15-5-26
 * Time: 下午3:58
 */
public class Client {
    private static Expression ex;

    private static Context con;

    public static void main(String[] args) {
        con = new Context();
        //设置变量、常量
        Variable a = new Variable();
        Variable b = new Variable();
        Constant c = new Constant(2);
        //为变量赋值
        con.addValue(a, 5);
        con.addValue(b, 8);

        //运算，对句子的结构由我们自己来分析，构造
        ex = new Division(new Multiply(a, b), new Add(new Subtract(a, b), c));

        System.out.println("运算结果为：" + ex.interpret(con));

    }
}
