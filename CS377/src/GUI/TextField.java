package GUI;

import java.awt.*;
import javax.swing.*;

public class TextField
{ 
  public static void main(String[] args)
  {
	 JFrame f = new JFrame("My GUI");

	 JTextField x;
	 x = new JTextField();           // Make a JTextField      

	 f.getContentPane().add(x);      // Stick

	 x.setEditable(false);           // Output only

	 x.setText("Hello World");

	 f.setSize(400, 300);
	 f.setVisible(true);
  }
}