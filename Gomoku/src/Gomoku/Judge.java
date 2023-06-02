package Gomoku;

public class Judge implements Constant{
  private static int count = 0;
  public static void addCount() {
    count += 1;
  }
  public static void resetCount() {
    count = 0;
  }  
  
  public int getCount() {
    return count;
  }  

  public static boolean checkWin(int stone) {
    if (checkLines(stone) | checkColumns(stone) | checkSlash(stone) | checkBackSlash(stone)) {
      // System.out.println("WIN!");
      return true;
    }
    return false;
  }
  public static boolean checkFive() {
    if(count >= 5) {
      return true;
    }
    // if(this.count > 0) {
    //   System.out.println(this.count);
    // }
    return false;
  }
  // 横の方向(-)を確認する関数
  public static boolean checkLines(int stone) {
    resetCount(); 
    for (int i = 0; i < BOARD_LENGTH; i++) {
      resetCount();
      for (int j = 0; j < BOARD_LENGTH; j++) {
        if(JabberServer3.board.getBoard(i, j) == stone) {
          addCount();
          if (checkFive()) return true;
        }
        else resetCount();
      }
    }
    return false;
  }
  // 縦の方向(|)を確認する関数
  public static boolean checkColumns(int stone) {
    resetCount();
    for (int j = 0; j < BOARD_LENGTH; j++) {
      resetCount();
      for (int i = 0; i < BOARD_LENGTH; i++) {
        if (JabberServer3.board.getBoard(i, j) == stone) {
          addCount();
          if (checkFive()) return true;
        } 
        else resetCount();
      }
    }
    return false;
  }
  // 斜めの方向(/)を確認する関数
  public static boolean checkSlash(int stone) {
    resetCount();
    for (int i = 0; i <= BOARD_LENGTH - 5; i++) {
      resetCount();
      for (int j = 0; j <= BOARD_LENGTH - 5; j++) {
    	  resetCount();
        for (int k = 0; k < 5; k++) {
          if (JabberServer3.board.getBoard(i+k, j+k) == stone) {
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
  public static boolean checkBackSlash(int stone) {
    resetCount();
    for (int i = BOARD_LENGTH - 1; i >= 4; i--) {
      resetCount();
      for (int j = 0; j <= BOARD_LENGTH - 5; j++) {
    	  resetCount();
        for (int k = 0; k < 5; k++) {
          if (JabberServer3.board.getBoard(i-k, j+k) == stone) {
            addCount();
            if (checkFive()) return true;
          } 
          else resetCount();
        }
      }
    }
    return false;
  }
  
  public static boolean fullBoard() {
	  for (int i=0; i < BOARD_LENGTH; i++) {
		  for (int j=0; j < BOARD_LENGTH; j++) {
			  if(JabberServer3.board.getBoard(i, j)==0) return false;
		  }
	  }
	  return true;
  }
}