package GUI;
import java.awt.*;
import javax.swing.*;

public class Frame2
{
   public static void main(String[] args)
   {
      JFrame f = new JFrame("My First GUI");

      f.setSize(400,300);

      JLabel L = new JLabel("Hello World !");                 

      f.getContentPane().add( L );

      f.setVisible(true);
   }
}