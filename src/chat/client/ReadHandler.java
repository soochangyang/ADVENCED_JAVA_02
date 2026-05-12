package chat.client;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.log;

public class ReadHandler implements Runnable {

    private final DataInputStream input;
    private final Client client;
    public boolean closed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = input.readUTF();
                System.out.println(received);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        } finally{
            client.close();
        }
     }

     // 동기화 처리
     public synchronized void close(){
        if  (closed) {
            return;
        }

        closed = true;
        log(" readHandler Finish");
     }
}
