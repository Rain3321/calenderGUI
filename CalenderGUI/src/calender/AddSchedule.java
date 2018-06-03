package calender;
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
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddSchedule extends JPanel{ 
	/**
	 * PanelManage 프레임에서 관리하는 2번째 패널.
	 * 일정 추가를 위한 패널로 일정을 추가하고 D-day를 계산하는 패널
	 */
	private static final long serialVersionUID = 1L;

	PanelManage f;
	private JLabel txtLabel;	//해야할 목록 라벨 추가
	private JLabel txtDDayLabel; // DDAY표시 목록 라벨 추가
	private JTextArea txt; // 해야할 목록
	private JTextArea txtDDay;	//DDAY표시 목록
	private JScrollPane scrollpane1; //txt의 스크롤팬
	private JScrollPane scrollpane2; // txtDDay의 스크롤 팬0
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
		
		path += Login.id; //파일의 경로에 user의 아이디가 들어가서 구분하기위해
		File fileNm = new File(path);
		if(!fileNm.exists()){ //파일이 없을경우 경로에 없는 폴더 모두 생성
				fileNm.mkdirs();
		}
		  
		
		// 목록 위 라벨 추가 및 textArea 구성
		setLayout(new GridLayout(2, 1));
		setBackground(Color.CYAN);
		JPanel ListText = new JPanel();
		ListText.setLayout(new GridLayout(2, 2));
		txtLabel = new JLabel("해야할 목록");
		txtLabel.setFont(new Font("고딕", Font.BOLD, 30));
		txtDDayLabel = new JLabel("D-day 표시 목록");
		txtDDayLabel.setFont(new Font("고딕", Font.BOLD, 30));
		txt = new JTextArea(); 
		txt.setPreferredSize(new Dimension(200,300));
		txt.setEditable(false);
		txtDDay = new JTextArea();
		txtDDay.setPreferredSize(new Dimension(200,300));
		txtDDay.setEditable(false);
		scrollpane1 = new JScrollPane(txt);
		scrollpane2 = new JScrollPane(txtDDay);
		ListText.add(txtDDayLabel);
		ListText.add(txtLabel);
		ListText.add(scrollpane2);
		ListText.add(scrollpane1);
		add(ListText);
		
		//일정 추가를 위한 패널 구성
		JPanel AddScheduleP = new JPanel();
		AddScheduleP.setLayout(new GridLayout(3, 1));
		JPanel TitlePanel = new JPanel();
		TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel DDayPanel = new JPanel();
		DDayPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		TitlePanel.add(TitleLabel = new JLabel("일정 추가 : "));
		TitleLabel.setFont(new Font("고딕", Font.PLAIN, 36));
		TitlePanel.add(TitleText = new JTextField());
		TitleText.setPreferredSize(new Dimension(300, 50));
		DDayPanel.add(DDayLabel = new JLabel("D-day 설정: "));
		DDayLabel.setFont(new Font("고딕", Font.PLAIN, 36));
		DDayPanel.add(SetDDay = new JTextField());
		SetDDay.setPreferredSize(new Dimension(300, 50));
		JPanel BtnPanel = new JPanel();
		BtnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		addScheduleBtn = new JButton("일정 추가");
		
		addScheduleBtn.addActionListener(new ActionListener() { //일정추가버튼 액션 리스너
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String writetxt = TitleText.getText();
				String writedate = SetDDay.getText();
				String readtxt1, path1;
				getSetDDay(writedate);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				
				//선택된 날짜를 date 형태로 가져오기 때문에 String 으로 YYYYMMDD 형태로 만들어주기 위한 과정
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
					JOptionPane.showMessageDialog(null, "날짜 포맷이 이상합니다.", "D-day 계산", JOptionPane.ERROR_MESSAGE);
					e2.printStackTrace();
				}
				long diff = endDate.getTime() - StartDate.getTime();
				long diffDays = diff /(24 * 60 * 60 * 1000); //현재 날짜와 설정한 날짜까지의 차이 계산
				try {
					path1 =path +  "\\" + SelectDay + ".txt"; //설정한 날짜 이름의 txt 파일을 생성하기 위해 새로운 경로 생성
					
					File fileName = new File(path1);
					
					BufferedWriter in = new BufferedWriter(new FileWriter(fileName, true));
					BufferedReader out = new BufferedReader(new FileReader(fileName));
					if(writetxt.equals("")){ 
						JOptionPane.showMessageDialog(null, "일정을 입력해주세요.", "일정 추가", JOptionPane.ERROR_MESSAGE);
					}
					else{
						in.newLine();
						in.write(writetxt); //일정추가란에 작성한 부분을 파일로 저장
						in.write(" (~");
						in.write(writedate + ")");
						txtDDay.setText(txtDDay.getText() + "\n"+ writetxt + ": D-" + diffDays); 
					}
					
					in.flush();
					in.close();
					readtxt1= out.readLine();
					txt.setText(readtxt1);
					while((readtxt1= out.readLine()) != null) { //생성한 파일을 읽어 txt에 표시
						txt.setText(txt.getText() + "\n" + readtxt1 );
					}
					
					out.close();
				
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				TitleText.setText("");
				SetDDay.setText("");
				
			}
		});
		MoveCalPage = new JButton("돌아가기");
		MoveCalPage.addActionListener(new ActionListener() { //처음 캘런더 화면으로 돌아가기 위한 버튼 리스너
			public void actionPerformed(ActionEvent e) {
				f.change("Calender");
				txt.setText("");
			}
		});
		loadScheduleBtn = new JButton("일정 불러오기");
		loadScheduleBtn.addActionListener(new ActionListener() { //일정을 불러오기 위한 버튼리스너 
			
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
					out.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		addScheduleBtn.setPreferredSize(new Dimension(150, 100));
		MoveCalPage.setPreferredSize(new Dimension(150, 100));
		loadScheduleBtn.setPreferredSize(new Dimension(150, 100));
		BtnPanel.add(loadScheduleBtn);
		BtnPanel.add(addScheduleBtn);
		BtnPanel.add(MoveCalPage);
		AddScheduleP.add(TitlePanel);
		AddScheduleP.add(DDayPanel);
		AddScheduleP.add(BtnPanel);
		add(AddScheduleP);
	}
}


