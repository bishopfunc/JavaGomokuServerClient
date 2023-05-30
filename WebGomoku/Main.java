package WebGomoku;

import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(80);
        System.out.println("started: " + server);
        System.out.println("start >>>");
        Socket socket = null;

        try {
            socket = server.accept();
            InputStream in = socket.getInputStream();
            HttpRequest request = new HttpRequest(in);
            System.out.println(request.getHeaderText());
            System.out.println(request.getBodyText());
            System.out.println("<<< end");
        } finally {
            if (socket != null) {
                socket.close();
                System.out.println("closing socket...");
            }
        }

        server.close();
        System.out.println("closing server...");
    }
}
