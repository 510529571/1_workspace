package own.hhw.business;

/**
 * User: hanwei
 * Date: 15-8-5
 * Time: 下午3:56
 */
public class BusinessException extends Exception {
    /**
     * 错误代码
     */
    private BusinessReturnCode errCode;

    /**
     * 构造函数,指定错误信息和异常堆栈
     *
     * @param cause 异常堆栈
     */
    public BusinessException(BusinessReturnCode errCode, Throwable cause) {
        super(errCode.getMsg(), cause);
        this.errCode = errCode;
    }

    /**
     * 构造函数,指定错误信息和异常堆栈
     *
     * @param cause 异常堆栈
     */
    public BusinessException(BusinessReturnCode errCode, String msg, Throwable cause) {
        super(errCode.getMsg() + " : " + msg, cause);
        this.errCode = errCode;
    }

    /**
     * 构造函数,指定错误编码
     *
     * @param errCode 错误编码
     */
    public BusinessException(BusinessReturnCode errCode) {
        super(errCode.getMsg());
        this.errCode = errCode;
    }

    /**
     * 构造函数,指定错误编码和错误提示
     *
     * @param errCode 错误编码
     */
    public BusinessException(BusinessReturnCode errCode, String msg) {
        super(errCode.getMsg() + " : " + msg);
        this.errCode = errCode;
    }

    /**
     * @return the errCode
     */
    public BusinessReturnCode getErrCode() {
        return this.errCode;
    }
}
