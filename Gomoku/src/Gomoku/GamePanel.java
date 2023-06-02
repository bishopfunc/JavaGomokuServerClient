package Gomoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	JLabel gameLabel;
	JButton returnbutton, againbutton;
	ReturnButtonListener returnbuttonListener;
	AgainButtonListener againbuttonListener;
	//JLabel label = new JLabel();
	int mx=160, my=150, Mx=460, My=450, x, y, mystone;
	static int judge =0;
	GamePanel(){
		this.setLayout(null);
		addMouseListener(new MouseProc());
	}
	
	public void prepareComponents() {
		returnbutton = new JButton("終了");
		returnbutton.setFocusable(true);
		returnbutton.setBackground(new Color(153, 255, 153));
		returnbutton.setFont(new Font("ＭＳ ゴシック", Font.ITALIC, 16));
		returnbutton.setContentAreaFilled(true);
		returnbutton.setBounds(50, 523, 50, 50);
		returnbutton.setHorizontalAlignment(JLabel.CENTER);
		returnbutton.setVerticalAlignment(JLabel.CENTER);
		this.add(returnbutton);
		returnbuttonListener = new ReturnButtonListener();
		returnbutton.addActionListener(returnbuttonListener);
		
		againbutton = new JButton("もう一戦");
		againbutton.setFocusable(true);
		againbutton.setBackground(new Color(153, 255, 153));
		againbutton.setFont(new Font("ＭＳ ゴシック", Font.ITALIC, 16));
		againbutton.setContentAreaFilled(true);
		againbutton.setBounds(120, 523, 100, 50);
		againbutton.setHorizontalAlignment(JLabel.CENTER);
		againbutton.setVerticalAlignment(JLabel.CENTER);
		this.add(againbutton);
		againbuttonListener = new AgainButtonListener();
		againbutton.addActionListener(againbuttonListener);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		paintLine(g);
		paintStone(g);
		Font font = new Font("HGP行書体",Font.BOLD,50);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("五目並べ", 220, 100);
		if(judge == 1) {
			g.setColor(Color.red);
			g.drawString("WIN", 270, 300);
		}
		else if(judge == -1) {
			g.setColor(Color.blue);
			g.drawString("LOSE", 255, 300);
		}
		else if(judge == 2) {
			g.setColor(new Color(10,100,70));
			g.drawString("DRAW", 255, 300);
		}
		paintPlayerTag(g);
		paintTurn(g);
	}

	//盤面の線を書いている
	void paintLine(Graphics g) {
		g.setColor(Color.gray);
		//g.fillRect(0, 0, 800, 520);
		g.fillRect(0, 0, 800, 600);
		g.setColor(new Color(210,130,0));
		g.fillRect(mx-10, my-10, 321, 321);
				
		g.setColor(Color.black);
		for(int y=my;y<=My;y+=20) {
			g.drawLine(mx-10, y, Mx+10, y);
		}
		
		for(int x=mx;x<=Mx;x+=20) {
			g.drawLine(x, my-10, x, My+10);
		}
	}
	
	//自分の石を置いている
	void paintStone(Graphics g) {
		for(int i=0;i<16;i++) {
			for(int j=0;j<16;j++) {
				if(MainWindow.myboard.getBoard(i, j) == 1) {
					g.setColor(Color.black);
					g.fillOval(mx+i*20-8, my+j*20-8,16, 16);
				}
				else if(MainWindow.myboard.getBoard(i, j) == 2) {
					g.setColor(Color.white);
					g.fillOval(mx+i*20-8, my+j*20-8,16, 16);
				}
			}
		}
	}
	
	//敵の石を置いている
	public static void enemyStone(int x, int y, int stone){
		int putstone;
		if(stone == 1) putstone=2;
		else putstone=1;
		MainWindow.myboard.addBoard(x, y, putstone);
		JabberClient3.mainWindow.repaint();									
		return;
	}
	
	//勝ち表示
	public static void displayWin() {
		judge++;
		JabberClient3.mainWindow.repaint();
	}
	
	//負け表示
	public static void displayLose() {
		judge--;
		JabberClient3.mainWindow.repaint();
	}
	
	public static void displayDraw() {
		judge = 2;
		JabberClient3.mainWindow.repaint();
	}
	
	public void paintPlayerTag(Graphics g) {
		if(JabberClient3.mystone ==1) {
			Font font = new Font("HGP行書体",Font.BOLD,30);
			Font font2 = new Font("HGP行書体",Font.BOLD,20);
			g.setFont(font);
			if(JabberClient3.mode == 1) {	
				g.setColor(Color.black);
				g.drawString("Player 1", 10, 30);
				g.setFont(font2);
				g.drawString("your stone:", 10, 60);
				g.fillOval(130, 43,20, 20);
			}
			else if(JabberClient3.mode == 2) {	
				g.setColor(Color.black);
				g.drawString("Player", 10, 30);
				g.setFont(font2);
				g.drawString("your stone:", 10, 60);
				g.fillOval(130, 43,20, 20);
			}
			
		}
		else if(JabberClient3.mystone ==2) {
			Font font = new Font("HGP行書体",Font.BOLD,30);
			Font font2 = new Font("HGP行書体",Font.BOLD,20);
			g.setFont(font);
			if(JabberClient3.mode == 1) {	
				g.setColor(Color.black);
				g.drawString("Player 2", 10, 30);
				g.setFont(font2);
				g.drawString("your stone:", 10, 60);
				g.setColor(Color.white);
				g.fillOval(130, 43,20, 20);
			}
			else if(JabberClient3.mode == 2) {	
				g.setColor(Color.black);
				g.drawString("CPU", 10, 30);
				g.setFont(font2);
				g.drawString("your stone:", 10, 60);
				g.setColor(Color.white);
				g.fillOval(130, 43,20, 20);
			}
			
		}
		
	}
	
	void paintTurn(Graphics g) {
		Font font = new Font("HGP行書体",Font.BOLD,30);
		g.setFont(font);
		g.setColor(Color.black);
		if(JabberClient3.b.equals("Your turn")) {
			g.drawString("Your turn", 240, 500);
		}
		else {
			g.drawString("Enemy turn", 230, 500);
		}
		
		
	}
	
	
	//クリック制御
	public class MouseProc extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(JabberClient3.getmyturn()) {
				x = e.getX();
				y = e.getY();
				
				if(x<mx-10 || Mx+10<x) return;
				if(y<my-10 || My+10<y) return;
				for(int i=0;i<=15;i++) {
					if(x<=mx+10+i*20) {
						for(int j=0;j<=15;j++) {				
							if(y<=my+10+j*20) {
								if(MainWindow.myboard.getBoard(i, j) !=0) break;
								JabberClient3.sendcoordinate(i,j);
								JabberClient3.mainWindow.trueClick();
								MainWindow.myboard.addBoard(i, j, JabberClient3.mystone);
								JabberClient3.mainWindow.repaint();	
								return;
							}
						}
						break;
					}
				}
			}
					

			
		}
	}
	
	public class ReturnButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JabberClient3.mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE);
			
		}
	}
	public class AgainButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			judge = 0;
			MainWindow.restart();
		}
	}
}