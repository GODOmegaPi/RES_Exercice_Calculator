package Server;

import org.json.JSONException;
import org.json.JSONObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.Socket;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket = null;
    protected String serverText = null;
    private PrintWriter out;
    private BufferedReader in;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    public void run() {
        try {
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
                obj.put("Calculation", "");
                obj.put("Error", "The JSON is not well formated or is missing some informations!");
                obj.put("Result", "");
            } catch (ScriptException e){
                obj = new JSONObject();
                obj.put("Calculation", "");
                obj.put("Error", "The JSON use fonctions that are not part of JavaScript!");
                obj.put("Result", "");
            }

            out.println(obj.toString());

            in.close();
            out.close();
            System.out.println("Request processed");
            System.out.println("Closing connection with the client");
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
        System.out.println("Closing the thread");
    }
}
