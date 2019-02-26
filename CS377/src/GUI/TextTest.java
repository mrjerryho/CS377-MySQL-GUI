package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.StringTokenizer;

public class TextTest {
	public static JTextArea inputArea;
	public static JTextField resultField; 
	public static JButton calcButton;
	
	public static void main(String[] args){
		JFrame f = new JFrame("TextTest");
		//1. Construct the Components
		inputArea = new JTextArea();
		resultField = new JTextField(20);
		resultField.setEditable(false);
		calcButton = new JButton("Calculate");
		
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(inputArea, "Center");
		JPanel resultPanel = new JPanel();
		resultPanel.add(new JLabel("Average: "));
		resultPanel.add(resultField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(calcButton);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2,1));
		southPanel.add(resultPanel);
		southPanel.add(buttonPanel);
		
		f.getContentPane().add(southPanel,  "South");
		
		calcButton.addActionListener(new ButtonListener());
		f.setSize(500, 500);
		f.setVisible(true);
		}
	public static class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String input;
			double sum;
			int i, N;
			input = inputArea.getText();
			
			StringTokenizer tokenizer = new StringTokenizer(input);
			N = tokenizer.countTokens();
			sum = 0;
			for( i = 0; i < N; i++)
				sum += Double.parseDouble(tokenizer.nextToken());
			resultField.setText("" + sum/N);
		}
	}
	
}
