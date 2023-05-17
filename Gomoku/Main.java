package Gomoku;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      Game game = new Game();
      game.start();
      game.play();
    }
    else if(args[0].toLowerCase().equals("friend")) {
      GameServer server = new GameServer();
      server.start();
      server.play();

    }
    else if(args[0].toLowerCase().equals("ai")) {
      System.out.println("AI mode is WIP");
    }
  }
}
