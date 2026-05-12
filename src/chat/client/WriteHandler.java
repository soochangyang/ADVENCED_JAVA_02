package chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static util.MyLogger.log;

public class WriteHandler implements Runnable{

    private static final String DELIMITER = "|";

    private final DataOutputStream output;
    private final Client client;

    private boolean closed;


    public WriteHandler(DataOutputStream output, Client client) {
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            String username = getString(scanner);
            output.writeUTF("/join"+DELIMITER +username);

            while(true){
                String toSend = scanner.next();
                if (toSend.isEmpty()){
                    continue;
                }

                if (toSend.equals("/exit")){
                    output.writeUTF(toSend);
                    break;
                }

                // Commend starts with "/" ;
                if (toSend.startsWith("/")){
                    output.writeUTF(toSend);
                } else {
                    output.writeUTF("message" + DELIMITER + toSend);
                }
            }
        } catch (IOException | NoSuchElementException e){
            log(e);
        } finally{
            client.close();
        }

    }

    private String getString(Scanner scanner) {
        System.out.println("Input your Name : ");
        String username;
        do {
            username = scanner.nextLine();
        } while(username.isBlank());
        return username;
    }

    public synchronized void close(){
        if (closed){
            return;
        }

        try {
            System.in.close(); // not allow to input in the console;
        } catch (IOException e) {
            log(e);
        }
        closed = true;
        log("writeHandler Finish");
    }
}
