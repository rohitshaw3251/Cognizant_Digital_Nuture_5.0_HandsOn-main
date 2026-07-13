import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static volatile Logger instance;

    private Logger() {
        System.out.println("[Logger] Initializing Logger instance...");
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = now.format(formatter);
        System.out.println("[" + formattedTime + "] " + message);
    }
}
