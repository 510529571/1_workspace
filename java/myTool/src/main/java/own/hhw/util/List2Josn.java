package own.hhw.util;

import java.util.*;


public class List2Josn {
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("country_cnname", "中国");
        for (int i = 0; i < 5; i++) {

            Map<String, Object> map_temp = new HashMap<String, Object>();
            map_temp.put("city_id", "817" + i);
            map_temp.put("city_cnname", "南宁" + i);
            map_temp.put("city_enname", "nanning" + i);
            list2.add(map_temp);
        }
        map1.put("cityList", list2);
        list.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("country_cnname", "美国");
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map_temp = new HashMap<String, Object>();
            map_temp.put("city_id", "817" + i);
            map_temp.put("city_cnname", "洛杉矶" + i);
            map_temp.put("city_enname", "nanning" + i);
            list3.add(map_temp);
        }
        map2.put("cityList", list3);
        list.add(map2);
        String retStr = List2Josn.getJsonData(list, "");
        System.out.println(retStr);
    }

    /**
     * 将list,map转换成json格式的数据
     */
    public static String getJsonData(List<Map<String, Object>> list, String rootName) {
        StringBuffer sb = new StringBuffer();
        if (!"".equals(rootName))
            sb.append("[{" + rootName + ":[");
        else
            sb.append("[");
        String objOut = "";
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            Set set = map.keySet();
            Iterator it = set.iterator();
            String obj = ",{";
            while (it.hasNext()) {
                String key = (String) it.next();
                if (map.get(key) == null) {
                    obj += key + ":\"\",";
                } else if (map.get(key) instanceof String) {
                    obj += key + ":\"" + map.get(key) + "\",";
                } else {
                    obj += key + ":" + getJsonData((List<Map<String, Object>>) map.get(key), "") + ",";
                }
            }
            obj = obj.substring(0, obj.length() - 1);
            obj += "}";
            objOut += obj;
        }
        if (!"".equals(objOut)) {
            objOut = objOut.substring(1);
            sb.append(objOut);
        }
        if (!"".equals(rootName))
            sb.append("]}]");
        else
            sb.append("]");
        return sb.toString();
    }
}
