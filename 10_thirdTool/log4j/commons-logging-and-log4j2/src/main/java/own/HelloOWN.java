package own;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by tw on 4/5/14.
 */
public class HelloOWN {
    private static Log log = LogFactory.getLog(HelloOWN.class);

    public void log(){
        log.trace("this is the ===trace=== level");
        log.debug("this is the ===debug=== level");
        log.info("this is the ===info=== level");
        log.error("this is the ===error=== level");
    }

    public static void main(String[] args){
        new HelloOWN().log();
    }
}
