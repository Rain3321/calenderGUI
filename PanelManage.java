package Calender;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

import javax.swing.*;

public class PanelManage extends JFrame{
	
	public Calender Panel1 = null;
	public AddSchedule Panel2 = null;
	
	
	public void change(String paneName) {
		if(paneName.equals("Calender")) {
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
}
	
	
