package Layout;
import java.awt.*;
import javax.swing.*;
 
public class Layout3
{ 
   public static void main(String[] args)
   {
	 JFrame f = new JFrame("Border Layout");
 
	 // -------------------------------------------------------
	 // Make Panel with 5 button
	 // -------------------------------------------------------
	 JPanel MyPanel1 = new JPanel();
 
	 MyPanel1.setLayout( new BorderLayout() );
 
	 JButton x1 = new JButton("I am x1");
	 JButton x2 = new JButton("I am x2");
	 JButton x3 = new JButton("I am x3");
	 JButton x4 = new JButton("I am x4");
	 JButton x5 = new JButton("I am x5");
 
	 MyPanel1.add(x1, "North");
	 MyPanel1.add(x2, "South");
	 MyPanel1.add(x3, "East");
	 MyPanel1.add(x4, "West");
	 MyPanel1.add(x5, "Center");
 
	 f.getContentPane().add( MyPanel1, "North");   // Add MyPanel1 to North
 
	 // -----------------------------------------------------------
	 // Make another Panel with 5 button
	 // -----------------------------------------------------------
	 JPanel MyPanel2 = new JPanel();
 
	 MyPanel2.setLayout( new BorderLayout() );
 
	 JButton x6 = new JButton("I am x6");
	 JButton x7 = new JButton("I am x7");
	 JButton x8 = new JButton("I am x8");
	 JButton x9 = new JButton("I am x9");
	 JButton x10 = new JButton("I am x10");
 
	 MyPanel2.add(x6, "North");
	 MyPanel2.add(x7, "South");
	 MyPanel2.add(x8, "East");
	 MyPanel2.add(x9, "West");
	 MyPanel2.add(x10, "Center");
 
	 f.getContentPane().add( MyPanel2, "South");   // Paste MyPanel2 to South
//    f.getContentPane().add(MyPanel2, "Center");  // Paste MyPanel2 to Center     
 
	 f.setSize(400, 300);
	 f.setVisible(true);
   }
}