import own.hhw.IPayStarter;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-11-26
 * Time: ÏÂÎç3:52
 * To change this template use File | Settings | File Templates.
 */
public class MyContext {
    private boolean cacheEnabled;
    private boolean lazyLoadingEnabled;
    private IPayStarter payStarter;
    private Map<String,String> errorMsgMap;

    public boolean isCacheEnabled() {
        return cacheEnabled;
    }

    public void setCacheEnabled(boolean cacheEnabled) {
        this.cacheEnabled = cacheEnabled;
    }

    public boolean isLazyLoadingEnabled() {
        return lazyLoadingEnabled;
    }

    public void setLazyLoadingEnabled(boolean lazyLoadingEnabled) {
        this.lazyLoadingEnabled = lazyLoadingEnabled;
    }

    public IPayStarter getPayStarter() {
        return payStarter;
    }

    public void setPayStarter(IPayStarter payStarter) {
        this.payStarter = payStarter;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }
}
