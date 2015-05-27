package pattern23_ext;

import java.util.Map;

/**
 * User: hanwei
 * Date: 15-5-18
 * Time: ÏÂÎç4:30
 */
public class Memento {
    private Map<String, Object> stateMap;

    public Memento(Map<String, Object> map){
        this.stateMap = map;
    }

    public Map<String, Object> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<String, Object> stateMap) {
        this.stateMap = stateMap;
    }
}