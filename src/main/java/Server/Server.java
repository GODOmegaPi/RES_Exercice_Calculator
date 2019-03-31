package Server;

public class Server {
    public static void main(String[] args) {
        MultiThreadedServer server = new MultiThreadedServer(6666);
        new Thread(server).start();

        try {
            Thread.sleep(500 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Stopping Server");
        server.stop();
    }
}
