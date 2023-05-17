import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PlayerClient {
  public static void main(String[] args) throws IOException {
    InetAddress addr = InetAddress.getByName("localhost");
    System.out.println("addr = " + addr);
    Socket socket = new Socket(addr, 8080);
    Scanner sc = new Scanner(System.in);

    try {
      System.out.println("socket: " + socket);
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //データ受信用バッファ
      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); // 送信バッファ設定 autoFlush
      while (true) {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null && !line.isEmpty()) {
          sb.append(line);
          sb.append("\n");
        }
        System.out.println(sb.toString());
        out.println(sc.next()); // 送信
        out.println(sc.next()); // 送信
      }
      // out.println("END");
    } finally {
      System.out.println("closing...");
      socket.close();
    }
  }
}
