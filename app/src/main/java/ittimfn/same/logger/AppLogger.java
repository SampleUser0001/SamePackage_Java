package ittimfn.same.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class AppLogger {
    private Logger logger;

    public static final String KEY = "key";

    public AppLogger() {
        this.logger = LogManager.getLogger();
    }
    
    public void log(Object obj) {
        if (ThreadContext.containsKey(KEY)) {
            this.logger.info(this.infoMessage(obj));
        } else {
            this.logger.error(this.errorMessage(obj));
        }
    }

    private String infoMessage(Object obj) {
        return String.format("%s, I have %s.", obj, ThreadContext.get(KEY));
    }
    private String errorMessage(Object obj) {
        return String.format("%s, %s is Not Found.", obj, KEY);
    }

    public void put(String value) {
        ThreadContext.put(KEY, value);
    }

    public void remove() {
        ThreadContext.remove(KEY);
    }

}
