import Client.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {
    @Test
    public void oneClientShouldReceiveTheCorrectJSONWhenAskingTheServer() {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("{\"Calculation\": \"Math.pow(2,4) % 7\" }");
        assertEquals("{\"Error\":\"\",\"Calculation\":\"Math.pow(2,4) % 7\",\"Result\":\"2.0\"}", response);
    }

    @Test
    public void fiveClientsShouldReceivesTheSameCorrectJSONWhenAskingTheServer() {
        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();
        Client client4 = new Client();
        Client client5 = new Client();

        client1.startConnection("127.0.0.1", 6666);
        client2.startConnection("127.0.0.1", 6666);
        client3.startConnection("127.0.0.1", 6666);
        client4.startConnection("127.0.0.1", 6666);
        client5.startConnection("127.0.0.1", 6666);

        String response;

        response = client1.sendMessage("{\"Calculation\": \"Math.pow(2,4) % 7\" }");
        assertEquals("{\"Error\":\"\",\"Calculation\":\"Math.pow(2,4) % 7\",\"Result\":\"2.0\"}", response);
        response = null;

        response = client2.sendMessage("{\"Calculation\": \"Math.pow(2,4) % 7\" }");
        assertEquals("{\"Error\":\"\",\"Calculation\":\"Math.pow(2,4) % 7\",\"Result\":\"2.0\"}", response);
        response = null;

        response = client3.sendMessage("{\"Calculation\": \"Math.pow(2,4) % 7\" }");
        assertEquals("{\"Error\":\"\",\"Calculation\":\"Math.pow(2,4) % 7\",\"Result\":\"2.0\"}", response);
        response = null;

        response = client4.sendMessage("{\"Calculation\": \"Math.pow(2,4) % 7\" }");
        assertEquals("{\"Error\":\"\",\"Calculation\":\"Math.pow(2,4) % 7\",\"Result\":\"2.0\"}", response);
        response = null;

        response = client5.sendMessage("{\"Calculation\": \"Math.pow(2,4) % 7\" }");
        assertEquals("{\"Error\":\"\",\"Calculation\":\"Math.pow(2,4) % 7\",\"Result\":\"2.0\"}", response);
        response = null;
    }

    @Test
    public void oneClientShouldReceiveAnErrorMessageIfTheJSONSentIsWrong() {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("{\"Calculation\": \"Math.pow(2,4) % 7\" ");
        assertEquals("{\"Error\":\"The JSON is not well formated or is missing some informations!\",\"JSON\":\"{\\\"Calculation\\\": \\\"Math.pow(2,4) % 7\\\" \",\"Result\":\"\"}", response);
    }

    @Test
    public void oneClientShouldReceiveAnErrorMessageIfTheCalculationSentIsWrong() {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("{\"Calculation\": \"Math.pow(2 4) % 7\" }");
        assertEquals("{\"Error\":\"The JSON is not well formated or is missing some informations!\",\"JSON\":\"{\\\"Calculation\\\": \\\"Math.pow(2 4) % 7\\\" }\",\"Result\":\"\"}", response);
    }
}
