package calender;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Register extends JFrame{

	private static final long serialVersionUID = 2;
	public Register(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container field = getContentPane();
//		Container text = getContentPane();
//		JPanel text = new JPanel();
//		text.add(new TextArea("정보를 입력해주세요."));
//		add(text);
		GridLayout grid = new GridLayout(3, 2);
		grid.setVgap(5);
		field.setLayout(grid);
		JButton confirm = new JButton("confirm");
		JButton cancel = new JButton("cancel");
		JTextField text_id = new JTextField("");
		JPasswordField text_password = new JPasswordField("");
		field.add(new JLabel("ID : "));
		field.add(text_id);
		field.add(new JLabel("Password : "));
		field.add(text_password);
		field.add(confirm);
		confirm.addMouseListener(new MouseAdapter() { //확인버튼 눌렀을 때 마우스 리스너
			
			public void mouseClicked(MouseEvent e) { 
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				String id = text_id.getText().toString();
				String password = new String(text_password.getPassword());
				String fileNm = "C:\\Users\\Lee\\Desktop\\Rain\\data_file.txt";
				try{
					File file = new File(fileNm);
//					FileWriter filewrite = new FileWriter(file, true);
					BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
					/*
					 * id와 password를 띄어쓰기로 구분하여 저장하고 데이터 두개가 모두 저장된 후에 줄바꿈
					 * id를 찾았을 때 password를 바로 찾기 위하여 각각의 대응되는 값들만 같은 줄에 저장함
					 */
					out.write(id);
					out.write(" ");
					out.write(password);
					out.newLine(); 
					
					out.close();
				}catch (Exception e1){
					e1.printStackTrace();
				}
				dispose();
			}
		});
		field.add(cancel);
		cancel.addMouseListener(new MouseAdapter(){
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
	
	
	public static void main(String[] args){
		new Register();
	}
}
