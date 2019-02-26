	/* =======================================================================
	  	Author:Jerry Ho
	  	CS377 GUI JDBC Project
	  	This code is my own work with the help of Dr.Cheung's online class notes. 11/21/16
	   ======================================================================= */
	
	import java.awt.*;
	import javax.swing.*;
	import javax.swing.border.EmptyBorder;
	
	import java.awt.event.*;
	import java.sql.*;
	import java.text.DecimalFormat;
	import java.util.Arrays;
	
	public class JDBC  
	{
	   public static JFrame mainFrame;
	
	   public static JLabel DBName = new JLabel("Database: ");
	   public static JTextField Db = new JTextField();
	   public static JButton Select = new JButton("Select");
	   public static JTextField Empty = new JTextField();
	   public static JButton Execute = new JButton("Execute");
	
	
	   public static JLabel ColumnLabel = new JLabel("Column: ");
	   public static JTextField Column = new JTextField();
	
	   public static JTextField MaxText = new JTextField();
	   public static JButton Max = new JButton("Maximum");
	   public static JTextField MinText = new JTextField();
	   public static JButton Min = new JButton("Minimum");
	   public static JTextField AvgText = new JTextField();
	   public static JButton Avg = new JButton("Average");
	   public static JTextField MedianText = new JTextField();
	   public static JButton Median = new JButton("Median");
	
	   public static JTextArea Input = new JTextArea();
	   public static JTextArea Output = new JTextArea();
	   public static Connection hoConnection;
	   public static Statement SQLstmt;
	   public static ResultSet rset;
	   public static ResultSetMetaData meta;
	   public static String queryStmt;
	   public static int globalCount = 0;
	
	   public static void main( String[] args )
	   {
	      Font ss_font = new Font("SansSarif",Font.BOLD,16) ;
	      Font ms_font = new Font("Monospaced",Font.BOLD,16) ;
	
	      JPanel P1 = new JPanel();   // Top panel
	      JPanel P2 = new JPanel();
	
	      P1.setLayout( new BorderLayout() );
	      P2.setLayout( new BorderLayout() );
	      
	      P1.setBorder(new EmptyBorder(10,10,0,0));
	      P2.setBorder(new EmptyBorder(10,10,10,0));
	
	      /* =============================================
	         Make top panel
	         ============================================= */
	      JScrollPane d1 = new JScrollPane(Input, 
	                                       JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,
	                                       JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	      Input.setFont( ss_font );
	
	      JPanel s1 = new JPanel(); // Side panel
	      s1.setLayout( new GridLayout(10,1) );
	      s1.setBorder(new EmptyBorder(50,10,10,10));
	      s1.add( DBName );      
	      s1.add( Db );
	      Db.setFont( ss_font );
	      s1.add( Select );
	      Empty.setVisible(false);
	      s1.add(Empty);
	      s1.add( Execute );
	      Execute.setPreferredSize(new Dimension(140, 30)) ;
	
	      P1.add(d1, "Center");
	      P1.add(s1, "East");
	
	      /* =============================================
	         Make bottom panel
	         ============================================= */
	      JScrollPane d2 = new JScrollPane(Output, 
	                                       JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,
	                                       JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
	      Output.setFont( ms_font );
	      Output.setEditable(false);
	
	      JPanel s3 = new JPanel(); // Put ColumnLabel and Column on 1 row
	      s3.add(ColumnLabel);
	      s3.add(Column);
	      Column.setFont( ss_font );
	
	      Column.setPreferredSize(new Dimension(40, 30)) ;
	
	      JPanel s2 = new JPanel(); // Side panel
	      s2.setLayout( new GridLayout( 10,1 ) );
	      s2.setBorder(new EmptyBorder(10,10,10,10));
	      s2.add( s3 );
	
	      MaxText.setPreferredSize(new Dimension(140, 30)) ;
	      s2.add( MaxText );
	      MaxText.setFont( ss_font );
	      MaxText.setEditable(false);
	      s2.add( Max );
	
	      s2.add( MinText );
	      MinText.setFont( ss_font );
	      MinText.setEditable(false);
	      s2.add( Min );
	
	      s2.add( AvgText );
	      AvgText.setFont( ss_font );
	      AvgText.setEditable(false);
	      s2.add( Avg );
	
	      s2.add( MedianText );
	      MedianText.setFont( ss_font );
	      MedianText.setEditable(false);
	      s2.add( Median );
	
	
	      P2.add(d2, "Center");
	      P2.add(s2, "East");
	
	      mainFrame = new JFrame("Jerry Ho's GUI for CS377 JDBC project");
	      mainFrame.getContentPane().setLayout( new GridBagLayout() );
	      GridBagConstraints bounds = new GridBagConstraints();
	      bounds.fill = GridBagConstraints.BOTH;
	      bounds.weightx = 1;
	      bounds.weighty = 1.0/3.0;
	      bounds.gridx = 0;
	      bounds.gridy = 0;
	      mainFrame.getContentPane().add( P1, bounds );
	      bounds.fill = GridBagConstraints.BOTH;
	      bounds.weightx = 1;
	      bounds.weighty = 2.0/3.0;
	      bounds.gridx = 0;
	      bounds.gridy = 1;
	      mainFrame.getContentPane().add( P2, bounds );
	      mainFrame.setSize(900, 700);
	      mainFrame.setVisible(true);
	      
	      Select.addActionListener(new SelectListener());
	      Execute.addActionListener(new ExecuteListener());
	      Max.addActionListener(new MaxListener());
	      Min.addActionListener(new MinListener());
	      Avg.addActionListener(new AvgListener());
	      Median.addActionListener(new MedianListener());
	   }
	   
	   public static class SelectListener implements ActionListener{
	
		   	public void actionPerformed(ActionEvent arg0) {
		   		String url = "jdbc:mysql://holland.mathcs.emory.edu:3306/";
		        String dbName = "companyDB";
		        String userName = "cs377";
		        String password = "abc123";
		        
		        /****************************************************************/
		        try{
		        	// Registering the MySQL JDBC driver in MySQL
		        	Class.forName("com.mysql.jdbc.Driver");
		        } 
		        catch (Exception e){
		        	System.out.println("Failed to load JDBC driver.");
		        	return;
		        }
		    
		        // Connect to the database
		        hoConnection = null;
		        SQLstmt = null;
		        dbName = Db.getText();
		        
		  
		        try{
		        	hoConnection = DriverManager.getConnection(url+dbName,userName,password);
		        	Output.setText("");
		        	Output.append("Successfully Connected");
		        } 
		        catch (Exception e){
		        	System.err.println("Problems connecting to " + url+dbName);
		        	Output.setText("Problems connecting to " + url);
		        }
			}
	   }
	   
	   public static class ExecuteListener implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				queryStmt = Input.getText();
				Output.setText("");
				globalCount = 0;
				int[] length = new int [100];
				try {
					SQLstmt = hoConnection.createStatement();
					rset = SQLstmt.executeQuery(queryStmt);
					meta = rset.getMetaData(); //getting metaData
					int NCols = meta.getColumnCount();
					
					for (int i = 1; i <= NCols; i++){
						String name = meta.getColumnLabel(i);
						length[i] = Math.max(6, meta.getColumnDisplaySize(i));
						if(meta.getColumnType(i) == Types.DECIMAL || meta.getColumnType(i) == Types.INTEGER){				
							length[i] = 10;
							name = name.substring(0, Math.min(name.length(), 10));
						}
						else name = name.substring(0, Math.min(name.length(), length[i]));
						System.out.print(name); //field name
						Output.append(name);
						
						/* ----------------------------------------------
			   		     ****Pad**** the attr name i to length[i]
			   		     ---------------------------------------------- */
			   		  	for (int j = name.length(); j < length[i]; j++){
			   		  		System.out.print(" ");
			   		  		Output.append(" ");
						}
			   		  	System.out.print(" ");
						Output.append(" ");
					}
					System.out.println();
					Output.append("\n");
					/* ---------------------------------
	                	Print a dividing line....
			 			--------------------------------- */
					for ( int i = 1; i <= NCols; i++ ){
						for ( int j = 0; j < length[i]; j++){
							System.out.print("-");
						    Output.append("-");
						    }
						System.out.print(" ");
						Output.append(" ");
					}
					System.out.println();
					Output.append("\n");
					/* ===========================================
			      		Fetch and print one row at a time....
			      	=========================================== */
				   while (rset.next ())    // Advance to next row
				   {
				      /* ===========================================
				         Fetch the columns (attributes) from a row
				         =========================================== */
				      for(int i = 1; i <= NCols; i++)
				      {
				         String nextItem;
				         if (rset.getString(i) == null) nextItem = "NULL"; 
				         else nextItem = rset.getString(i);
				         if (meta.getColumnType(i) == Types.DECIMAL||meta.getColumnType(i) == Types.INTEGER){
				        	 for (int j = 0; j < length[i] - nextItem.length(); j++){
				        		 if(nextItem != null){
				        			 int scale =  meta.getScale(i);
										String scaleForm = "0.";
										if(scale == 0)
											scaleForm = "0";
										for(int k = 0; k< scale; k++)
											scaleForm += "0";
										double myDouble = rset.getDouble(i);
										DecimalFormat form = new DecimalFormat(scaleForm);
										nextItem = form.format(myDouble).toString();
										if(rset.getString(i) == null) nextItem = "NULL";
									}
				        		 System.out.print(" ");   
				        		 Output.append(" ");
				             } 
				        	 System.out.print(nextItem);
				        	 Output.append(nextItem);
				        	 System.out.print(" ");
				        	 Output.append(" ");
				        	 continue;            
				         }
				         /* ----------------------------------------------
			         		Pad the attr value to length[i]
			       			---------------------------------------------- */
				         System.out.print(nextItem);
				         Output.append(nextItem);
				         for (int j = nextItem.length(); j < length[i]+1; j++){
				        	 System.out.print(" ");
				        	 Output.append(" ");
			             }
				      }
			         System.out.println();
			         Output.append("\n");
			         globalCount++;
			   }
			}
				catch (Exception e)
				{
				   System.out.println("e.getMessage();"); //printing error message
				}
			}
	   }
	   public static class MaxListener implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				try{
					String numCol = Column.getText();
					int nCol = Integer.parseInt(numCol);
					double max = 0;
					rset.beforeFirst();
					rset.next();
					if(meta.getColumnType(nCol) == Types.DECIMAL || meta.getColumnType(nCol) == Types.INTEGER){
						double[] numArray = new double[globalCount];
						for(int i = 0; i < globalCount; i++){
							
							if(rset.getString(nCol) == "NULL"){
								numArray[i] = 0;
								rset.next();
								continue;
							}
							numArray[i] = rset.getDouble(nCol);
							rset.next();
						}
						Arrays.sort(numArray);
						max = numArray[globalCount - 1];
						int scale = meta.getScale(nCol);
						String scaleForm = "0.";
						if(scale == 0)
							scaleForm = "0";
						for(int k = 0; k < scale; k++)
							scaleForm += "0";
						DecimalFormat form = new DecimalFormat(scaleForm);
						String sDouble = form.format(max).toString();
						System.out.println(sDouble);
						MaxText.setText(sDouble);
					}
					if(meta.getColumnType(nCol) == Types.CHAR){
						String[] strArray = new String [globalCount];
						for(int i = 0; i < globalCount; i++){
							if(rset.getString(nCol) == "NULL" || rset.getString(nCol) == null){
								strArray[i] = "";
								rset.next();
								continue;
							}
							strArray[i] = rset.getString(nCol);
							rset.next();
						}
						Arrays.sort(strArray);
						System.out.println(strArray[globalCount-1]);
						MaxText.setText(strArray[globalCount-1]);
					}
				}
				catch (Exception e){
					System.out.println("Max Error");
					MaxText.setText("Max Error");
				}
			}
	   }
	   public static class MinListener implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				try{
					String numCol = Column.getText();
					int nCol = Integer.parseInt(numCol);
					int countNull = 0;
					double min = 0;
					rset.beforeFirst();
					rset.next();
					if(meta.getColumnType(nCol) == Types.DECIMAL || meta.getColumnType(nCol) == Types.INTEGER){
						double[] numArray = new double[globalCount];
						for(int i = 0; i < globalCount; i++){
							if(rset.getString(nCol) == "NULL" || rset.getString(nCol) == null){
								countNull++; 
								numArray[i] = 0; //set that spot to 0
								rset.next();
								continue;
							}
							numArray[i] = rset.getDouble(nCol);
							rset.next();
						}
						Arrays.sort(numArray);
						min = numArray[countNull];
						int scale = meta.getScale(nCol);
						String scaleForm = "0.";
						if(scale == 0)
							scaleForm = "0";
						for(int k = 0; k<scale; k++)
							scaleForm += "0";
						DecimalFormat form = new DecimalFormat(scaleForm);
						String sDouble = form.format(min).toString();
						System.out.println(sDouble);
						MinText.setText(sDouble);
					}
					if(meta.getColumnType(nCol) == Types.CHAR){
						String[] strArray = new String [globalCount];
						for(int i = 0; i < globalCount; i++){
							if(rset.getString(nCol) == "NULL" || rset.getString(nCol) == null){
								countNull++;
								strArray[i] = ""; //make that spot blank
								rset.next();
								continue;
							}
							strArray[i] = rset.getString(nCol);
							rset.next();
						}
						Arrays.sort(strArray);
						System.out.println(strArray[countNull]);
						MinText.setText(strArray[countNull]);
					}
				}
				catch (Exception e){
					System.out.println("Min Error");
					MinText.setText("Min Error");
				}
			}
	   }
	   public static class AvgListener implements ActionListener{
		   public void actionPerformed(ActionEvent event) {
				try{
					String numCol = Column.getText();
					int nCol = Integer.parseInt(numCol);
					int countNull = 0;
					double sum = 0;
					double avg = 0;
					rset.beforeFirst();
					rset.next();
					if(meta.getColumnType(nCol) == Types.DECIMAL || meta.getColumnType(nCol) == Types.INTEGER){
						for(int i = 0; i < globalCount; i++){
							
							if(rset.getString(nCol) == "NULL" || rset.getString(nCol) == null){
								countNull++; 
								rset.next();
								continue;
							}
							sum += rset.getDouble(nCol);
							rset.next();
						}
							
						avg = sum/(globalCount-countNull);
						int scale = meta.getScale(nCol);
						if(scale == 0){
							scale = 4; //in case there is integers and the average is a double. 
						}
						String scaleForm = "0.";
						for(int k = 0; k<scale; k++)
							scaleForm += "0";
						DecimalFormat form = new DecimalFormat(scaleForm);
						String sDouble = form.format(avg).toString();					
						System.out.println(sDouble);
						AvgText.setText(sDouble);
					}
					if(meta.getColumnType(nCol) == Types.CHAR){
						AvgText.setText("illegal");
					}
				}
				catch (Exception e){
					System.out.println("Avg Error");
					AvgText.setText("Avg Error");
				}
			}
	   }
	   public static class MedianListener implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				try{
					String numCol = Column.getText();
					int nCol = Integer.parseInt(numCol);
					int countNull = 0;
					double median = 0;
					rset.beforeFirst();
					rset.next();
					if(meta.getColumnType(nCol) == Types.DECIMAL || meta.getColumnType(nCol) == Types.INTEGER){
						double[] numArray = new double[globalCount];
						for(int i = 0; i < globalCount; i++){
							if(rset.getString(nCol) == "NULL" || rset.getString(nCol) == null){
								countNull++; 
								numArray[i] = 0; //set that spot to 0
								rset.next();
								continue;
							}
							numArray[i] = rset.getDouble(nCol);
							rset.next();
						}
						Arrays.sort(numArray);
						median = numArray[(globalCount-countNull)/2+countNull];
						int scale = meta.getScale(nCol);
						String scaleForm = "0.";
						if(scale == 0)
							scaleForm = "0";
						for(int k = 0; k < scale; k++)
							scaleForm += "0";
						DecimalFormat form = new DecimalFormat(scaleForm);
						String sDouble = form.format(median).toString();
						System.out.println(sDouble);
						MedianText.setText(sDouble);
					}
					if(meta.getColumnType(nCol) == Types.CHAR){
						String[] strArray = new String [globalCount];
						for(int i = 0; i < globalCount; i++){
							if(rset.getString(nCol) == "NULL" || rset.getString(nCol) == null){
								countNull++;
								strArray[i] = ""; //make that spot blank
								rset.next();
								continue;
							}
							strArray[i] = rset.getString(nCol);
							rset.next();
						}
						Arrays.sort(strArray);
						System.out.println(strArray[(globalCount-countNull)/2+countNull]);
						MedianText.setText(strArray[(globalCount-countNull)/2+countNull]);
					}
				}
				catch (Exception e){
					System.out.println("Median Error");
					MedianText.setText("Median Error");
				}
			}
	   }
	}