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
	 * Ķ���� ó�� ȭ�� ����
	 * ȸ�� ���� �� �α��� ���
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
		btn_login.addMouseListener(new MouseAdapter(){ //�α��� ��ư ������ �� ���콺 ������ ����
			public void mouseClicked(MouseEvent e){ //Ŭ���Ǿ��� ��
				String str_id = txt_id.getText();
				String str_password = new String(txt_password.getPassword());
				try{
					File file = new File("C:\\Users\\Lee\\Desktop\\Rain\\data_file.txt"); //����������  id �� password�� ����� ����Ǿ�����
					BufferedReader in = new BufferedReader(new FileReader(file)); 
					String get_all,get_id, get_pw; //���پ� �о���� ���� ��ü�� id, password�� �����ϱ����� ��ü ����
					int check = 0; //�α��ο� �����Ͽ��� ��  üũ������ ����
					while((get_all = in.readLine()) != null){ //���̻� ���� ������ ���� ������
						StringTokenizer token = new StringTokenizer(get_all, " "); //���� ���忡�� ����� ���е� ���ڵ��� ���� ��ū���� ����
						while(token.hasMoreTokens()){ //��ū�� �������� ����
							get_id = token.nextToken(); 
							if(str_id.equals(get_id)){
								get_pw = token.nextToken();
								if(str_password.equals(get_pw)){
									JOptionPane.showMessageDialog(null, "�α���!", "�α���", JOptionPane.INFORMATION_MESSAGE);
									new Calender(str_id);
									check = 1;
									dispose(); //�α��� ȭ�� ���ֱ�
								}
							}
							
						}
						
					}
					if(check == 0){
						JOptionPane.showMessageDialog(null, "��й�ȣ�Ǵ� ID�� Ʋ�Ƚ��ϴ�.", "�α��ο���", JOptionPane.ERROR_MESSAGE);
					}
					in.close();
				}
				
				catch(Exception e1){
					e1.printStackTrace();
				}
				
			}
		
		});
		contentPane1.add(btn_register);
		btn_register.addMouseListener(new MouseAdapter() { //ȸ������ ��ư ������ �� ���콺 ������
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



