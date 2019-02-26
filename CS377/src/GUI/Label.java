package GUI;
import java.awt.*;
import javax.swing.*;
 
public class Label
{ 
 
   public static void main(String[] args)
   {
	 JFrame f = new JFrame("My Second GUI");
 
	 JLabel L;
	 L = new JLabel("Hello World !");  // Make a JLabel component      
 
	 f.getContentPane().add(L);        // Stick
 
	 f.setSize(400, 300);
	 f.setVisible(true);
   }
}