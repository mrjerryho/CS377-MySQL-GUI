package GUI;
import java.awt.*;
import javax.swing.*;
 
public class ScrollPane
{ 
   public static void main(String[] args)
   {
	  JFrame f = new JFrame("My GUI");
 
	  JTextArea x;
	  JScrollPane y;
 
	  x = new JTextArea();             // Make a JTextArea
	  x.setEditable(true);             // Output only
 
	  y = new JScrollPane(x, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
 
	  f.getContentPane().add(y);     // Add y !!!
 
	  x.setText("Hello World");      // Use x to read/write text
	  x.append("\n\n\n");
	  x.append("Hello Again");
	  x.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	  x.append("Bye now....");
 
	  f.setSize(400, 300);
	  f.setVisible(true);
   }
}