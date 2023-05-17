package Gomoku;

public class Main {
  public static void main(String[] args) {
    Game game = new Game();
    boolean readyFlag = true;
    boolean endFlag = false;
    String client1 = "Client1";
    String client2 = "Client2";
    if (readyFlag) {
      game.start();
      game.play();
    }
    if (endFlag) game.giveup(client1);
    // 終了の挨拶
  }
}
