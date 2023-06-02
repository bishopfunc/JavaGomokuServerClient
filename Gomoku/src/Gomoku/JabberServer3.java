package Gomoku;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JabberServer3{
  private static PrintWriter client1Out;
  private static PrintWriter client2Out;
  static Board board;
  

  
  
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8081);
    System.out.println("Server started: " + serverSocket);
    board = new Board();
    board.initBoard(); 
    try {
    	Socket client1Socket = serverSocket.accept();
    	System.out.println("Client1 connected: " + client1Socket);
    	BufferedReader client1In = new BufferedReader(new InputStreamReader(client1Socket.getInputStream()));
	    client1Out = new PrintWriter(client1Socket.getOutputStream(), true);
	    client1Out.println("Player1");
	    Socket client2Socket = serverSocket.accept();
	    System.out.println("Client2 connected: " + client2Socket);
	    BufferedReader client2In = new BufferedReader(new InputStreamReader(client2Socket.getInputStream()));
	    client2Out = new PrintWriter(client2Socket.getOutputStream(), true);
	    client2Out.println("Player2");
	    Thread client1Thread = new ClientThread(client1In, client1Out, client2Out);
	    Thread client2Thread = new ClientThread(client2In, client1Out, client2Out);
	
	    client1Thread.start();
	    client2Thread.start();
	
	    client1Thread.join();
	    client2Thread.join();
	    } catch (InterruptedException e) {
	    	e.printStackTrace();
	    	} finally {
	    		serverSocket.close();
	    		}
    }
}

class ClientThread extends Thread {
	private BufferedReader in;
	private PrintWriter out1;
	private PrintWriter out2;
	public static boolean nowturn = true;
	boolean aaa = true;


	public ClientThread(BufferedReader in, PrintWriter out1, PrintWriter out2) {
		this.in = in;
		this.out1 = out1;
		this.out2 = out2;
    
	}
	private static String s1 ="Your turn";
	private static String s2 ="Wait a minute";
	private static int turncount=1;
  
	public static void judgeturn() {
		if(turncount % 2 == 1) {
			s1 = "Wait a minute";
			s2 = "Your turn";
		}
		else {
			s2 = "Wait a minute";
			s1 = "Your turn";	  
		}
		turncount++;
	}
  
	public void run() {
		try {
			out1.println(s1);
			out2.println(s2);
			int cx,cy,stone;
			while (aaa) {
				cx = Integer.parseInt(in.readLine());
				cy = Integer.parseInt(in.readLine());
				stone = Integer.parseInt(in.readLine());	
				JabberServer3.board.addBoard(cx, cy, stone);
				if(s1.equals("Wait a minute")) {
					System.out.println("Player1にPlayer2が置いた座標を送ります");
					System.out.println("置いた座標: ("+cx+", "+cy+") 色:白");
					out1.println(String.valueOf(cx));
					out1.println(String.valueOf(cy));
					out2.println("-1");
					out2.println("-1");
				}
				else if (s2.equals("Wait a minute")){
					System.out.println("Player2にPlayer1が置いた座標を送ります");
					System.out.println("置いた座標: ("+cx+", "+cy+") 色:黒");
					out2.println(String.valueOf(cx));	
					out2.println(String.valueOf(cy));
					out1.println("-1");	
					out1.println("-1");
				}
				if(Judge.checkWin(stone)) {
        	 
					System.out.println("Game Finish"); 
					JabberServer3.board.initBoard();
					if(stone == 1) {
						out1.println("WIN");
						out2.println("LOSE");
					}
					else {
						out1.println("LOSE");
						out2.println("WIN");
					}
					aaa = false;
					Thread.sleep(10);
				}
				else if (Judge.fullBoard()){
					out1.println("DRAW");
					out2.println("DRAW");
					JabberServer3.board.initBoard();
					aaa = false;
				}
				else {
					System.out.println("Game Continue"); 
					out1.println("Game Continue");
					out2.println("Game Continue");
				}
				Thread.sleep(10);
				judgeturn();
				out1.println(s1);
				out2.println(s2);
			}
		} catch (IOException | InterruptedException e) {
      //e.printStackTrace();
      //System.out.println("error");
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			out1.close();
			out2.close();
		}
	}
  
  
}

