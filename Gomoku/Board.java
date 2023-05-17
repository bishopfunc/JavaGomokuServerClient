package Gomoku;
import java.util.stream.IntStream; 


public class Board implements Constant{
  private static String [][] position = new String [BOARD_LENGTH][BOARD_LENGTH];
  // 誰がどこに指したかを表示する関数

  // ボードの指定した座標に石を設置する関数
  // ボードに設置するたび、判定処理をする
  public void setBoard(int x, int y, String stone) {
    if(!checkExist(x, y)) {
      position[x][y] = stone;
    }
  }
  // 指定した座標の石の種類を確認する
  public boolean checkStone(int x, int y, String stone) {
    if(position[x][y].equals(stone)) return true;
    return false;
  }
  
  // ボードの指定した座標に石が存在するか
  public boolean checkExist(int x, int y) {
    if(checkStone(x, y, BLACK_STONE)) {
      return true;
    } else if(checkStone(x, y, WHITE_STONE)) {
      return true;
    } 
    return false;
  }


  // ボードの初期状態にする関数
  public void initBoard() {
    for (int i = 0; i < BOARD_LENGTH; i++) {
      for (int j = 0; j < BOARD_LENGTH; j++) {
        position[i][j] = Constant.EMPTY_STONE;
      }
    }
  }
  public String getBoardText() {
    StringBuilder sb = new StringBuilder();
    
    sb.append("  ");
    IntStream.range(1, BOARD_LENGTH + 1).forEachOrdered(n -> {
      sb.append(String.format("%2d", n));
    });
    sb.append("\n");
    
    for (int i = 0; i < BOARD_LENGTH; i++) {
      sb.append(String.format("%2d", i + 1));
      for (int j = 0; j < BOARD_LENGTH; j++) {
        sb.append(String.format("%s ", position[i][j]));
      }
      sb.append("\n");
    }
    
    return sb.toString();
  }
  public void showBoard() {
    System.out.println(getBoardText());
  }
}
