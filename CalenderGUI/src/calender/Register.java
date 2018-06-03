package calender;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Register extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton confirm = new JButton("확인");
	JButton cancel = new JButton("취소");
	Container field = getContentPane();
	GridLayout grid = new GridLayout(3, 2);
	JTextField text_id;
	JPasswordField text_password;
	static String fileNm = "data\\log_in.txt"; //회원가입시 ID와 비밀번호가 저장될 text 파일 경로
	public Register(){
		setTitle("회원가입");
		grid.setVgap(5);
		field.setLayout(grid);
		text_id = new JTextField("");
		field.add(new JLabel("ID : "));
		field.add(text_id);
		text_password = new JPasswordField("");
		field.add(new JLabel("비밀번호 : "));
		field.add(text_password);
		field.add(confirm);
		confirm.addMouseListener(new MouseAdapter() { //확인버튼 눌렀을 때 마우스 리스너
			
			public void mouseClicked(MouseEvent e) { 
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				String id = text_id.getText().toString();
				String password = new String(text_password.getPassword());
				
				try{
					File file = new File(fileNm); 
					BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
					BufferedReader in = new BufferedReader(new FileReader(file));
					/*
					 * id와 password를 띄어쓰기로 구분하여 저장하고 데이터 두개가 모두 저장된 후에 줄바꿈
					 * id를 찾았을 때 password를 바로 찾기 위하여 각각의 대응되는 값들만 같은 줄에 저장함
					 */
					String check;
					int standard = 0; //ID 중복검사를 위한 변수
					while((check = in.readLine()) != null){
						StringTokenizer token = new StringTokenizer(check, " ");
						while(token.hasMoreTokens()){
							if(id.equals(token.nextToken())){
								standard = 1;
							}
						}
					}
					if(standard == 1){
						JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.", "회원가입오류", JOptionPane.ERROR_MESSAGE);
						text_id.setText("");
						text_password.setText("");
					}
					else if(standard == 0){
						out.write(id);
						out.write(" ");
						out.write(password);
						out.newLine(); 
						JOptionPane.showMessageDialog(null, "회원가입 성공!", "회원가입", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
					
					out.close();
					in.close();
				}catch (Exception e1){
					e1.printStackTrace();
				}
				
			}
		});
		field.add(cancel);
		cancel.addMouseListener(new MouseAdapter(){ //취소버튼을 눌렀을 때 작동하는 마우스 리스너
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				dispose();
			}
		});
		setSize(300, 150);
		setResizable(true);
		setVisible(true);
	}
}
