package neointernship.chess.logger;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;


public class ErrorLoggerServer {
    private static Logger logger;

    private ErrorLoggerServer() {
        logger = Logger.getLogger(ErrorLoggerServer.class);

        try {
            final PatternLayout patternLayout = new PatternLayout();
            patternLayout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss}  %-5p %c{1}:");
            logger.addAppender(new FileAppender(patternLayout, "logs\\server\\error_log_server.txt", true));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void logException(final Exception exception) {
        if (logger == null) new ErrorLoggerServer();
        logger.warn(exception, exception.fillInStackTrace());
    }

    public static void logError(final Error error) {
        if (logger == null) new ErrorLoggerServer();
        logger.error(error, error.fillInStackTrace());
    }
}
