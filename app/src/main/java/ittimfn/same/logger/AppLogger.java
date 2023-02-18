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
        this.logger.info("{},{}", obj, ThreadContext.get(KEY));
    }

    public void put(String value) {
        ThreadContext.put(KEY, value);
    }

    public void remove() {
        ThreadContext.remove(KEY);
    }

}
