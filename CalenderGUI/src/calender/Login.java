package calender;

import java.awt.Container;
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
	 * Ķ���� ó�� ȭ�� ����
	 * ȸ�� ���� �� �α��� ���
	 * 
	 */
	static String id;


	JTextField txt_id = new JTextField(""); //Id�� ������ TextField
	JPasswordField txt_password = new JPasswordField(""); //��й�ȣ�� ������ TextField
	JButton btn_login = new JButton("�α���"); //�α��� ��ư
	JButton btn_register = new JButton("ȸ������"); //ȸ������ ��ư
	
	public Login(){
		setTitle("�α���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane1 = getContentPane();

		GridLayout grid = new GridLayout(3, 2);
		grid.setVgap(5);
		contentPane1.setLayout(grid);
		contentPane1.add(new JLabel("ID : "));
		contentPane1.add(txt_id);
		contentPane1.add(new JLabel("��й�ȣ : "));
		contentPane1.add(txt_password);
		contentPane1.add(btn_login);
		btn_login.addMouseListener(new MouseAdapter(){ //�α��� ��ư ������ �� ���콺 ������ ����
			public void mouseClicked(MouseEvent e){ //Ŭ���Ǿ��� ��
				String str_id = txt_id.getText();
				String str_password = new String(txt_password.getPassword());
				try{
					File file = new File("data\\log_in.txt"); //ID�� ��й�ȣ�� ����� text���� ��� ����
					BufferedReader in = new BufferedReader(new FileReader(file)); 
					String get_all,get_id, get_pw; //���پ� �о���� ���� ��ü�� id, password�� �����ϱ����� ��ü ����
					int check = 0; //�α��ο� �����Ͽ��� ��  üũ������ ����
					while((get_all = in.readLine()) != null){ //���̻� ���� ������ ���� ������ ID�� ��й�ȣ ��Ī
						StringTokenizer token = new StringTokenizer(get_all, " "); //���� ���忡�� ����� ���е� ���ڵ��� ���� ��ū���� ����
						while(token.hasMoreTokens()){  
							get_id = token.nextToken(); 
							if(str_id.equals(get_id)){
								get_pw = token.nextToken();
								if(str_password.equals(get_pw)){
									JOptionPane.showMessageDialog(null, "�α���!", "�α���", JOptionPane.INFORMATION_MESSAGE); //�α��� ���� Ȯ��â
									id = txt_id.getText();
									PanelManage();
									check = 1;
									dispose(); //�α��� ȭ�� ���ֱ�
								}
							}
							
						}
						
					}
					if(check == 0){
						JOptionPane.showMessageDialog(null, "ID �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.", "�α��ο���", JOptionPane.ERROR_MESSAGE); //�α��� ���� ���â
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
	void PanelManage() throws ParseException { //PanelManage ȭ���� �����ϴ� �޼ҵ� 
		PanelManage Manager = new PanelManage();
		Manager.setTitle("������ ����");
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



