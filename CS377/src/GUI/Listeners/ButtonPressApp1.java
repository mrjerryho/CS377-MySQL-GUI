package GUI.Listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonPressApp1
{  
   public static void main(String[] args)
   {  
      JFrame f = new JFrame("Button Test");

      JButton B;
      B = new JButton("Press me !");

      f.getContentPane().add(B);           // Add button

      B.addActionListener(new myListener() );

      f.setSize(300, 300);
      f.setVisible(true);
   }
}


class myListener implements ActionListener
{  
   public void actionPerformed(ActionEvent event)
   {  
      System.out.println("The button was pressed !");
   }
}