package own.hhw.step1.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 15-1-8
 * Time: ����4:39
 * To change this template use File | Settings | File Templates.
 */
public class LogUtil {
    private static Log LOGGER = LogFactory.getLog(LogUtil.class);

    public Object logBefore(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        if (point.getArgs() != null) {
            LOGGER.info("dubbo�ӿ�" + declaringTypeName + "." + methodName + "�������������Ϊ��" + Arrays.toString(point.getArgs()));
        }
        Object proceed = null;
        long begin = System.currentTimeMillis();
        try {
            proceed = point.proceed();
            LOGGER.info("dubbo�ӿ�" + declaringTypeName + "." + methodName + "�ķ�������Ϊ��" + proceed + ",��ʱ��" + (System.currentTimeMillis() - begin) + "������");
        } catch (Throwable throwable) {
            LOGGER.warn("dubbo�ӿ�" + declaringTypeName + "." + methodName + "���쳣��ϢΪ��" + throwable.toString());
            throw throwable;
        }
        return proceed;
    }
}
