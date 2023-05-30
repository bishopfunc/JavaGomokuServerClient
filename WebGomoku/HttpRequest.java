package WebGomoku;
import java.net.*;
import java.io.*;

public class HttpRequest {
  public static final String CRLF = "\r\n";
  private final String headerText;
  private final String bodyText; 
  // コンストラクタ InputStreamを受け取る
  public HttpRequest(InputStream input) {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(input, "UTF-8")); 
      this.headerText = this.readHeader(in);
      this.bodyText = this.readBody(in);
    } catch (IOException e) {
      throw new UncheckedIOException(e); 
    }
  }
  // HTTPHeaderを読み込むprivate関数
  private String readHeader(BufferedReader in) throws IOException {
    String line = in.readLine();
    StringBuffer header = new StringBuffer();
    while (line != null && !line.isEmpty()) { //空文字列になるまで読み込む
      header.append(line + CRLF);
      line = in.readLine();
    }    
    return header.toString();
  } 
  private String readBody(BufferedReader in) throws IOException{
    final int contentLength = this.getContentLength();
    StringBuffer body = new StringBuffer();
    if(contentLength <= 0) return null;
    for (int i = 0; i < contentLength; i++) { //contentLength分読み込む
      int c = in.read();
      if(c == -1) break;
      body.append((char)c);
    }
    return body.toString();
  }  

  // HTTPBodyを読み込むprivate関数
  private int getContentLength() throws IOException {
    int contentLength = 0;
    BufferedReader header = new BufferedReader(new StringReader(this.headerText));
    String line = null;
    while ((line = header.readLine()) != null) {
      if (line.startsWith("Content-Length")) {
        contentLength = Integer.parseInt(line.split(":")[1].trim()); // Content-Length: 14 をパース
      }

    }
    return contentLength;
  }
  // HTTPHeaderを取得する関数
  public String getHeaderText() {
    return this.headerText;
  }
  // HTTPBodyを取得する関数
  public String getBodyText() {
    return this.bodyText;
  }

}
