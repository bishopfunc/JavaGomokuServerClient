package Gomoku;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
public class MainWindow extends JFrame {
	static Board myboard = new Board();
	private static final long serialVersionUID = 1L;

	ScreenMode screenMode = ScreenMode.TITLE;
	//final int WIDTH = 800;
	//final int HEIGHT = 600;
	final int WIDTH = 600;
	final int HEIGHT = 600;
	
	CardLayout layout = new CardLayout();
	TitlePanel titlePanel;
	JLabel winLabel, loseLabel;
	GamePanel gamePanel;
	KaihatuPanel kaihatuPanel;
	public static int count=1;
	static boolean yourturn;
	static boolean nowturn = true;
	public static boolean click=false;

	MainWindow() {
		myboard.initBoard();
		this.setTitle("タイトル設定");
		//ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(""));
		//this.setIconImage(icon.getImage());
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(100,50,100));
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void preparePanels() {
		titlePanel = new TitlePanel();
		this.add(titlePanel, "タイトル画面");
		gamePanel = new GamePanel();
		this.add(gamePanel,"ゲーム画面");
		kaihatuPanel = new KaihatuPanel();
		this.add(kaihatuPanel,"開発画面");
		this.pack();
	}
	
	public void prepareComponents() {
		titlePanel.prepareComponents();
		gamePanel.prepareComponents();
		kaihatuPanel.prepareComponents();
		
	}
	
	public void setturn(boolean turn) {
		yourturn = turn;
	}
	public boolean getClick() {
		return click;
	}
	public void trueClick() {
		click = true;
	}
	public void falseClick() {
		click = false;
	}
	
	public void setFrontScreenAndFocus(ScreenMode s) {
		screenMode = s;
		switch(screenMode) {
		case TITLE:
			layout.show(this.getContentPane(),"タイトル画面");
			titlePanel.requestFocus();
			break;
		case GAME:
			layout.show(this.getContentPane(),"ゲーム画面");
			break;
		case KAIHATU:
			layout.show(this.getContentPane(),"開発画面");
			break;
		}
		
	}
	public void enemyStone(int x, int y, int stone) {
		GamePanel.enemyStone(x,y,stone);
	}
	
	public void displayWin() {
		GamePanel.displayWin();
	}
	
	public void displayLose() {
		GamePanel.displayLose();
		}
	public void displayDraw() {
		GamePanel.displayDraw();
		}
	public static void restart() {
		myboard.initBoard();
		JabberClient3.mainWindow.repaint();
		
	}
}


