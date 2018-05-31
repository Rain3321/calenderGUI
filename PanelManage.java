package Calender;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

import javax.swing.*;

public class PanelManage extends JFrame{
	
	public Cal Panel1 = null;
	public AddSchedule Panel2 = null;
	
	public void change(String paneName) {
		if(paneName.equals("Cal")) {
			getContentPane().removeAll();
			getContentPane().add(Panel1);
			revalidate();
			repaint();
		}else {
			getContentPane().removeAll();
			getContentPane().add(Panel2);
			revalidate();
			repaint();
		}
		
	}
	
	public static void main(String[] args) throws ParseException {
		PanelManage Manager = new PanelManage();
		
		Manager.setTitle("페이지 관리");
		Manager.Panel1 = new Cal(Manager);
		Manager.Panel2 = new AddSchedule(Manager);
		
		Manager.add(Manager.Panel1);
		Manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		Manager.setSize(1000,700);
		Manager.setVisible(true);
	}
}
