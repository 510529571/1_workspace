import java.util.HashMap;
import java.util.Map;

/**
 * User: hanwei
 * Date: 15-4-14
 * Time: ионГ11:30
 */
public class TestMap {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<String, String>();
        map.put("name","hanwei");
        map.put("password","123");

        System.out.println(map.toString());
    }
}
