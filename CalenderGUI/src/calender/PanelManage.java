package calender;


import javax.swing.*;

public class PanelManage extends JFrame{ //페이지를 관리하는 프레임
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Calender Panel1 = null;
	public AddSchedule Panel2 = null;
	
	
	public void change(String paneName) { //2개의 패널을 바꿔주는 메소드 
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
	
	