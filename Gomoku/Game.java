package Gomoku;
import java.util.Scanner;

public class Game implements Constant {
  Board board = new Board();
  Judge judge = new Judge();

  private int turn = 1;
  private String x, y;
  private String stone = "";
  Scanner sc = new Scanner(System.in);

  // ボードの表示
  public void showGameLog() {
    System.out.println("ターン" + this.turn + ": " + this.stone + " が(" + this.x + "," + this.y + ")に置きました");
    board.showBoard();
    System.out.println();
  }
  // 順番の表示
  public void showIsYourTurn() {
    System.out.println("ターン" + this.turn + ": " + this.stone + " の番です, 石を打つ場所を入力してください");
  }
  // 勝敗の表示
  public void showYouWin() {
    System.out.println(this.stone + " の勝利です、おめでとございます");
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
    System.out.println("\n");
  }
  // プレイ
  public void play() {
    // 横チェック
    // board.setBoard(8, 8, BLACK_STONE);
    // board.setBoard(8, 9, BLACK_STONE);
    // board.setBoard(8, 10, BLACK_STONE);
    // board.setBoard(8, 11, BLACK_STONE);
    // board.setBoard(8, 12, BLACK_STONE);

    // 斜めチェック
    // board.setBoard(8, 8, BLACK_STONE);
    // board.setBoard(9, 9, BLACK_STONE);
    // board.setBoard(10, 10, BLACK_STONE);
    // board.setBoard(11, 11, BLACK_STONE);
    // board.setBoard(12, 12, BLACK_STONE);

    // 斜めチェック2
    // board.setBoard(9, 8, BLACK_STONE);
    // board.setBoard(10, 9, BLACK_STONE);
    // board.setBoard(11, 10, BLACK_STONE);
    // board.setBoard(12, 11, BLACK_STONE);
    // board.setBoard(13, 12, BLACK_STONE);

    // 斜めチェック3
    // board.setBoard(7, 8, BLACK_STONE);
    // board.setBoard(6, 9, BLACK_STONE);
    // board.setBoard(5, 10, BLACK_STONE);
    // board.setBoard(4, 11, BLACK_STONE);
    // board.setBoard(3, 12, BLACK_STONE);

    while(true) {
      if(this.turn % 2 == 1) {
        //先手
        this.stone = BLACK_STONE;
        genernalFunc();
        if(judge.checkWin(this.stone)) { //勝敗判定
          showYouWin();
          break; 
        }
      } else {
        //後手
        this.stone = WHITE_STONE;
        genernalFunc();
        if(judge.checkWin(this.stone)) { //勝敗判定
          showYouWin();
          break; 
        }
      }
    }
  }
  public void giveup(String client) {}

}
