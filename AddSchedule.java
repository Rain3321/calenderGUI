package Calender;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddSchedule extends JPanel{
	PanelManage f;
	private JLabel txtLabel;	//해야할 목록 라벨 추가
	private JLabel txtDDayLabel; // DDAY표시 목록 라벨 추가
	private JTextArea txt; // 해야할 목록
	private JTextArea txtDDay;	//DDAY표시 목록
	private JScrollPane scrollpane1; //txt
	private JScrollPane scrollpane2; // txtDDay
	private JButton addScheduleBtn; //일정 추가 버튼
	private JButton MoveCalPage; // 달력 페이지 돌아가기
	private JButton loadScheduleBtn; //일정 로드 버튼
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
	String month;
	String day;
	
	
//	"C:\\Users\\Lee\\data\\";
	String path = "data\\";
	public static void getSetDDay(String StrSetDDay) {
		StrendDate = StrSetDDay;
	}
	
	public AddSchedule(PanelManage f) throws ParseException {
		this.f = f;
		
		path += Login.id;
		File fileNm = new File(path);
		if(!fileNm.exists()){
				fileNm.mkdirs();
		}
		  
		BufferedReader out;
		// 목록 위 라벨 추가
		setLayout(new GridLayout(2, 1));
		setBackground(Color.CYAN);
		JPanel ListText = new JPanel();
		ListText.setLayout(new GridLayout(2, 2));
		txtLabel = new JLabel("해야할 목록");
		txtLabel.setFont(new Font("고딕", Font.BOLD, 30));
		txtDDayLabel = new JLabel("D-DAY 표시 목록");
		txtDDayLabel.setFont(new Font("고딕", Font.BOLD, 30));
		txt = new JTextArea(); 
		txt.setPreferredSize(new Dimension(200,300));
		txtDDay = new JTextArea();
		txtDDay.setPreferredSize(new Dimension(200,300));
		scrollpane1 = new JScrollPane(txt);
		scrollpane2 = new JScrollPane(txtDDay);
		ListText.add(txtDDayLabel);
		ListText.add(txtLabel);
		ListText.add(scrollpane2);
		ListText.add(scrollpane1);
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
				String writetxt = TitleText.getText();
				String writedate = SetDDay.getText();
				String readtxt1, path1;
				getSetDDay(writedate);
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
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
				long diff = endDate.getTime() - StartDate.getTime();
				long diffDays = diff /(24 * 60 * 60 * 1000);
				System.out.println(diffDays);
				try {
					path1 =path +  "\\" + SelectDay + ".txt";
					
					File fileName = new File(path1);
					
					BufferedWriter in = new BufferedWriter(new FileWriter(fileName, true));
					BufferedReader out = new BufferedReader(new FileReader(fileName));
					in.newLine();
					in.write(writetxt);
					in.write(" (~");
					in.write(writedate + ")");
					
					in.flush();
					in.close();
					readtxt1= out.readLine();
					txt.setText(readtxt1);
					while((readtxt1= out.readLine()) != null) {
						txt.setText(txt.getText() + "\n" + readtxt1 );
					}
					
					out.close();
				
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				TitleText.setText("");
				SetDDay.setText("");
				txtDDay.setText(txtDDay.getText() + "\n"+ writetxt + ": D- " + diffDays); 
			}
		});
		MoveCalPage = new JButton("돌아가기");
		MoveCalPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.change("Calender");
				txt.setText("");
			}
		});
		loadScheduleBtn = new JButton("일정 불러오기");
		loadScheduleBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String path1;
				if(SelectCalMonth < 10) {
			    	 Selectedcmonth = "0" + Integer.toString(SelectCalMonth);
				} else
					Selectedcmonth = Integer.toString(SelectCalMonth);
				if(SelectCalDay < 10) {
					Selectedcday = "0" + Integer.toString(SelectCalDay);
				} else
					Selectedcday = Integer.toString(SelectCalDay);
				SelectDay = "2018" + Selectedcmonth + Selectedcday;
				path1 =path+ "\\" + SelectDay + ".txt";
				 
				File fileName = new File(path1);
				String readtxt;
				try {
					BufferedReader out = new BufferedReader(new FileReader(fileName));
					readtxt= out.readLine();
					txt.setText(readtxt);
					while((readtxt= out.readLine()) != null) {
						txt.setText(txt.getText() + "\n" + readtxt);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		addScheduleBtn.setPreferredSize(new Dimension(150, 100));
		MoveCalPage.setPreferredSize(new Dimension(150, 100));
		loadScheduleBtn.setPreferredSize(new Dimension(150, 100));
		BtnPanel.add(addScheduleBtn);
		BtnPanel.add(MoveCalPage);
		BtnPanel.add(loadScheduleBtn);
		AddScheduleP.add(TitlePanel);
		AddScheduleP.add(DDayPanel);
		AddScheduleP.add(BtnPanel);
		add(AddScheduleP);
	}
}


