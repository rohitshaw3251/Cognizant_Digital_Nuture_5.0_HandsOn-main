import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

interface DatabaseConnection {
    String query(String sql);
}

interface RestApiClient {
    String get(String url);
}

interface FileService {
    String read(String filepath) throws IOException;
}

interface NetworkConnection {
    void connect() throws IOException;
}

public class AdvancedMockitoTest {
    @Test
    public void testDatabaseMock() {
        DatabaseConnection mockDb = Mockito.mock(DatabaseConnection.class);
        when(mockDb.query("SELECT name FROM users WHERE id = 1")).thenReturn("Alice");
        assertEquals("Alice", mockDb.query("SELECT name FROM users WHERE id = 1"));
    }

    @Test
    public void testRestApiMock() {
        RestApiClient mockClient = Mockito.mock(RestApiClient.class);
        when(mockClient.get("https://api.example.com/data")).thenReturn("{\"status\":\"success\"}");
        assertEquals("{\"status\":\"success\"}", mockClient.get("https://api.example.com/data"));
    }

    @Test
    public void testFileServiceMock() throws IOException {
        FileService mockFileService = Mockito.mock(FileService.class);
        when(mockFileService.read("config.txt")).thenReturn("port=8080");
        assertEquals("port=8080", mockFileService.read("config.txt"));
    }

    @Test
    public void testNetworkServiceMock() throws IOException {
        NetworkConnection mockNetwork = Mockito.mock(NetworkConnection.class);
        Mockito.doThrow(new IOException("Connection Refused")).when(mockNetwork).connect();
        assertThrows(IOException.class, () -> mockNetwork.connect());
    }

    @Test
    public void testMultipleReturnMock() {
        DatabaseConnection mockDb = Mockito.mock(DatabaseConnection.class);
        when(mockDb.query("SELECT count(*) FROM users")).thenReturn("0").thenReturn("1").thenReturn("2");
        assertEquals("0", mockDb.query("SELECT count(*) FROM users"));
        assertEquals("1", mockDb.query("SELECT count(*) FROM users"));
        assertEquals("2", mockDb.query("SELECT count(*) FROM users"));
    }
}
