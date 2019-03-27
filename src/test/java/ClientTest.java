import Client.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    @Test
    public void givenClient_whenServerRespondsWhenStarted_thenCorrect() {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("{\"Calculation\": \"Math.pow(2,4) % 7\" }");
        assertEquals("{\"Error\":\"\",\"Calculation\":\"Math.pow(2,4) % 7\",\"Result\":\"2.0\"}", response);
    }
}
