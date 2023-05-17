package Gomoku;

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

  public class OutOfRangeException extends Exception {
    OutOfRangeException(String message) {
      super(message);
    }
  };

  public class PositionOccupiedException extends Exception {
    PositionOccupiedException(String message) {
      super(message);
    }
  };

  
  public int[] validateInput(String x, String y, String stone) throws NumberFormatException, OutOfRangeException, PositionOccupiedException {
    int x_, y_;
    String message = stone + " の入力(" + x + ", " + y + "): ";
    try {
      x_ = Integer.parseInt(x) - 1;
      y_ = Integer.parseInt(y) - 1;
    } catch (NumberFormatException e) {
      throw new NumberFormatException(message + "は数字でなければなりません");
    }
    
    
    if (x_ < 0 || x_ >= BOARD_LENGTH || y_ < 0 || y_ >= BOARD_LENGTH) {
      throw new OutOfRangeException(message + "は1~16の範囲でなければなりません");
    }
    
    if (board.checkExist(x_, y_)) {
      throw new PositionOccupiedException(message + "には既に石が存在しています");
    }
    
    int[] values = {x_, y_};
    return values;
  }
  

  public boolean checkWin(String stone) {
    if (checkLines(stone) | checkColumns(stone) | checkSlash(stone) | checkBackSlash(stone)) {
      // System.out.println("WIN!");
      return true;
    }
    return false;
  }
  public boolean checkFive() {
    if(this.count >= 5) {
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
      // resetCount();
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
