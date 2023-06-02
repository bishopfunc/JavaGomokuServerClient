package Gomoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel titleLabel;
	JButton humanbutton,AIbutton;
	HumanButtonListener humanbuttonListener; 
	AIButtonListener AIbuttonListener;

	TitlePanel(){
		this.setLayout(null);
		this.setBackground(new Color(153,76,0));
	}
	
	public void prepareComponents() {
		
		titleLabel = new JLabel();
		titleLabel.setText("五目並べ");
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD,70));
		titleLabel.setBounds(160,0,400,300);
		this.add(titleLabel);
		
		humanbutton = new JButton("VS Player");
		humanbutton.setFocusable(false);
		humanbutton.setBackground(new Color(153, 255, 153));
		humanbutton.setFont(new Font("ＭＳ ゴシック", Font.ITALIC, 16));
		humanbutton.setContentAreaFilled(true);
		humanbutton.setBounds(200, 220, 200, 100);
		humanbutton.setHorizontalAlignment(JLabel.CENTER);
		humanbutton.setVerticalAlignment(JLabel.CENTER);
		this.add(humanbutton);
		humanbuttonListener = new HumanButtonListener();
		humanbutton.addActionListener(humanbuttonListener);
		
		AIbutton = new JButton("VS AI");
		AIbutton.setFocusable(false);
		AIbutton.setBackground(new Color(153, 255, 153));
		AIbutton.setFont(new Font("ＭＳ ゴシック", Font.ITALIC, 16));
		AIbutton.setContentAreaFilled(true);
		AIbutton.setBounds(200, 420, 200, 100);
		AIbutton.setHorizontalAlignment(JLabel.CENTER);
		AIbutton.setVerticalAlignment(JLabel.CENTER);
		this.add(AIbutton);
		AIbuttonListener = new AIButtonListener();
		AIbutton.addActionListener(AIbuttonListener);
	}
	
	public class HumanButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			JabberClient3.mainWindow.setFrontScreenAndFocus(ScreenMode.GAME);
			JabberClient3.decideVSmode(1);
		}
	}
	public class AIButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			JabberClient3.mainWindow.setFrontScreenAndFocus(ScreenMode.KAIHATU);
			JabberClient3.decideVSmode(1);
		}
	}
}
