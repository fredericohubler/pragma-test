package com.pragmachallenge;

import com.pragmachallenge.handler.ContainerHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RestController {

    private ContainerHandler containerHandler = new ContainerHandler();

    public void startServer(int port) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            server.createContext("/containers", containerHandler.handle());
//            server.createContext("/container", containerHandler.teste());
            server.setExecutor(null);
            server.start();
            System.out.println("Server started successfully.");
        } catch (IOException e) {
            System.err.println("Unable to start server at port :");
        }
    }
}
