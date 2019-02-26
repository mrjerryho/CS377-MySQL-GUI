package GUI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.StringTokenizer;

public class CopyText2
{  
   // Components shared by main() and call back methods
   // must be defined OUTSIDE

   public static JTextArea inputArea;       // Text area for input
   public static JTextArea outputArea;      // Text area for output
   public static JButton actionButton;      // Button to trigger copy action


   public static void main(String[] args)
   {  
      JFrame f = new JFrame("TextTest 2");

      // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      // 1. Construct components in the GUI
      // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

      // =====================================================
      // 1. a text area for input
      //    The text area will hold 6 lines or 42 characters
      // =====================================================
      inputArea = new JTextArea(6, 42);        // Default is editable...

      // ****************************************************************
      // The following statement picks a font for the text
      // ****************************************************************
      inputArea.setFont( new Font("SansSerif", Font.BOLD, 17) );

      // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
      // ATTENTION: Add horizntal & vertical scroll bars to JTextArea
      // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
      JScrollPane inputScrollArea = 
	new JScrollPane(inputArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
   
      // ===========================
      // 2. a text area for output
      // ===========================
      outputArea = new JTextArea(12, 42);
      outputArea.setEditable(false);     // No editing in output area....

      outputArea.setFont( new Font("Monospaced", Font.BOLD, 17) );

      // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
      // ATTENTION: Add scroll bars to JTextArea
      // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
      JScrollPane outputScrollArea = 
	new JScrollPane(outputArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      
      // ================================
      // 3. a button to start the show
      // ================================
      actionButton = new JButton("Copy !");
      

      // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      // 2. Place the components in JFrame
      // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

      // ======================================================
      // Pick Layout manager first !!!
      // ======================================================
      f.getContentPane().setLayout(new BorderLayout() );

      // ======================================================
      // Place the input & output areas in CENTER - stacked
      // ======================================================
      JPanel x = new JPanel();
      x.setLayout( new GridLayout(2,1) );

      x.add(inputScrollArea);
      x.add(outputScrollArea);

      f.getContentPane().add(x, "Center");

      // Place a sexy looking button in the South....
      JPanel buttonPanel = new JPanel();
      buttonPanel.add(actionButton);
      f.getContentPane().add(buttonPanel, "South");

      /* ++++++++++++++++++++++++++++++++++++++++++
         3. Register Listener for (some) components
         ++++++++++++++++++++++++++++++++++++++++++ */
      actionButton.addActionListener(new ButtonListener());  // Listener !

      f.setSize(500, 500);
      f.setVisible(true);
   }
   


   /* =============================================================
      Listener class for the Calculate button
      ============================================================= */
   public static class ButtonListener implements ActionListener
   {  
      public void actionPerformed(ActionEvent event)
      {  
	 String inputtext;
         StringTokenizer t;

	 // get user input from text area
	 inputtext = inputArea.getText();

	 // Tokenize input text
         t = new StringTokenizer(inputtext);

	 outputArea.setText("");
	 while (t.hasMoreTokens())
         {
            outputArea.append(t.nextToken() + "\n");
	 }
      }
   }   
}