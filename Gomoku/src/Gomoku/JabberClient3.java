package Gomoku;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class JabberClient3 {
	static MainWindow mainWindow;
	static boolean myturn,restart = false;
	public static String b,judge,again;
	public static int mystone, cx,cy, mode;
	public static int receiveclick = 0, preparesend = 0, setx,sety;
	public static int acceptx=-1, accepty=-1;
	
	public static void judgeturn(String b){
		if(b.equals("Your turn")) {
			myturn = true;
		}
		else if(b.equals("Wait a minute")) {
			myturn = false;
		}
		mainWindow.repaint();
	}
	
	public static boolean getmyturn() {
		return myturn;
	}
	
	public static void sendcoordinate(int i, int j) {
		cx = i;
		cy = j;
		System.out.println("置いた座標: ("+i + ", " +j + ")");
		preparesend = 1;
	}
	
	public static void decideVSmode(int m) {
		mode = m;
		//System.out.println("Mode: "+mode);
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		InetAddress addr = InetAddress.getByName("localhost");
		System.out.println("addr = " + addr);
		int port = args.length > 0 ? Integer.parseInt(args[0]) : 8081;
		Socket socket = new Socket(addr, port);
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("socket: " + socket);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // データ受信用バッファ
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true); // 送信バッファ設定 autoFlush
			
			String s = in.readLine();
			System.out.println(s);
			if(s.equals("Player1")) {
				setx = 100;
				sety = 150;
			}
			else if(s.equals("Player2")) {
				setx = 750;
				sety = 150;
			}
			mainWindow = new MainWindow();
			mainWindow.preparePanels();
			mainWindow.prepareComponents();
			mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE);
			mainWindow.setLocation(setx,sety);
			mainWindow.setturn(myturn);
			mainWindow.setVisible(true);
			
			Thread receiveThread = new ReceiveThread(in);
			receiveThread.start();
			
			Thread.sleep(100);
			while(true) {
				
				if(mode == 1) {			
					Thread.sleep(100);				
					if(b.equals("Your turn")) mystone = 1;
					else if(b.equals("Wait a minute")) mystone = 2;
					judgeturn(b);
					mainWindow.repaint();
					while (true) {
						boolean cl = mainWindow.getClick();
						//System.out.println(mainWindow.getClick());
						if(myturn == false) {
							//System.out.println("falsedayo"+mystone);
							if(acceptx>=0 && accepty>=0) {	
								Thread.sleep(100);
								System.out.println("置かれた座標： ("+acceptx+", "+accepty+")");
								mainWindow.enemyStone(acceptx, accepty, mystone);
								if(judge.equals("LOSE")) {
									mainWindow.displayLose();
									break;
								}
								else if(judge.equals("DRAW")) {
									mainWindow.displayDraw();
									break;
								}
								System.out.println(b);						
								judgeturn(b);
								acceptx = -1;
								accepty = -1;
							}
							
						}
						else {
							//System.out.println("truedayo"+mystone);
							if(cl == true) {
								out.println(String.valueOf(cx));
								out.println(String.valueOf(cy));
								out.println(String.valueOf(mystone));
								mainWindow.falseClick();
								Thread.sleep(100);
								if(judge.equals("WIN")) {
									mainWindow.displayWin();
									break;
								}
								else if(judge.equals("DRAW")) {
									mainWindow.displayDraw();
									judge="AGAIN";
									//break;
								}
								System.out.println(b);
								judgeturn(b);
											
							}
						}
						
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}
					Thread.sleep(100);					 
					break;
					
				}
				else if(mode == 2) {
					/*
					Thread.sleep(100);	
					if(b.equals("Your turn")) mystone = 1;
					else if(b.equals("Wait a minute")) mystone = 2;
					judgeturn(b);
					mainWindow.repaint();
					while (true) {
						boolean cl = mainWindow.getClick();
						//System.out.println(mainWindow.getClick());
						if(myturn == false) {
							
						}
						else {
							if(cl == true) {
		
							}
						}
					}*/
				}
				else Thread.sleep(1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("closing...");
			socket.close();
			sc.close();
		}
		return;
	}
}

class ReceiveThread extends Thread {
  private BufferedReader in;

  public ReceiveThread(BufferedReader in) {
    this.in = in;
  }

  public void run() {
    try {
      while(true) {
    	  if(JabberClient3.mode == 1) {
    		  JabberClient3.b= in.readLine();
    		  JabberClient3.b= in.readLine();	
    		  Thread.sleep(1000);    
              while (true) {  
            	  JabberClient3.acceptx = Integer.parseInt(in.readLine());
          		  JabberClient3.accepty = Integer.parseInt(in.readLine());
          		  JabberClient3.judge = in.readLine();       		
          		  if(JabberClient3.judge.equals("WIN") ||JabberClient3.judge.equals("LOSE") ||JabberClient3.judge.equals("DRAW")) {
          			break;	
          		  }
          		JabberClient3.b = in.readLine();
                JabberClient3.receiveclick ++;
                }
              break;
             }
    	  else if(JabberClient3.mode == 2) {
    		  JabberClient3.b= in.readLine();
    		  JabberClient3.b= in.readLine();  		  
    	  }
    	  
    	  else Thread.sleep(1);
    	  }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}


