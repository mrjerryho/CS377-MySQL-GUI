package GUI;
import java.awt.*;
import javax.swing.*;
 
public class Button
{
   public static void main(String[] args)
   {
	JFrame f = new JFrame("My GUI");
 
	JButton L;
	L = new JButton("ABC");        // Make a JButton      
 
	f.getContentPane().add(L);     // Stick
 
	f.setSize(400, 300);
	f.setVisible(true);
   }
}