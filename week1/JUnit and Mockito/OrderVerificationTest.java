import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.inOrder;

interface SequenceService {
    void stepOne();
    void stepTwo();
}

public class OrderVerificationTest {
    @Test
    public void testOrderVerification() {
        SequenceService mockService = Mockito.mock(SequenceService.class);
        mockService.stepOne();
        mockService.stepTwo();

        InOrder inOrder = inOrder(mockService);
        inOrder.verify(mockService).stepOne();
        inOrder.verify(mockService).stepTwo();
    }
}
