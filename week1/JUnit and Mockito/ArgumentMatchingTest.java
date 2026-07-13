import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

interface ApiService {
    String process(String input);
}

public class ArgumentMatchingTest {
    @Test
    public void testArgumentMatching() {
        ApiService mockApi = Mockito.mock(ApiService.class);
        when(mockApi.process(anyString())).thenReturn("Response");

        assertEquals("Response", mockApi.process("hello"));
        assertEquals("Response", mockApi.process("world"));
    }
}
