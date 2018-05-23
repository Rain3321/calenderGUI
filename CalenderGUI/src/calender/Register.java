package calender;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


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
		field.add(new JPasswordField(""));
		field.add(confirm);
		confirm.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				String id = text_id.getText().toString();
				String password = text_password.toString();
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
