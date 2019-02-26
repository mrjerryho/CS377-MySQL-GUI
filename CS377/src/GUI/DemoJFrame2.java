package GUI;
import javax.swing.*;

  public class DemoJFrame2
  {
     public static void main(String args[])
      {
        JFrame f = new JFrame("Hello World!");
        f.setSize(400, 300);                 

	JLabel L = new JLabel("I am a label !");

	f.getContentPane().add(L);     // Stick label L on contentPane   

        f.setVisible(true);
      }
  }