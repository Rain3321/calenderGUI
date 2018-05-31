package Calender;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddSchedule extends JPanel{
	PanelManage f;
	private JTextArea txt; // 해야할 목록
	private JScrollPane scrollpane;
	private JButton addScheduleBtn; //일정 추가 버튼
	private JButton MoveCalPage; // 달력 페이지 돌아가기
	private JLabel TitleLabel; //일정 추가시 제목 라벨
	private JLabel DDayLabel; // DDAY 추가 라벨
	private JTextField TitleText; // 일정 추가시 제목
	private JTextField SetDDay; // 일정 추가시 DDay 설정 (20180601으로 받음)
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	
	
	static int SelectCalMonth;	//선택한 달 가져옴
	static int SelectCalDay;	//선택한 일 가져옴
	String SelectDay;
	String Selectedcmonth ="";	// 선택한 버튼 날짜에서 가져온 달 추가
	String Selectedcday =""; // 선택한 버튼 날짜에서 가져온 일 추가
	static String StrendDate = "";	//DDay로 일정 추가한 텍스트 
	Date StartDate;
	Date endDate;
	String path = "C:\\Users\\yangj\\data.txt";
	File fileNm = new File(path);
	
	public static void getSetDDay(String StrSetDDay) {
		StrendDate = StrSetDDay;
	}
	
	public AddSchedule(PanelManage f) throws ParseException {
		
	    
		
		
		this.f = f;
		setLayout(new GridLayout(2, 1));
		
		JPanel ListText = new JPanel();
		txt = new JTextArea(); 
		txt.setPreferredSize(new Dimension(400, 300));
		txt.setText("할 일 목록 \n 1. 자바 프로젝트 \n 2. 리눅스 과제");
		scrollpane = new JScrollPane(txt);
		ListText.add(scrollpane);
		add(ListText);
		JPanel AddScheduleP = new JPanel();
		AddScheduleP.setLayout(new GridLayout(3, 1));
		JPanel TitlePanel = new JPanel();
		TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel DDayPanel = new JPanel();
		DDayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		TitlePanel.add(TitleLabel = new JLabel("일정 추가 제목: "));
		TitleLabel.setFont(new Font("고딕", Font.PLAIN, 36));
		TitlePanel.add(TitleText = new JTextField());
		TitleText.setPreferredSize(new Dimension(300, 50));
		DDayPanel.add(DDayLabel = new JLabel("DDAY 설정: "));
		DDayLabel.setFont(new Font("고딕", Font.PLAIN, 36));
		DDayPanel.add(SetDDay = new JTextField());
		SetDDay.setPreferredSize(new Dimension(300, 50));
		
		JPanel BtnPanel = new JPanel();
		BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		
		
		addScheduleBtn = new JButton("일정 추가");
		addScheduleBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String txt1 = TitleText.getText();
				String txt2 = SetDDay.getText();
				
				String t;
				getSetDDay(txt2);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				if(SelectCalMonth < 10) {
			    	 Selectedcmonth = "0" + Integer.toString(SelectCalMonth);
			    	 
				} else
					Selectedcmonth = Integer.toString(SelectCalMonth);
				if(SelectCalDay < 10) {
					Selectedcday = "0" + Integer.toString(SelectCalDay);
				} else
					Selectedcday = Integer.toString(SelectCalDay);
				SelectDay = "2018" + Selectedcmonth + Selectedcday;
				try {
					StartDate = formatter.parse(SelectDay);
					
					endDate = formatter.parse(StrendDate);
				} catch (ParseException e3) {
					e3.printStackTrace();
				}
				
				long diff = endDate.getTime() - StartDate.getTime();
				long diffDays = diff /(24 * 60 * 60 * 1000);
				System.out.println(SelectCalMonth);
				System.out.println(SelectCalDay);
				
				System.out.println(diffDays);
				
				try {
					BufferedWriter in = new BufferedWriter(new FileWriter(fileNm, true));
					BufferedReader out = new BufferedReader(new FileReader(fileNm));
					in.newLine();
					in.write(txt1);
					
					in.flush();
					in.close();
					
					while((t = out.readLine()) != null) {
						txt.setText(txt.getText() + "\n");
					}
				
				} catch (IOException e1) {
			
					e1.printStackTrace();
				}
			}
		});
		
		MoveCalPage = new JButton("돌아가기");
		MoveCalPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.change("Cal");
			}
		});
		BtnPanel.add(addScheduleBtn);
		BtnPanel.add(MoveCalPage);
		AddScheduleP.add(TitlePanel);
		AddScheduleP.add(DDayPanel);
		AddScheduleP.add(BtnPanel);
		add(AddScheduleP);
	}
	
	
	public static void main(String[] args) {
		
	}
}


