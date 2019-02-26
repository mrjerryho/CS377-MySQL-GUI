package GUI;
import java.awt.*;
import javax.swing.*;
 
public class SelectFont
{
   public static void main(String[] args)
   {
	 JFrame f = new JFrame("My GUI");
	 JTextArea x;
	 JScrollPane y;
	 Font F;
 
	 x = new JTextArea();             // Make a JTextArea
	 x.setEditable(true);             // Output only
 
	 y = new JScrollPane(x, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
 
	 f.getContentPane().add(y);       // Stick
 
//	 F = new Font("Monospaced", Font.BOLD, 18);
	 F = new Font("Roman", Font.ITALIC, 18);
	 x.setFont( F );
 
	 x.setText("Hello World");
	 x.append("\n\n\n");
	 x.append("Hello Again");
 
	 f.setSize(400, 300);
	 f.setVisible(true);
   }
}