package Gomoku;

public class Game implements Constant {
  Board board = new Board();
  Judge judge = new Judge();
  public void start() {
    board.initBoard();
    board.showBoard();
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
    board.setBoard(7, 8, BLACK_STONE);
    board.setBoard(6, 9, BLACK_STONE);
    board.setBoard(5, 10, BLACK_STONE);
    board.setBoard(4, 11, BLACK_STONE);
    board.setBoard(3, 12, BLACK_STONE);

    board.showBoard();
    while(true) {
      if(judge.checkWin(BLACK_STONE))  {
        break;
      }
    }
  }
  public void giveup(String client) {}

}
