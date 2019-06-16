package com.pragmachallenge.handler;

import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    public HttpHandler getContainerHandler() {
        return httpExchange -> {
            if ("GET".equals(httpExchange.getRequestMethod())) {
                String respText = "Hello!";
                httpExchange.sendResponseHeaders(200, 0);
                OutputStream output = httpExchange.getResponseBody();
                output.write(respText.getBytes());
                output.flush();
            } else {
                httpExchange.sendResponseHeaders(405, -1);
            }
            httpExchange.close();
            System.out.println("Hello endpoint called.");
        };
    }

    public HttpHandler teste() {
        return httpExchange -> {
            OutputStream out = httpExchange.getResponseBody();
            String query = httpExchange.getRequestURI().getQuery();
            if (query == null) {
                httpExchange.sendResponseHeaders(200, 0);
                out.write("Hello!".getBytes());
                out.flush();
                httpExchange.close();
            }else {
                if (query.split("=")[0].equals("name")) {
                    Map<String, String> parms = queryToMap(query);
                    httpExchange.sendResponseHeaders(200, 0);
                    out.write(("Hello " + parms.get("name") + "!").getBytes());
                    out.flush();
                    httpExchange.close();
                } else {
                    httpExchange.sendResponseHeaders(404, 0);
                    out.write("Invalid parameter name!".getBytes());
                    out.flush();
                    httpExchange.close();
                }
            }
            System.out.println("teste chamado.");
        };
    }

    public Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }
}

