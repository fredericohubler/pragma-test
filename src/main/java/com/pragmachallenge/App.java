package com.pragmachallenge;

public class App {

    public static final int SERVER_PORT = 8000;

    public static void main(String[] args) {
        RestController rest = new RestController();
        rest.startServer(SERVER_PORT);
    }

}
