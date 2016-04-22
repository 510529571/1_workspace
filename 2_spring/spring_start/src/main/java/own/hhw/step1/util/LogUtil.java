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
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public class LogUtil {
    private static Log LOGGER = LogFactory.getLog(LogUtil.class);

    public Object logBefore(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        if (point.getArgs() != null) {
            LOGGER.info("dubbo接口" + declaringTypeName + "." + methodName + "的请求参数内容为：" + Arrays.toString(point.getArgs()));
        }
        Object proceed = null;
        long begin = System.currentTimeMillis();
        try {
            proceed = point.proceed();
            LOGGER.info("dubbo接口" + declaringTypeName + "." + methodName + "的返回内容为：" + proceed + ",耗时（" + (System.currentTimeMillis() - begin) + "）毫秒");
        } catch (Throwable throwable) {
            LOGGER.warn("dubbo接口" + declaringTypeName + "." + methodName + "的异常信息为：" + throwable.toString());
            throw throwable;
        }
        return proceed;
    }
}
