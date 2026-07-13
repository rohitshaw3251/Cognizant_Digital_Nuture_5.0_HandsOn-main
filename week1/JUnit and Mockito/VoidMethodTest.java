import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

interface VoidService {
    void performAction(String data);
}

public class VoidMethodTest {
    @Test
    public void testVoidMethodException() {
        VoidService mockService = Mockito.mock(VoidService.class);
        doThrow(new RuntimeException("Error")).when(mockService).performAction("bad data");

        assertThrows(RuntimeException.class, () -> mockService.performAction("bad data"));
        verify(mockService).performAction("bad data");
    }
}
