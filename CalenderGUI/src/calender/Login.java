package calender;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

	private static final long serialVersionUID = 1;
	
	public Login(){
		setTitle("CalenderEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane1 = getContentPane();
//		Container register = getContentPane();
		GridLayout grid = new GridLayout(3, 2);
		grid.setVgap(5);
		contentPane1.setLayout(grid);
		JButton btn_login = new JButton("Login");
		JButton btn_register = new JButton("Register");
		JTextField txt_id = new JTextField("");
		JPasswordField txt_password = new JPasswordField("");
		contentPane1.add(new JLabel("ID : "));
		contentPane1.add(txt_id);
		contentPane1.add(new JLabel("password : "));
		contentPane1.add(txt_password);
		contentPane1.add(btn_login);
		btn_login.addMouseListener(new MouseAdapter(){ //로그인 버튼 눌렀을 때 마우스 리스너 구현
			public void mouseClicked(MouseEvent e){ //클릭되었을 때
				String str_id = txt_id.getText();
				String str_password = new String(txt_password.getPassword());
				try{
					File file = new File("C:\\Users\\Lee\\Desktop\\Rain\\data_file.txt"); //문서에서는  id 와 password가 띄어쓰기로 저장되어있음
					BufferedReader in = new BufferedReader(new FileReader(file)); 
					String get_all,get_id, get_pw; //한줄씩 읽어오기 위한 객체와 id, password를 저장하기위한 객체 생성
					int check = 0; //로그인에 실패하였을 때  체크가능한 변수
					while((get_all = in.readLine()) != null){ //더이상 읽을 문장이 없을 때까지
						StringTokenizer token = new StringTokenizer(get_all, " "); //읽은 문장에서 띄어쓰기로 구분된 문자들을 각각 토큰으로 저장
						while(token.hasMoreTokens()){ //토큰이 없어질때 까지
							get_id = token.nextToken(); 
							if(str_id.equals(get_id)){
								get_pw = token.nextToken();
								if(str_password.equals(get_pw)){
									JOptionPane.showMessageDialog(null, "로그인!", "로그인", JOptionPane.INFORMATION_MESSAGE);
									new Calender(str_id);
									check = 1;
									dispose(); //로그인 화면 없애기
								}
							}
							
						}
						
					}
					if(check == 0){
						JOptionPane.showMessageDialog(null, "비밀번호또는 ID가 틀렸습니다.", "로그인오류", JOptionPane.ERROR_MESSAGE);
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

	public static void main(String[] args){
		new Login();
	}
	
}



