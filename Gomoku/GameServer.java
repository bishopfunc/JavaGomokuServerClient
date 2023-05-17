package Gomoku;
import java.util.Scanner;
import java.io.*;
import java.net.*;


public class GameServer implements Constant {
  Board board = new Board();
  Judge judge = new Judge();

  private int turn = 1;
  private String x, y;
  private String stone = "";
  Scanner sc = new Scanner(System.in);

  private static PrintWriter client1Out; //C1送信用
  private static PrintWriter client2Out; //C2送信用
  
  // ボードの表示
  public void showGameLog() {
    System.out.println("ターン" + this.turn + ": " + this.stone + " が(" + this.x + "," + this.y + ")に置きました");
    board.showBoard();
  }
  // 順番の表示
  public void showIsYourTurn() {
    System.out.println(getIsYourTurnText());
  }
  public String getIsYourTurnText() {
    return "ターン" + this.turn + ": " + this.stone + " の番です, 石を打つ場所を入力してください";
  }
  // 勝敗の表示
  public void showYouWin() {
    System.out.println(getYouWinText());
  }
  public String getYouWinText() {
    return this.stone + " の勝利です、おめでとございます";
  }
  // 汎用的に処理の共通化
  public void genernalFunc() {
    showIsYourTurn(); //あなたの番ですと表示する
    x = sc.next(); //入力
    y = sc.next();
    try {
      int[] v = this.judge.validateInput(this.x, y, this.stone); //返り値 int x, y
      board.setBoard(v[0], v[1], this.stone); // ボードにおく
      showGameLog(); //どこにおいたかを表示する
      this.turn++; //成功しないとターンが変わらない
    } catch (Exception e) {
      System.out.println(e);
    }
  } 

  // スタート処理 
  public void start() {
    board.initBoard();
    board.showBoard();
  }
  // プレイ
  public void play() throws IOException {
    ServerSocket s = new ServerSocket(8080);
    System.out.println("Started: " + s);    
    try {
      Socket socket = s.accept(); //要求を待つ
      try {
        System.out.println("Connection accepted: " + socket);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //データ受信用バッファ
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); // 送信バッファ設定 autoFlash
        while(true) {
          if(this.turn % 2 == 1) {
            //先手
            this.stone = BLACK_STONE; //GM側で石の切り替え
            showIsYourTurn(); //サーバー側であなたの番ですと表示する
            out.println(""); //クライアント側に空文字を送る
            x = sc.next(); //サーバー側の入力受信
            y = sc.next(); //サーバー側の入力受信
            try {
              int[] v = this.judge.validateInput(x, y, this.stone); //GM側判定
              board.setBoard(v[0], v[1], this.stone); // GM側でボードにセットする
              out.println(board.getBoardText()); //ボードのテキストをクライアントに送信する
              board.showBoard(); //ボードのテキストをサーバー側で表示させる
              this.turn++; //GM側でターンの変更
            } catch (Exception e) {
              System.out.println(e);
            }
            if(judge.checkWin(this.stone)) { //勝敗判定
              showYouWin(); //サーバー側が勝ちだと判定
              out.println(getYouWinText()); //クライアント側に勝った人を伝える
              break; 
            }
          } else {
            //後手              
            this.stone = WHITE_STONE; //GM側で石の切り替え
            out.println(getIsYourTurnText()); //クライアント側にあなたの番ですと伝える
            String x = in.readLine(); //クライアントの入力を受信
            String y = in.readLine(); //クライアントの入力を受信
            try {
              int[] v = this.judge.validateInput(x, y, this.stone); ///GM側判定
              board.setBoard(v[0], v[1], this.stone); // ボードにおく
              out.println(board.getBoardText()); //ボードのテキストをクライアントに送信する
              board.showBoard(); //ボードのテキストをサーバー側で表示させる
              this.turn++; //GM側でターンの変更
            } catch (Exception e) {
              System.out.println(e);
            }
            if(judge.checkWin(this.stone)) { //勝敗判定
              showYouWin(); //サーバー側が勝ちだと判定
              out.println(getYouWinText()); //クライアント側に勝った人を伝える
              break; 
            }
          }
        }
      } finally {
        System.out.println("closing...");
        socket.close();
      }
    } finally {
      s.close();
    }       
  }
  public void giveup(String client) {}
}

class ClientThread extends Thread {

}