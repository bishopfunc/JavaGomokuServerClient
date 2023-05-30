import java.net.*;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
      ServerSocket server = new ServerSocket(80);
      System.out.println("started: " + server);
      System.out.println("start >>>");
      try {
        Socket socket = server.accept();
        try {
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")); {
            String line = in.readLine();
            StringBuffer header = new StringBuffer();
            StringBuffer body = new StringBuffer();
            int contentLength = 0;
            while (line != null && !line.isEmpty()) { //空文字列になるまで読み込む
              if (line.startsWith("Content-Length")) {
                contentLength = Integer.parseInt(line.split(":")[1].trim()); // Content-Length: 14 をパース
              }
              header.append(line + "\n");
              line = in.readLine();
            }
            for (int i = 0; i < contentLength; i++) { //contentLength分読み込む
              int c = in.read();
              if(c == -1) break;
              body.append((char)c);
            }
            System.out.println(header);
            System.out.println(body);
          }
          System.out.println("<<< end");
        } finally {
          socket.close();
          System.out.println("closing socket...");
        }
      } finally {
        server.close();
        System.out.println("closing server...");
      }
    }
}
