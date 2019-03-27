package Server;

import org.json.JSONException;
import org.json.JSONObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        System.out.println("Server is booting");

        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String json = in.readLine();
            JSONObject obj;

            try {
                obj = new JSONObject(json);
                String calcul = obj.getString("Calculation");

                // auto eval of a string to a math function using javascript
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("js");
                Object result = engine.eval(calcul);

                obj.put("Error", "");
                obj.put("Result", result.toString());
            } catch (JSONException e){
                obj = new JSONObject();
                obj.put("Calculation", json);
                obj.put("Error", e.toString());
                obj.put("Result", "");
            } catch (ScriptException e){
                obj = new JSONObject();
                obj.put("Calculation", json);
                obj.put("Error", e.toString());
                obj.put("Result", "");
            }

            out.println(obj.toString());

            stop();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void stop() {
        System.out.println("Server is shutting down");

        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Server server = new Server();
        server.start(6666);
    }
}
