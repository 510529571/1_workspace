/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-11-27
 * Time: ÉÏÎç10:51
 * To change this template use File | Settings | File Templates.
 */
public class SqlMapper {
    private String key;
    private String reqType;
    private String respType;
    private String sql;
    private String type;//0 :insert 1:select

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
