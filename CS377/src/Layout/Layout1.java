package Layout;
import java.awt.*;
import javax.swing.*;
 
public class Layout1
{ 
   public static void main(String[] args)
   {
	 JFrame f = new JFrame("Hello");
 
	 JPanel MyPanel = new JPanel();
 
	 MyPanel.setLayout( new FlowLayout() );    // Demo flow layout
 
	 JButton x1 = new JButton("I am x1");
	 JButton x2 = new JButton("I am x2");
	 JButton x3 = new JButton("I am x3");
	 JButton x4 = new JButton("I am x4");
	 JButton x5 = new JButton("I am x5");

	 MyPanel.add(x1);           // Add JButtons using Flow Layout
	 MyPanel.add(x2);
	 MyPanel.add(x3);
	 MyPanel.add(x4);
	 MyPanel.add(x5);
 
	 f.getContentPane().add(MyPanel, "Center"); // Paste MyPanel in center
						    // of the contentPane    
	 f.setSize(300, 300);
	 f.setVisible(true);
   }
}