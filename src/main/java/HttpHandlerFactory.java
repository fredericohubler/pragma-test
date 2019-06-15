import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;

public class HttpHandlerFactory {

    public HttpHandler getHelloHandler() {
        return httpExchange -> {
            String respText = "Hello!";
            httpExchange.sendResponseHeaders(200, 0);
            OutputStream out = httpExchange.getResponseBody();
            out.write(respText.getBytes());
            out.flush();
            httpExchange.close();
            System.out.println("Hello endpoint called.");
        };
    }

    public HttpHandler getGoodbyeHandler() {
        return httpExchange -> {
            String respText = "Goodbye!";
            httpExchange.sendResponseHeaders(200, 0);
            OutputStream out = httpExchange.getResponseBody();
            out.write(respText.getBytes());
            out.flush();
            httpExchange.close();
            System.out.println("Goodbye endpoint called.");
        };
    }
}
