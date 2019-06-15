import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ChallengeRestController {

    private HttpHandlerFactory factory = new HttpHandlerFactory();

    public void startServer (int port) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            server.createContext("/api/hello", factory.getHelloHandler());
            server.createContext("/api/goodbye", factory.getGoodbyeHandler());
            server.setExecutor(null);
            server.start();
            System.out.println("Server started successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
