import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSuiteExample {
    @Test
    @Tag("fast")
    public void testFastOperation() {
        assertTrue(true);
    }

    @Test
    @Tag("slow")
    public void testSlowOperation() {
        assertTrue(true);
    }
}
