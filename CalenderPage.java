package Calender;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class CalenderPage extends JFrame {
	DefaultTableModel model;
	Calendar cal = new GregorianCalendar();
	JButton labs[]= new JButton[31];
	JLabel label;
	
	public CalenderPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("2018 달력");
		Container c = getContentPane();
		setSize(1000,300);
		setLayout(new BorderLayout());
		setVisible(true);
		
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		ImageIcon ILeft = new ImageIcon("images/Left.PNG");	//이전 달로 가는 화살표 이미지 가져오기
		ImageIcon IRight = new ImageIcon("images/Right.PNG"); //다음 달로 가는 화살표 이미지 가져오기
		JButton Left = new JButton(ILeft);
		Left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cal.add(Calendar.MONTH, -1);
				updateMonth();
			}
		});
		JButton Right = new JButton(IRight);
		Right.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cal.add(Calendar.MONTH, +1);
				updateMonth();
			}
		});
		JPanel NorthPanel = new JPanel();
		NorthPanel.setLayout(new BorderLayout());
		NorthPanel.add(Left, BorderLayout.WEST);
		NorthPanel.add(label, BorderLayout.CENTER);
		NorthPanel.add(Right, BorderLayout.EAST);
		
		String [] columns = {"일", "월", "화", "수", "목", "금", "토"};
		model = new DefaultTableModel(columns, 0);
		JTable table = new JTable(model);
		JScrollPane pane = new JScrollPane(table);
		
		add(NorthPanel, BorderLayout.NORTH);
		add(pane, BorderLayout.CENTER);
		
		updateMonth();
	}
	void updateMonth() {
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.KOREA);
		int year = cal.get(Calendar.YEAR);
		label.setText(year + "년  " + month);
		
		int startDay = cal.get(Calendar.DAY_OF_WEEK);	//일요일은 1, 토요일은7
		int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //현재 월의 날짜(EX. 31,30,28)
		int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);	//현재 월의 주 수
		
		model.setRowCount(0);
		model.setRowCount(weeks);
		
		int i = startDay-1;
		for(int Start=1; Start<=numberOfDays; Start++) {
			model.setValueAt((labs[Start-1]=new JButton(Integer.toString(Start))).getText(), i/7, i%7);
			i = i+1;
		}
	}
	public static void main(String[] args) {
		new CalenderPage();
		
	}
	
}