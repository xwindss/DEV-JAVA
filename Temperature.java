/*
 * Temperature format convert
 */

import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Temperature {
	public static void main (String argv[]) {
		new ShowGUI();
	}
}//end class

class ShowGUI extends JFrame implements ActionListener {
	JButton btnConvert;
	JRadioButton rbtnCelsius;
	JRadioButton rbtnFahrenheit;
	ButtonGroup rbtnGroup;
	JTextField tjInput;
	
	public ShowGUI() {
		super("溫度轉換");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblTemp = new JLabel("請輸入溫度:");
		tjInput = new JTextField();
		rbtnGroup = new ButtonGroup();
		rbtnCelsius = new JRadioButton("攝氏",true);
		rbtnCelsius.setActionCommand("Celsius");
		rbtnFahrenheit = new JRadioButton("華氏");
		rbtnFahrenheit.setActionCommand("Fahrenheit");
		rbtnGroup.add(rbtnCelsius);
		rbtnGroup.add(rbtnFahrenheit);
		btnConvert = new JButton("轉換");
		btnConvert.addActionListener(this);
		
		GroupLayout layout = new GroupLayout(this.getContentPane());
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//layout
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(lblTemp)
						.addComponent(rbtnCelsius))
				.addGroup(layout.createParallelGroup()
						.addComponent(tjInput)
						.addComponent(rbtnFahrenheit))
				.addGroup(layout.createParallelGroup()
						.addComponent(btnConvert))
		);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(lblTemp)
						.addComponent(tjInput)
						.addComponent(btnConvert))
				.addGroup(layout.createParallelGroup()
						.addComponent(rbtnCelsius)
						.addComponent(rbtnFahrenheit))
		);
		
		
		this.getContentPane().setLayout(layout);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}//end 
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnConvert) {
			String choice = rbtnGroup.getSelection().getActionCommand();
			Convert convert = new Convert();
			double temp = Double.parseDouble(tjInput.getText());
			
			
			if(choice == "Celsius") {
				//@see Convert#ConvertCelisus(double c)
				temp = convert.ConvertCelisus(temp);
				tjInput.setText(Double.toString(temp));
			} else if(choice == "Fahrenheit") {
				//@see Convert#ConvertFahrenheit(double f)
				temp = convert.ConvertFahrenheit(temp);
				tjInput.setText(Double.toString(temp));
			}//end inner if
		}//end outer if
	}//end
	
}//end class 

class Convert {
	private double convertTemp;
	
	Convert() {
		this.convertTemp = 0;
	}//end constructor
	
	/*
	 * @param c is a input Temperature
	 * @return after convert Temperature 
	 * 
	 */
	public double ConvertCelisus(double c) {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		convertTemp = (c - 32) * 5 / 9;
		convertTemp = Double.parseDouble(decimalFormat.format(convertTemp));
		
		return convertTemp;
	}//end 
	
	/*
	 * @param f is a input Temperature
	 * @return after convert Temperature 
	 * 
	 */
	public double ConvertFahrenheit(double f) {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		convertTemp = f * 9 / 5 + 32;
		convertTemp = Double.parseDouble(decimalFormat.format(convertTemp));
		return convertTemp;
	}//end 
}//end 
