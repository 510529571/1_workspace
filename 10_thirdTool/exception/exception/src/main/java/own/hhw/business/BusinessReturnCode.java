package own.hhw.business;

/**
 * User: hanwei
 * Date: 15-8-5
 * Time: 下午3:55
 */
public enum BusinessReturnCode {
    OK("0000", "成功"),
    UN_KNOWN("0001", "处理中");

    private String code;
    private String msg;

    BusinessReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
