package MIPSGo;

import javax.swing.*;

public class mainEntry extends JFrame{
	
	 private JLabel jLabel;
	 private JTextField jTextField;
	 private JButton jButton;
	 
	public mainEntry() 
	   { 
	      super(); 
	      this.setSize(800, 600); 
	      this.getContentPane().setLayout(null);
	      this.add(getJLabel(), null); 
	      this.add(getJTextField(), null); 
	      this.add(getJButton(), null); 
	      this.setTitle("HelloWorld"); 
	   } 
	  
	   private javax.swing.JLabel getJLabel() { 
	      if(jLabel == null) { 
	         jLabel = new javax.swing.JLabel(); 
	         jLabel.setBounds(34, 49, 53, 18); 
	         jLabel.setText("Name:"); 
	      } 
	      return jLabel; 
	   } 
	  
	   private javax.swing.JTextField getJTextField() { 
	      if(jTextField == null) { 
	         jTextField = new javax.swing.JTextField(); 
	         jTextField.setBounds(96, 49, 160, 20); 
	      } 
	      return jTextField; 
	   } 
	  
	   private javax.swing.JButton getJButton() { 
	      if(jButton == null) { 
	         jButton = new javax.swing.JButton(); 
	         jButton.setBounds(103, 110, 71, 27); 
	         jButton.setText("OK"); 
	      } 
	      return jButton; 
	   }
	   
	   /*public static void main(String[] args)
	   {
		   HelloWorld w = new HelloWorld();
		   w.setVisible(true);
	   }*/
	   public static void main(String[] args)
		{
			/*String[] testStr = new String[3];
			testStr[0] = ".text";
			testStr[1] = "L1: mv $t0,$t1";
			testStr[2] = "add $t1,$zero,$v0";	
		    codeSegment test = new codeSegment(testStr);
		    String[] c=test.generateBinaryCode();
			for(int i=0;c[i]!=null;i++)
			{
		    	System.out.println(c[i]);
			}*/
		   mainEntry c = new mainEntry();
		   c.setVisible(true);
		}
}
	   

     