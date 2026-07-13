import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

interface CounterService {
    int getNext();
}

public class MultipleReturnsTest {
    @Test
    public void testMultipleReturns() {
        CounterService mockService = Mockito.mock(CounterService.class);
        when(mockService.getNext()).thenReturn(1).thenReturn(2).thenReturn(3);

        assertEquals(1, mockService.getNext());
        assertEquals(2, mockService.getNext());
        assertEquals(3, mockService.getNext());
    }
}
