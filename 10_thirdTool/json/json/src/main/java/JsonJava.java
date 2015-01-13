import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * java处理json需要导入的jar包
 * 1.commons-lang-2.4.jar
 * 2.commons-logging-1.1.1.jar
 * 3.json-lib-2.2.3-jdk13.jar
 * 4.ezmorph-1.0.6.jar
 * 5.commons-collections-3.2.1.jar
 *
 * @author 胡寒伟
 * @class JsonJava
 * @description
 * @copyRight copyright(c) 2012 广东南航易网通电子商务有限公司,Rights Reserved
 * @time Dec 7, 2012 11:26:11 AM
 */
public class JsonJava {
    /**
     * 字符串转换成JSONObject
     *
     * @throws java.io.IOException
     */
    public static void paserJson() throws IOException {
        File f = new File("src/json.txt");
        InputStream is = new FileInputStream(f);

        byte[] b = new byte[1024];

        int a = 0;
        StringBuffer sb = new StringBuffer();
        a = is.read(b);
        while (a != -1) {
            sb.append(new String(b, 0, a));
            a = is.read(b);
        }
        JSONObject jObject = JSONObject.fromObject(sb.toString());

        JSONArray jsonArray = jObject.getJSONArray("programmers");

        for (Object j_temp : jsonArray) {
            JSONObject jObject_temp = (JSONObject) j_temp;

            System.out.println("姓：" + jObject_temp.get("firstName"));
            System.out.println("名：" + jObject_temp.get("lastName"));
        }
    }

    public static void listToJson() {
        List list = new ArrayList<Student>();
        Student s1 = new Student();
        s1.setName("liutao");
        s1.setAge(12);

        Student s2 = new Student();
        s2.setName("jinglong");
        s2.setAge(13);

        list.add(s1);
        list.add(s2);

        JSONArray jObject = JSONArray.fromObject(list);

        System.out.println(jObject.toString());

    }

    public static void mapToJson() {
        Map map = new HashMap<String, Object>();
        map.put("name", "laoda");
        map.put("age", 12);
        JSONObject jObject = JSONObject.fromObject(map);

        System.out.println(jObject.toString());
    }

    public static void arrayToJson() {
        boolean[] boolArray = new boolean[]{true, false, true};

        JSONArray jsonArray1 = JSONArray.fromObject(boolArray);

        System.out.println(jsonArray1.get(0).toString());

        Student s1 = new Student();
        s1.setName("liutao");
        s1.setAge(12);
        JSONObject js = JSONObject.fromObject(s1);

        System.out.println(js.toString());


        JSONArray jsonArray3 = JSONArray.fromObject("['json','is','easy']");

        System.out.println(jsonArray3.get(0).toString());
        System.out.println(jsonArray3.toString());
    }

    public void testlistToJson() {
        JsonJava.listToJson();
        JsonJava.mapToJson();
        JsonJava.arrayToJson();
    }

    public void test1() {

        try {
            JsonJava.paserJson();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class Student {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}