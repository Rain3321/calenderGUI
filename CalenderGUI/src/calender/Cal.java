package Calender;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Cal extends JPanel {
  
	protected int yy = 2018;
	protected int mm, dd;
	protected JButton labs[][]; 	//일 표시되는 버튼
	protected int leadGap = 0;		//월이 시작할 때 비어있는 버튼 개수
	private JButton b0;	//clear시 b0을 이용해 리셋

	static Calendar calendar = new GregorianCalendar();
	final static int thisYear = calendar.get(Calendar.YEAR);
	final static int thisMonth = calendar.get(Calendar.MONTH);
	final static int thisDay = calendar.get(Calendar.DATE);
	
	
	JLabel UserName = new JLabel("유저 1");	//유저 이름 표시
	
	
	JLabel selectYearMonth = new JLabel(); //현재 년도 및 월 표시
	ImageIcon ILeft = new ImageIcon("images/Left.PNG");	//이전 달로 가는 화살표 이미지 가져오기
	ImageIcon IRight = new ImageIcon("images/Right.PNG"); //다음 달로 가는 화살표 이미지 가져오기
	JButton Left;	//이전 달로 가는 화살표
	JButton Right;	//다음 달로 가는 화살표
	
	PanelManage f;
	
	
	JPanel Pop;	//상단 유저네임, 월 이동 버튼, 일정추가 버튼, 현재 날짜 가는 버튼
	JPanel NowDayBtnPanel = new JPanel();
	

	Cal(PanelManage f) {
		super();
		this.f = f;
		setLayout(new BorderLayout());
		Pop = new JPanel();
		JButton NowDay = new JButton("현재 날짜 이동");
		NowDay.setFont(new Font("고딕", Font.ITALIC, 13));
		NowDay.setBackground(Color.CYAN);
		NowDay.setPreferredSize(new Dimension(130,50));
		NowDayBtnPanel.add(NowDay);
		Pop.setLayout(new GridLayout(1,3));
		Pop.add(UserName);
		// 현재 날짜로 이동하는 이벤트 추가
		NowDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mm = thisMonth;
				setDayActive(thisDay);
				recompute();
			}
		});
		
		setYYMMDD(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));	//날짜 설정
		buildGUI();	//GUI 구현
		Pop.add(NowDayBtnPanel);
		add(BorderLayout.NORTH, Pop);
		recompute();	//이벤트나 설정에 따라 표시되는 년,월,일 변경
		
	}
	
  
	private void setYYMMDD(int year, int month, int today) {
		yy = year;
		mm = month;
		dd = today;
	}

	String[] months = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"};
      
private void buildGUI() {		//GUI 구현
 
    JPanel tp = new JPanel(); //패널 tp에 왼쪽, 오른쪽 버튼 부착 및 현재 년,월 표시 라벨 부착
    tp.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
    tp.add(Left = new JButton(ILeft));
    Left.setPreferredSize(new Dimension(50, 50));
    tp.add(selectYearMonth);
    tp.add(Right = new JButton(IRight));
    Right.setPreferredSize(new Dimension(50, 50));
    Left.addActionListener(new ActionListener() {	//이전 달로 가는 버튼 이벤트
    	public void actionPerformed(ActionEvent e) {
    		if(mm>=1)
    			mm--;
    		recompute();
    	}
    });
    
    Right.addActionListener(new ActionListener() {	//다음 달로 가는 버튼 이벤트
      public void actionPerformed(ActionEvent e) {
    	  if(mm<11)
    		  mm++;
    	  recompute();
      }
    });

    Pop.add(tp);

    JPanel bp = new JPanel();	// Day 버튼이 부착되는 패널 bp
    bp.setLayout(new GridLayout(7, 7));
    labs = new JButton[6][7]; 

    bp.add(b0 = new JButton("일"));
    bp.add(new JButton("월"));
    bp.add(new JButton("화"));
    bp.add(new JButton("수"));
    bp.add(new JButton("목"));
    bp.add(new JButton("금"));
    bp.add(new JButton("토"));

    ActionListener dateSetter = new ActionListener() { //Day버튼을 눌렀을 시 발생하는 이벤트
      public void actionPerformed(ActionEvent e) {
        String num = e.getActionCommand();
        if (!num.equals("")) {
          setDayActive(Integer.parseInt(num));
          f.change("AddSchedule");	//버튼 눌렀을 시 스케줄 추가 패널로 이동
        }
      }
    };

    for (int i = 0; i < 6; i++)
      for (int j = 0; j < 7; j++) {
        bp.add(labs[i][j] = new JButton(""));
        labs[i][j].addActionListener(dateSetter);	//Day버튼 리스너 추가.
      }

    add(BorderLayout.CENTER, bp);
 }

  public final static int dom[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  protected void recompute() {
	  selectYearMonth.setText(Integer.toString(yy) + "년 " + Integer.toString(mm+1) + "월");
	  if (mm < 0 || mm > 11)
		  throw new IllegalArgumentException("mm값  " + mm + "는 0-11 사이");
	  clearDayActive();
	  calendar = new GregorianCalendar(yy, mm, dd);
	  
	  leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK) - 1; //일요일은 1, 토요일 7
	  System.out.println("leadGap = " + leadGap);

	  int daysInMonth = dom[mm];
	  // 첫째 주 공백 버튼
	  for (int i = 0; i < leadGap; i++) {
		  labs[0][i].setText("");
	  }
	  // Day버튼 1일부터 추가
	  for (int i = 1; i <= daysInMonth; i++) {
		  JButton b = labs[(leadGap + i - 1) / 7][(leadGap + i - 1) % 7];
		  b.setText(Integer.toString(i));
	  }
	  // 마지막 주 공백 버튼
	  for (int i = leadGap + 1 + daysInMonth; i < 6 * 7; i++) {
		  labs[(i) / 7][(i) % 7].setText("");
	  }
	  
	  if (thisYear == yy && mm == thisMonth)
		  setDayActive(dd);
	  repaint();
  }

  	/*
  	public boolean isLeap(int year) {
  		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
  			return true;
  		return false;
  	}*/


  //Day 버튼 클리어
  private void clearDayActive() {
	  JButton b;
	  if (activeDay > 0) {
		  b = labs[(leadGap + activeDay - 1) / 7][(leadGap + activeDay - 1) % 7];
		  b.setBackground(b0.getBackground());
		  b.repaint(); //갱신
		  activeDay = -1;
	  }
  }

  private int activeDay = -1;

  //Day 버튼 눌렀을 시 배경을 빨간색으로 변경하는 이벤트 처리 함수
  public void setDayActive(int newDay) {
	  clearDayActive();
	  if (newDay <= 0)
		  dd = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
	  else
		  dd = newDay;
	  Component square = labs[(leadGap + newDay - 1) / 7][(leadGap + newDay - 1) % 7]; 
	  square.setBackground(Color.red);
	  square.repaint(); //갱신
	  activeDay = newDay;
  }
 

  
  public static void main(String[] args) {
    /*JFrame f = new JFrame("Cal");
    Container c = f.getContentPane();
    c.setLayout(new BorderLayout());

    c.add(new Cal());

    f.pack();
    f.setVisible(true);*/
	  
    
  }
}
