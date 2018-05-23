package calender;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Frame extends JFrame{
	/**
	 * 
	 */
	static String ID;
	static String password;
	private static final long serialVersionUID = 1;
	public Frame(){
		setTitle("CalenderEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane1 = getContentPane();
		Container register = getContentPane();
		GridLayout grid = new GridLayout(3, 2);
		grid.setVgap(5);
		contentPane1.setLayout(grid);
		JButton btn_login = new JButton("Login");
		JButton btn_register = new JButton("Register");
		contentPane1.add(new JLabel("ID : "));
		contentPane1.add(new JTextField(""));
		contentPane1.add(new JLabel("Password : "));
		contentPane1.add(new JPasswordField(""));
		contentPane1.add(btn_login);
		btn_login.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				dispose();
				new Calender();
			}
		});
		contentPane1.add(btn_register);
		btn_register.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				new Register();
			
			}
			});
		setSize(400, 200);
		setResizable(true);
		setVisible(true);
	}
	public static void main(String[] args){
		new Frame();
	}
}



