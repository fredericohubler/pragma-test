public class App {

    public static final int SERVER_PORT = 8000;

    public static void main(String[] args) {
        ChallengeRestController rest = new ChallengeRestController();
        rest.startServer(SERVER_PORT);
    }

}
