package Gomoku;
import java.util.Scanner;

import javax.xml.namespace.QName;

public class Game implements Constant {
  Board board = new Board();
  Judge judge = new Judge();

  private int turn = 0;
  private int x = 0, y = 0;
  private String stone = "";
  
  public void showGameLog() {
    System.out.println("ターン" + this.turn + ": " + this.stone + " が(" + this.x + "," + this.y + ")に置きました");
    board.showBoard();
    System.out.println("\n");
  }
  public void start() {
    board.initBoard();
    board.showBoard();
    System.out.println("\n");
  }
  public void play() {
    // 横チェック
    // board.setBoard(8, 8, BLACK_STONE);
    // board.setBoard(8, 9, BLACK_STONE);
    // board.setBoard(8, 10, BLACK_STONE);
    // board.setBoard(8, 11, BLACK_STONE);
    // board.setBoard(8, 12, BLACK_STONE);

    // 縦チェック
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

    Scanner sc = new Scanner(System.in);
    while(true) {
      if(turn % 2 == 0) {
        //先手
        stone = BLACK_STONE;
        x = sc.nextInt() - 1;
        y = sc.nextInt() - 1;
        board.setBoard(x, y, stone);
        showGameLog();
      } else {
        //後手
        stone = WHITE_STONE;
        x = sc.nextInt() - 1;
        y = sc.nextInt() - 1; 
        board.setBoard(x, y, stone);
        showGameLog();
      }
      if(judge.checkWin(BLACK_STONE))  {
        System.out.println(BLACK_STONE + " WIN!");
        break;
      } else if (judge.checkWin(WHITE_STONE))  {
        System.out.println(WHITE_STONE + " WIN!");
        break;
      } 
      turn++;
    }
  }
  public void giveup(String client) {}

}
