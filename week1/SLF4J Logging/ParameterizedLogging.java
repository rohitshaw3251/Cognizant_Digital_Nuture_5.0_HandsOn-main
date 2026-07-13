import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLogging {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLogging.class);

    public static void main(String[] args) {
        String user = "Alice";
        int loginAttempts = 3;
        logger.info("User {} failed to login after {} attempts", user, loginAttempts);
    }
}
