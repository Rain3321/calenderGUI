package Calender;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddSchedule extends JPanel{
	PanelManage f;
	private JTextArea txt; // 해야할 목록
	private JScrollPane scrollpane;
	private JButton addScheduleBtn; //일정 추가 버튼
	private JButton MoveCalPage;
	private JLabel TitleLabel;
	private JLabel DDayLabel;
	private JTextField TitleText; // 일정 추가시 제목
	private JTextField SetDDay; // 일정 추가시 DDay 설정 (20180601으로 받음)
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	Calendar c1 = Calendar.getInstance();
	String StrToday = formatter.format(new Date());
	Date beginDate = formatter.parse(StrToday);
	Date endDate;
	
	
	
	public AddSchedule(PanelManage f) throws ParseException {
		endDate = formatter.parse("20180605");
		long diff = endDate.getTime() - beginDate.getTime();
		long diffDays = diff /(24 * 60 * 60 * 1000);
		System.out.println(diffDays);
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
		
		TitlePanel.add(TitleLabel = new JLabel("일정 제목: "));
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

