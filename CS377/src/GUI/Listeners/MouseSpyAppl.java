package GUI.Listeners;
//MouseSpyAppl.java: shows how to add a listener for a component
//We add a listener for mouse events

import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseSpyAppl
{  
public static void main(String[] args)
{  
   JFrame f = new JFrame("Mouse Test");

   // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   // Register the CallBack Interface class (below)
   // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   f.addMouseListener(new myMouseListener() );

   f.setSize(300, 300);
   f.setVisible(true);
}
}


/* ============================================================
This is the call back interface class for Mouse events

Note: this is a SEPARATE class !!!
============================================================ */