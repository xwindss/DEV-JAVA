import javax.swing.*;
import java.awt.event.*;

public class Year {
	public static void main(String[] argv) {
		new ShowGUI();
	}
}

class ShowGUI extends JFrame implements ActionListener {
	JTextField jftInputText;
	JButton jbtnConvert;
	ButtonGroup btnGroup;
	JRadioButton jrbROC;
	JRadioButton jrbLord;
	
	public ShowGUI() {
		super("");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JLabel jblInput = new JLabel("請輸入年份");
		btnGroup = new ButtonGroup();
		jbtnConvert = new JButton("轉換");
		jrbROC = new JRadioButton("民國",true);
		jrbROC.setActionCommand("ROC");
		jrbLord = new JRadioButton("西元");
		jrbLord.setActionCommand("Lord");
		jftInputText = new JTextField("",10);
		btnGroup.add(jrbROC);
		btnGroup.add(jrbLord);
		jbtnConvert.addActionListener(this);
		
		GroupLayout layout = new GroupLayout(this.getContentPane());
		layout.setAutoCreateContainerGaps(true);
		layout.setAutoCreateGaps(true);
		
		//layout
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(jblInput)
						.addComponent(jrbROC))
				.addGroup(layout.createParallelGroup()
						.addComponent(jftInputText)
						.addComponent(jrbLord))
				.addGroup(layout.createParallelGroup()
						.addComponent(jbtnConvert))
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(jblInput)
						.addComponent(jftInputText)
						.addComponent(jbtnConvert))
				.addGroup(layout.createParallelGroup()
						.addComponent(jrbROC)
						.addComponent(jrbLord))
		);
		
		this.getContentPane().setLayout(layout);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbtnConvert) {
			String choice = btnGroup.getSelection().getActionCommand();
			String input = jftInputText.getText();							  //Check input is null or number
			int inputYear = Integer.parseInt(jftInputText.getText());  //Input year
			int year = 0;														//after convert year
			YearConvert yearConvert = new YearConvert();
			
			if(input.length() == 0) {               //Check is empty
				jftInputText.setText("未輸入年份");
			} else if(!isInteger(input)) {       //@see #isInteger(String str)
				jftInputText.setText("請輸入數字");
			} else if(choice == "ROC") {
				//@see YearConvert#LordToROC(int year)
				year = yearConvert.LordToROC(inputYear);
				jftInputText.setText(Integer.toString(year));
			} else if(choice == "Lord") {
				//@see YearConvert#ROCToLord(int year)
				year = yearConvert.ROCToLord(inputYear);
				jftInputText.setText(Integer.toString(year));
			}
		}//end outer if
	}//end function actionPerformed
	
	public Boolean isInteger(String str) {      //Check is number
		try {
			Integer.parseInt(str);
			return true;
		} catch(Exception e) {
			return false;
		}//end try...catch
	}//end function isInteger
	
}//end class

class YearConvert {
	private int year;
	
	public YearConvert() {
		this.year = 0;
	}
	
	public int LordToROC(int year) {
		this.year = year - 1911;
		return this.year;
	}
	
	public int ROCToLord(int year) {
		this.year = year + 1911;
		return this.year;
	}
}//end class
