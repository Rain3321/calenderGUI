package calender;


import java.awt.Container;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Event extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4;
	JFrame f = new JFrame();
	Container c = f.getContentPane();
	JButton btn;
	JTextArea schedule = new JTextArea();
	
	public Event(String id, int date){
		super();
		
		setSize(500, 300);
		String target = "C:\\Users\\Lee\\Desktop\\Rain\\data_file.txt";
//				"C:\\Users\\Lee\\Desktop\\Rain" + id +"\\" + date + ".txt";
		JPanel tp = new JPanel();
		tp.setLayout(new BoxLayout(tp, BoxLayout.Y_AXIS));
//		File file = new File(target);
		String text;
//		if(!file.exists()){
//			file.mkdirs();
//		}
		Path path = Paths.get(target);
		
		List<String> list1 = new ArrayList<String>();
		
		try {
//			BufferedReader out = new BufferedReader(new FileReader(file));
//			while((text = out.readLine()) != null){
//				schedule.setText(schedule.getText() + text);
				
//			}
			list1 = Files.readAllLines(path);
			for(String a : list1){
				schedule.setText(schedule.getText() + a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tp.add(schedule);
		tp.add(btn = new JButton(""));
		c.add(tp);
		btn.addActionListener(this);
		f.pack();
		f.setVisible(true);
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn3 = (JButton) e.getSource();
		if(btn3.getText().equals(btn.getText())){
			
			
			
		}
		
	}
	void makeTextField(){
		JPanel text = new JPanel();
		JTextArea newSchedule = new JTextArea("");
		JButton confirm = new JButton("confirm");
		text.add(newSchedule);
		text.add(confirm);
		f.add(text);
		
	}
	

}
