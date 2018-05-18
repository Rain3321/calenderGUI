package calender;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2;
	public Frame(){
		setTitle("CalenderEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane1 = getContentPane();
		
		GridLayout grid = new GridLayout(3, 3);
		grid.setVgap(5);
		contentPane1.setLayout(grid);
		JButton btn_login = new JButton("Login");
		contentPane1.add(new JLabel("ID : "));
		contentPane1.add(new JTextField(""));
		contentPane1.add(new JLabel("Password : "));
		contentPane1.add(new JPasswordField(""));
		contentPane1.add(btn_login);
		btn_login.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				contentPane1.setVisible(false);
			}
		});
		contentPane1.add(new JButton("Register"));
		setSize(300, 150);
		setResizable(true);
		setVisible(true);
	}
	public static void main(String[] args){
		new Frame();
	}
}



