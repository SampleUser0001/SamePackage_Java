package ittimfn.same.logger;

import ittimfn.same.logger.AppLogger;

import org.junit.Test;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.WriterAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.junit.Before;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.LogManager;

public class AppLoggerTest {
    
    public AppLogger test;
    public static final String MESSAGE = "message";

    private Method infoMessage;
    private Method errorMessage;

    @Before
    public void setUp() throws NoSuchMethodException, SecurityException {
        test = new AppLogger();

        infoMessage = this.test.getClass().getDeclaredMethod("infoMessage", Object.class);
        infoMessage.setAccessible(true);

        errorMessage = this.test.getClass().getDeclaredMethod("errorMessage", Object.class);
        errorMessage.setAccessible(true);
    }

    @Test
    public void privateInfo() {
        String value = "value";
        assertThat(MESSAGE + ", I have " + value + "." , is(equalTo(getInfoMessage(value))));
    }

    @Test
    public void privateError() {
        String value = "value";
        assertThat(MESSAGE + ", " + AppLogger.KEY + " is Not Found.", is(equalTo(getErrorMessage(value))));
    }

    @Test
    public void infoMessageTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String value = "hoge";

        test.put(value);
        String actural = (String)infoMessage.invoke(test, MESSAGE);
        assertThat(actural, is(equalTo(getInfoMessage(value))));
    }

    @Test
    public void errorMessageTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String actual = (String) errorMessage.invoke(test, MESSAGE);
        assertThat(actual, is(equalTo(getErrorMessage(null))));
    }

    private static String getInfoMessage(String value) {
        return String.format("%s, I have %s.", MESSAGE, value);
    }
    private static String getErrorMessage(String value) {
        return String.format("%s, %s is Not Found.", MESSAGE, AppLogger.KEY);
    }

    private void addAppender(Writer writer, String name) {
        final LoggerContext context = LoggerContext.getContext(false);
        final Configuration config = context.getConfiguration();
        final PatternLayout layout = PatternLayout.createDefaultLayout(config);
    
        Appender appender = WriterAppender.createAppender(layout, null, writer, name, false, true);
        appender.start();
        config.addAppender(appender);
        updateLoggers(appender, config);
    }
    
    private void updateLoggers(final Appender appender, final Configuration config) {
        for (final LoggerConfig loggerConfig : config.getLoggers().values()) {
          loggerConfig.addAppender(appender, null, null);
        }
        config.getRootLogger().addAppender(appender, null, null);
    }    
}
