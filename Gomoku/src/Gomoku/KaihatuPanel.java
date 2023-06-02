package Gomoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KaihatuPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	JLabel titleLabel;
	JButton button;
	ButtonListener buttonListener; 

	KaihatuPanel(){
		this.setLayout(null);
		this.setBackground(new Color(0,255,0));
	}
	
	public void prepareComponents() {
		
		titleLabel = new JLabel();
		titleLabel.setText("開発中");
		titleLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD,70));
		titleLabel.setBounds(200,0,400,300);
		this.add(titleLabel);
		
		button = new JButton("戻る");
		button.setFocusable(false);
		button.setBackground(new Color(153, 255, 153));
		button.setFont(new Font("ＭＳ ゴシック", Font.ITALIC, 16));
		button.setContentAreaFilled(true);
		button.setBounds(200, 320, 200, 100);
		button.setHorizontalAlignment(JLabel.CENTER);
		button.setVerticalAlignment(JLabel.CENTER);
		this.add(button);
		buttonListener = new ButtonListener();
		button.addActionListener(buttonListener);
		

	}
	
	public class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			JabberClient3.mainWindow.setFrontScreenAndFocus(ScreenMode.TITLE);
		}
	}

}
