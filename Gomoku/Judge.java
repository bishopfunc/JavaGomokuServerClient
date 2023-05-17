package Gomoku;

import java.rmi.server.RemoteStub;

public class Judge implements Constant {
  private int count = 0;
  public void addCount() {
    this.count += 1;
  }
  public void resetCount() {
    this.count = 0;
  }  
  public int getCount() {
    return this.count;
  }  
  Board board = new Board();

  public boolean checkWin(String stone) {
    if (checkLines(stone) || checkColumns(stone) || checkSlash(stone) || checkBackSlash(stone)) {
      System.out.println("WIN!");
      return true;
    }
    return false;
  }
  public boolean checkFive() {
    if(this.count == 5) {
      return true;
    }
    // if(this.count > 0) {
    //   System.out.println(this.count);
    // }
    return false;
  }
  // 横の方向(-)を確認する関数
  public boolean checkLines(String stone) {
    resetCount(); 
    for (int i = 0; i < BOARD_LENGTH; i++) {
      resetCount();
      for (int j = 0; j < BOARD_LENGTH; j++) {
        if(board.checkStone(i, j, stone)) {
          addCount();
          if (checkFive()) return true;
        }
        else resetCount();
      }
    }
    return false;
  }
  // 縦の方向(|)を確認する関数
  public boolean checkColumns(String stone) {
    resetCount();
    for (int j = 0; j < BOARD_LENGTH; j++) {
      resetCount();
      for (int i = 0; i < BOARD_LENGTH; i++) {
        if (board.checkStone(i, j, stone)) {
          addCount();
          if (checkFive()) return true;
        } 
        else resetCount();
      }
    }
    return false;
  }
  // 斜めの方向(/)を確認する関数
  public boolean checkSlash(String stone) {
    resetCount();
    for (int i = 0; i <= BOARD_LENGTH - 5; i++) {
      resetCount();
      for (int j = 0; j <= BOARD_LENGTH - 5; j++) {
        for (int k = 0; k < 5; k++) {
          if (board.checkStone(i + k, j + k, stone)) {
            addCount();
            if (checkFive()) return true;
          } 
          else resetCount();
        }
      }
    }
    return false;
  } 
  // 斜めの方向(\)を確認する関数
  public boolean checkBackSlash(String stone) {
    resetCount();
    for (int i = BOARD_LENGTH - 1; i >= 4; i--) {
      resetCount();
      for (int j = 0; j <= BOARD_LENGTH - 5; j++) {
        for (int k = 0; k < 5; k++) {
          if (board.checkStone(i - k, j + k, stone)) {
            addCount();
            if (checkFive()) return true;
          } 
          else resetCount();
        }
      }
    }
    return false;
  }
}
