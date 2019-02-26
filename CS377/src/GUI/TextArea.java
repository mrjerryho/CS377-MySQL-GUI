package GUI;
import java.awt.*;
import javax.swing.*;
 
public class TextArea
{
   public static void main(String[] args)
   {
	 JFrame f = new JFrame("My GUI");
 
	 JTextArea x;
	 x = new JTextArea();             // Make a JTextArea       
 
	 f.getContentPane().add(x);       // Stick
 
	 x.setEditable(true);             // Output only

	 x.setText("");          // Clear text area

	 x.append("Hello World\n");
	 x.append("Hello Again\n");
 
	 f.setSize(400, 300);
	 f.setVisible(true);
   }
}