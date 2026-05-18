package was.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpRequestHandlerV2 implements Runnable{

    private final Socket socket;

    public HttpRequestHandlerV2(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            process();
        } catch (Exception e){
            log(e);
        }
    }


/*    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("Server Starts Port : " + port);

        while (true) {
            Socket socket  = serverSocket.accept();
            process(socket);

        }
    }*/

    private void process( ) throws IOException {
        try(
                socket;
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
                PrintWriter  writer = new PrintWriter(socket.getOutputStream(),false, UTF_8);
        ){
            String requestString = requestToString(reader);
            if (requestString.contains("/favicon.ico")){
                log("Request favicon ");
                return;
            }

            log("Print HTTP Reuest Info");
            System.out.println(requestString);

            log("Creating Response........");
            sleep(3000);
            responseToClient(writer);
        }
    }


    private static String requestToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String lines;
        while((lines = reader.readLine()) != null){
            if (lines.isEmpty()){
                break;
            }
            sb.append(lines).append("\n");
        }
        return sb.toString();
    }

    private void responseToClient(PrintWriter writer){
        //

        String body = "<h1>Hello World</h1>";
        int length = body.getBytes(UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html; charset=utf-8\r\n");
        sb.append("Content-Length: ").append(length).append("\r\n");
        sb.append("\r\n");
        sb.append(body);

        log("Print HTTP Reuest Info");
        System.out.println(sb);

        writer.println(sb.toString());
        writer.flush();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
