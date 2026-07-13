import org.junit.jupiter.api.Test;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class PerformanceTest {
    @Test
    public void testPerformance() {
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(10);
        });
    }
}
