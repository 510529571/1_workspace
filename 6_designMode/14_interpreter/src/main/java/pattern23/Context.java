package pattern23;

import pattern23.terminalexpression.Variable;

import java.util.HashMap;
import java.util.Map;

/**
 * User: hanwei
 * Date: 15-5-26
 * Time: ÏÂÎç3:51
 */
public class Context {
    private Map valueMap = new HashMap();

    public void addValue(Variable x, int y)

    {

        Integer yi = new Integer(y);

        valueMap.put(x, yi);

    }

    public int LookupValue(Variable x)

    {

        int i = ((Integer) valueMap.get(x)).intValue();

        return i;

    }
}
