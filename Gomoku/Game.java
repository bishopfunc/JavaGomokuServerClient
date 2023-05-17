package Gomoku;

public class Game implements Constant {
  Board board = new Board();
  public void start() {
    board.initBoard();
    board.showBoard();
  }
  public void play() {
    board.setBoard(8, 8, Constant.BLACK_STONE);
    board.showBoard();
    board.setBoard(8, 8, Constant.BLACK_STONE);

  }
  public void giveup(String client) {}

}
