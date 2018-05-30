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

	private static final long serialVersionUID = 2;
	
	public Register(){
		setTitle("Register");
		
		Container field = getContentPane();
//		Container text = getContentPane();
//		JPanel text = new JPanel();
//		text.add(new TextArea("������ �Է����ּ���."));
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
		confirm.addMouseListener(new MouseAdapter() { //Ȯ�ι�ư ������ �� ���콺 ������
			
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
					BufferedReader in = new BufferedReader(new FileReader(file));
					/*
					 * id�� password�� ����� �����Ͽ� �����ϰ� ������ �ΰ��� ��� ����� �Ŀ� �ٹٲ�
					 * id�� ã���� �� password�� �ٷ� ã�� ���Ͽ� ������ �����Ǵ� ���鸸 ���� �ٿ� ������
					 */
					String check;
					int standard = 0;
					while((check = in.readLine()) != null){
						StringTokenizer token = new StringTokenizer(check, " ");
						while(token.hasMoreTokens()){
							if(id.equals(token.nextToken())){
								standard = 1;
							}
						}
					}
					if(standard == 1){
						JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ID�Դϴ�.", "ȸ�����Կ���", JOptionPane.ERROR_MESSAGE);
						text_id.setText("");
						text_password.setText("");
					}
					else if(standard == 0){
						out.write(id);
						out.write(" ");
						out.write(password);
						out.newLine(); 
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
