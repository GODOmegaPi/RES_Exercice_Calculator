import Client.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    @Test
    public void givenClient_whenServerRespondsWhenStarted_thenCorrect() {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("{\"Calcul\": \"Math.pow(2,4) % 7\" }");
        assertEquals("{\"Calcul\":\"Math.pow(2,4) % 7\",\"Error\":\"\",\"Result\":\"2.0\"}", response);
    }
}
