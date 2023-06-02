package Gomoku;

public class Board implements Constant{
  private static int [][] position = new int [BOARD_LENGTH][BOARD_LENGTH];
  // 誰がどこに指したかを表示する関数

  // ボードの指定した座標に石を設置する関数
  // ボードに設置するたび、判定処理をする

  // ボードの初期状態にする関数
  public void initBoard() {
    for (int i = 0; i < BOARD_LENGTH; i++) {
      for (int j = 0; j < BOARD_LENGTH; j++) {
        position[i][j] = 0;
      }
    }
    Judge.resetCount(); 
  }
  
  public int getBoard(int i, int j) {
	  return position[i][j];
  }
  
  public void addBoard(int i, int j, int stone) {
	  position[i][j] = stone;
  }
  
}