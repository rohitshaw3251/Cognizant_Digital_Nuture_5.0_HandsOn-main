import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderLogging {
    private static final Logger consoleLogger = LoggerFactory.getLogger("ConsoleLogger");
    private static final Logger fileLogger = LoggerFactory.getLogger("FileLogger");

    public static void main(String[] args) {
        consoleLogger.info("This log is routed to the Console Appender");
        fileLogger.warn("This log is simulated to be routed to the File Appender");
    }
}
