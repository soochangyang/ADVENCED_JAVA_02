package io.start;

import java.io.IOException;
import java.io.PrintStream;

import static java.lang.System.*;
import static java.nio.charset.StandardCharsets.*;

public class PrintStreamMain {
    public static void main(String[] args) throws IOException {
        PrintStream ps = out;

        byte[] bytes = "Hello!\n".getBytes(UTF_8);

        ps.write(bytes);
    }
}
