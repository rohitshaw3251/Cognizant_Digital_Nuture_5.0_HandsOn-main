import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class AAATest {
    private List<String> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add("item1");
    }

    @AfterEach
    public void tearDown() {
        list.clear();
        list = null;
    }

    @Test
    public void testListAdd() {
        list.add("item2");
        assertEquals(2, list.size());
        assertTrue(list.contains("item2"));
    }
}
