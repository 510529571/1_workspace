package own.hhw.business;

/**
 * User: hanwei
 * Date: 15-8-5
 * Time: ����3:56
 */
public class BusinessException extends Exception {
    /**
     * �������
     */
    private BusinessReturnCode errCode;

    /**
     * ���캯��,ָ��������Ϣ���쳣��ջ
     *
     * @param cause �쳣��ջ
     */
    public BusinessException(BusinessReturnCode errCode, Throwable cause) {
        super(errCode.getMsg(), cause);
        this.errCode = errCode;
    }

    /**
     * ���캯��,ָ��������Ϣ���쳣��ջ
     *
     * @param cause �쳣��ջ
     */
    public BusinessException(BusinessReturnCode errCode, String msg, Throwable cause) {
        super(errCode.getMsg() + " : " + msg, cause);
        this.errCode = errCode;
    }

    /**
     * ���캯��,ָ���������
     *
     * @param errCode �������
     */
    public BusinessException(BusinessReturnCode errCode) {
        super(errCode.getMsg());
        this.errCode = errCode;
    }

    /**
     * ���캯��,ָ���������ʹ�����ʾ
     *
     * @param errCode �������
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
