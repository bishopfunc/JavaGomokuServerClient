package Gomoku;
import java.util.stream.IntStream; 


public class Board implements Constant{
  private static String [][] position = new String [BOARD_LENGTH][BOARD_LENGTH];

  // ボードの指定した座標に石を設置する関数
  public void setBoard(int x, int y, String stone) {
    if(checkExist(x, y)) {
      position[x][y] = stone;
    }
  }
  public boolean checkExist(int x, int y) {
    if(position[x][y].equals(BLACK_STONE)) {
      System.out.printf("(%d, %d)にはすでに%s が存在します", x, y, BLACK_STONE); //AIに送信
      return false;
    } else if(position[x][y].equals(WHITE_STONE)) {
      System.out.printf("(%d, %d)にはすでに%s が存在します", x, y, WHITE_STONE); //AIに送信
      return false;
    } 
    return true;
  }
  // ボードの初期状態にする関数
  public void initBoard() {
    for (int i = 0; i < BOARD_LENGTH; i++) {
      for (int j = 0; j < BOARD_LENGTH; j++) {
        position[i][j] = Constant.EMPTY_STONE;
      }
    }
  }
  public void showBoard() {
    System.out.printf("  ");
    IntStream.range(1, BOARD_LENGTH + 1).forEachOrdered(n -> {
      System.out.printf("%2d", n);
    });
    System.out.println();
    for (int i = 0; i < BOARD_LENGTH; i++) {
      System.out.printf("%2d", i + 1);
      for (int j = 0; j < BOARD_LENGTH; j++) {
        System.out.printf("%s ", position[i][j]);
      }
      System.out.println();
    }
    System.out.println("\n\n");
  }
}
