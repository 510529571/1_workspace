package own.hhw.lang.enum1;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hanwei
 * Date: 15-8-5
 * Time: ÏÂÎç4:04
 */
public enum OrderState {
    success("0", "³É¹¦"),
    fail("1", "Ê§°Ü");

    private String key;
    private String value;

    private OrderState(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public List getKeys() {
        List<String> list = new ArrayList<String>();
        OrderState[] orderStateList = OrderState.values();
        for (OrderState state : orderStateList) {
            list.add(state.getKey());
        }
        return list;
    }

    public String getValue(String key){
        OrderState[] orderStateList = OrderState.values();
        for (OrderState state : orderStateList) {
            if(state.getKey().equals(key)){
                return state.getValue();
            }
        }
        return null;
    }
}