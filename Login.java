package Calender;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame {
	/** 
	* 캘린더 처음 화면 구현 
	* 회원 가입 및 로그인 기능 
	*  
	*/ 

	static String id;


	JTextField txt_id = new JTextField(""); //Id를 저장할 TextField
	JPasswordField txt_password = new JPasswordField("");  //비밀번호를 저장할 TextField
	JButton btn_login = new JButton("로그인"); //로그인 버튼
	JButton btn_register = new JButton("회원가입"); //회원가입 버튼
	
	public Login(){
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Container contentPane1 = getContentPane();
		
		GridLayout grid = new GridLayout(3, 2);
		grid.setVgap(5);
		contentPane1.setLayout(grid);
		JLabel IdLabel = new JLabel("ID : ");	//코드 수정
		IdLabel.setFont(new Font("A", Font.PLAIN, 30)); //코드 수정
		contentPane1.add(IdLabel); //코드 수정
		JLabel PwdLabel = new JLabel("비밀번호 : "); //코드 수정
		PwdLabel.setFont(new Font("A", Font.PLAIN, 30)); //코드 수정
		contentPane1.add(txt_id);
		contentPane1.add(PwdLabel); //코드 수정
		contentPane1.add(txt_password);
		contentPane1.add(btn_login);
		btn_login.addMouseListener(new MouseAdapter(){ //로그인 버튼 눌렀을 때 마우스 리스너 구현
			public void mouseClicked(MouseEvent e){ //클릭되었을 때
				String str_id = txt_id.getText();
				String str_password = new String(txt_password.getPassword());
				try{
					File file = new File("data\\log_in.txt"); //ID와 비밀번호가 저장될 text파일 경로 설정
					BufferedReader in = new BufferedReader(new FileReader(file)); 
					String get_all,get_id, get_pw; //한줄씩 읽어오기 위한 객체와 id, password를 저장하기위한 객체 생성
					int check = 0; //로그인에 실패하였을 때  체크가능한 변수
					while((get_all = in.readLine()) != null){ //더이상 읽을 문장이 없을 때까지 ID와 비밀번호 매칭
						StringTokenizer token = new StringTokenizer(get_all, " "); //읽은 문장에서 띄어쓰기로 구분된 문자들을 
						while(token.hasMoreTokens()){  
							get_id = token.nextToken(); 
							if(str_id.equals(get_id)){
								get_pw = token.nextToken();
								if(str_password.equals(get_pw)){
									JOptionPane.showMessageDialog(null, "로그인!", "로그인", JOptionPane.INFORMATION_MESSAGE); //�α��� ���� Ȯ��â
									id = txt_id.getText();
									PanelManage();
									check = 1;
									dispose(); //로그인 화면 없애기
								}
							}
							
						}
						
					}
					if(check == 0){
						JOptionPane.showMessageDialog(null, "ID 또는 비밀번호가 틀렸습니다.", "로그인 오류", JOptionPane.ERROR_MESSAGE); //�α��� ���� ����â
					}
					in.close();
				}
				
				catch(Exception e1){
					e1.printStackTrace();
				}
				
			}
		
		});
		contentPane1.add(btn_register);
		btn_register.addMouseListener(new MouseAdapter() { //회원가입 버튼 눌렀을 때 마우스 리스너
			public void mouseClicked(MouseEvent e){
				new Register();
			}
		});
		
		setSize(400, 200);
		setResizable(true);
		setVisible(true);
	}
	void PanelManage() throws ParseException { //PanelManage 화면을 구성하는 메소드 
		PanelManage Manager = new PanelManage();
		Manager.setTitle("페이지 관리");
		Manager.Panel1 = new Calender(Manager, id);
		Manager.Panel2 = new AddSchedule(Manager);
		
		
		Manager.add(Manager.Panel1);
		Manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		Manager.setSize(1000,700);
		Manager.setVisible(true);
	}

	public static void main(String[] args){
		new Login();
	}
	
}




