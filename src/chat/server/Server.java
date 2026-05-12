package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Server {
    private final int port;
    private final CommandManager commandManager;
    private final SessionManager sessionManager;

    private ServerSocket serverSocket;

    public Server(int port, CommandManager commandManager, SessionManager sessionManager) {
        this.port = port;
        this.commandManager = commandManager;
        this.sessionManager = sessionManager;
    }

    public void start() throws IOException {
        log(" Server Starts :  "+  commandManager.getClass());
        serverSocket = new ServerSocket(port);
        log("Server socket Starts (PORT): " + port);

        addShutdownHook();
        running();
    }

    private void addShutdownHook() {
        //regist shutdownhbook
        ShutdownHook target = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(target, "shutdown"));
    }

    private void running() {
        //start program
        try {
            while(true){
                Socket socket = serverSocket.accept();
                log("connected socket: " + socket);

                Session session = new Session(socket, commandManager, sessionManager);
                Thread thread  = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("ServerSocket close : " + e);
        }
    }

    static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManager sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("ShutdownHook Started ");

            try {
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000);
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }
}
