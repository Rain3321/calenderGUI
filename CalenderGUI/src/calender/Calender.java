package calender;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Calender extends JPanel {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 3;
	protected int yy = 2018;
	protected int mm, dd;
	protected JButton labs[][]; 	//�� ǥ�õǴ� ��ư
	protected int leadGap = 0;		//���� ������ �� ����ִ� ��ư ����
	private JButton b0;	//clear�� b0�� �̿��� ����

	Calendar calendar = new GregorianCalendar();
	protected final int thisYear = calendar.get(Calendar.YEAR);
	protected final int thisMonth = calendar.get(Calendar.MONTH);
	
	JLabel selectYearMonth = new JLabel();
	ImageIcon ILeft = new ImageIcon("images/Left.PNG");	//���� �޷� ���� ȭ��ǥ �̹��� ��������
	ImageIcon IRight = new ImageIcon("images/Right.PNG"); //���� �޷� ���� ȭ��ǥ �̹��� ��������
	JButton Left;	//���� �޷� ���� ȭ��ǥ
	JButton Right;	//���� �޷� ���� ȭ��ǥ
	JLabel User = new JLabel();
	
	JFrame f = new JFrame();
	Container c = f.getContentPane();
	

	Calender() {
		super();
		c.setLayout(new BorderLayout());
		setYYMMDD(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));	//��¥ ����
		buildGUI();	//GUI ����
		recompute();	//�̺�Ʈ�� ������ ���� ǥ�õǴ� ��,��,�� ����
		f.pack();
		f.setVisible(true);
	}
	Calender(String id){
		this();
		User.setText(id);
	}
	private void setYYMMDD(int year, int month, int today) {
		yy = year;
		mm = month;
		dd = today;
	}

	String[] months = { "1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��", "11��", "12��"};
      
private void buildGUI() {		//GUI ����
    getAccessibleContext().setAccessibleDescription("");
    setBorder(BorderFactory.createEtchedBorder());

    setLayout(new BorderLayout());

    JPanel tp = new JPanel(); //�г� tp�� ����, ������ ��ư ���� �� ���� ��,�� ǥ�� �� ����
    tp.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
    tp.add(User);

    tp.add(Left = new JButton(ILeft));
    Left.setPreferredSize(new Dimension(50, 50));
    tp.add(selectYearMonth);
    tp.add(Right = new JButton(IRight));
    Right.setPreferredSize(new Dimension(50, 50));
    Left.addActionListener(new ActionListener() {	//���� �޷� ���� ��ư �̺�Ʈ
    	public void actionPerformed(ActionEvent e) {
    		if(mm>=1)
    			mm--;
    		recompute();
    	}
    });
    
    Right.addActionListener(new ActionListener() {	//���� �޷� ���� ��ư �̺�Ʈ
      public void actionPerformed(ActionEvent e) {
    	  if(mm<11)
    		  mm++;
    	  recompute();
      }
    });

    c.add(BorderLayout.CENTER, tp);

    JPanel bp = new JPanel();	// Day ��ư�� �����Ǵ� �г� bp
    bp.setLayout(new GridLayout(7, 7));
    labs = new JButton[6][7]; 

    bp.add(b0 = new JButton("��"));
    bp.add(new JButton("��"));
    bp.add(new JButton("ȭ"));
    bp.add(new JButton("��"));
    bp.add(new JButton("��"));
    bp.add(new JButton("��"));
    bp.add(new JButton("��"));

    ActionListener dateSetter = new ActionListener() { //Day��ư�� ������ �� �߻��ϴ� �̺�Ʈ
      public void actionPerformed(ActionEvent e) {
        String num = e.getActionCommand();
        if (!num.equals("")) {
          setDayActive(Integer.parseInt(num));
        }
        String id = User.getText();
        new Event(id,Integer.parseInt(num));
      }
    };

    for (int i = 0; i < 6; i++)
      for (int j = 0; j < 7; j++) {
        bp.add(labs[i][j] = new JButton(""));
        labs[i][j].addActionListener(dateSetter);	//Day��ư ������ �߰�.
      }

    c.add(BorderLayout.SOUTH, bp);
 }

  public final static int dom[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  protected void recompute() {
	  selectYearMonth.setText(Integer.toString(yy) + "�� " + Integer.toString(mm+1) + "��");
	  if (mm < 0 || mm > 11)
		  throw new IllegalArgumentException("mm��  " + mm + "�� 0-11 ����");
	  clearDayActive();
	  calendar = new GregorianCalendar(yy, mm, dd);
	  
	  leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK) - 1; //�Ͽ����� 1, ����� 7
	  System.out.println("leadGap = " + leadGap);

	  int daysInMonth = dom[mm];
	  // ù° �� ���� ��ư
	  for (int i = 0; i < leadGap; i++) {
		  labs[0][i].setText("");
	  }
	  // Day��ư 1�Ϻ��� �߰�
	  for (int i = 1; i <= daysInMonth; i++) {
		  JButton b = labs[(leadGap + i - 1) / 7][(leadGap + i - 1) % 7];
		  b.setText(Integer.toString(i));
	  }
	  // ������ �� ���� ��ư
	  for (int i = leadGap + 1 + daysInMonth; i < 6 * 7; i++) {
		  labs[(i) / 7][(i) % 7].setText("");
	  }

	  /*
	  if (thisYear == yy && mm == thisMonth)
		  setDayActive(dd);
	  repaint();*/
  }

  	/*
  	public boolean isLeap(int year) {
  		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
  			return true;
  		return false;
  	}*/


  //Day ��ư Ŭ����
  private void clearDayActive() {
	  JButton b;
	  if (activeDay > 0) {
		  b = labs[(leadGap + activeDay - 1) / 7][(leadGap + activeDay - 1) % 7];
		  b.setBackground(b0.getBackground());
		  b.repaint(); //����
		  activeDay = -1;
	  }
  }

  private int activeDay = -1;

  //Day ��ư ������ �� ����� ���������� �����ϴ� �̺�Ʈ ó�� �Լ�
  public void setDayActive(int newDay) {
	  clearDayActive();
	  if (newDay <= 0)
		  dd = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
	  else
		  dd = newDay;
	  Component square = labs[(leadGap + newDay - 1) / 7][(leadGap + newDay - 1) % 7]; 
	  square.setBackground(Color.red);
	  square.repaint(); //����
	  activeDay = newDay;
}

  
  public static void main(String[] args) {
    /*JFrame f = new JFrame("Cal");
    Container c = f.getContentPane();
    c.setLayout(new BorderLayout());
    c.add(new Cal());
    f.pack();
    f.setVisible(true);*/
	  new Calender();
    
  }
}