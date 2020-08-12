package neointernship.chess.logger;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;
import java.util.HashMap;

public class ErrorLoggerClient {
    private static final HashMap<String, ErrorLoggerClient> mapLogger = new HashMap<String, ErrorLoggerClient>();
    private Logger logger;

    private ErrorLoggerClient(final String name) {
        logger = Logger.getLogger(name);

        try {
            final PatternLayout patternLayout = new PatternLayout();
            patternLayout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
            logger.addAppender(new FileAppender(patternLayout, "logs\\client\\error_log_player_" + name + ".txt"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void addLogger(final String name) {
        mapLogger.put(name, new ErrorLoggerClient(name));
    }

    public static ErrorLoggerClient getLogger(final String name) {
        return mapLogger.get(name);
    }

    public void logException(final Exception exception) {
       // logger.warn(exception, exception.fillInStackTrace());
    }

    public void logError(final Error error) {
      //  logger.error(error, error.fillInStackTrace());
    }
}
