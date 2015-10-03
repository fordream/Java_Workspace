package MIPSCPU;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow{

	JFrame frmMipsSimulator;
	MIPSCPU cpu;
	JMenuBar menuBar;
	JMenu mnFile;
	JMenuItem mntmNewImage;
	JMenuItem mntmSaveAs;
	JTextArea textArea;
	JLabel lblNewLabel;
	JButton btnCpuStep;
	JButton btnCpuFullSpeed;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Create the application.
	 */
	public MainWindow() {
		cpu=new MIPSCPU();
		cpu.boot("boot.bin");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmMipsSimulator = new JFrame();
		frmMipsSimulator.setTitle("MIPS Simulator");
		frmMipsSimulator.setResizable(false);
		frmMipsSimulator.setBounds(100, 100, 805, 607);
		frmMipsSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frmMipsSimulator.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		mnFile.setFont(new Font("Century Gothic", Font.BOLD, 12));
		menuBar.add(mnFile);
		
		mntmNewImage = new JMenuItem("New Image");
		mntmNewImage.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnFile.add(mntmNewImage);
		
		mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		mnFile.add(mntmSaveAs);
		
		JMenu mnRun = new JMenu("Run");
		mnRun.setFont(new Font("Century Gothic", Font.BOLD, 12));
		menuBar.add(mnRun);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mntmNewMenuItem.setFont(new Font("Century Gothic", Font.BOLD, 12));
		mnRun.add(mntmNewMenuItem);
		frmMipsSimulator.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.BLACK);
		textArea.setBounds(10, 25, 644, 489);
		textArea.setForeground(Color.WHITE);
		frmMipsSimulator.getContentPane().add(textArea);
		
		lblNewLabel = new JLabel("Moniter");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 0, 54, 15);
		frmMipsSimulator.getContentPane().add(lblNewLabel);
		
		btnCpuStep = new JButton("CPU Step");
		btnCpuStep.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnCpuStep.setForeground(Color.BLACK);
		btnCpuStep.setBounds(10, 524, 93, 23);
		frmMipsSimulator.getContentPane().add(btnCpuStep);
		
		btnCpuFullSpeed = new JButton("CPU FullSpeed");
		btnCpuFullSpeed.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnCpuFullSpeed.setBounds(113, 524, 121, 23);
		frmMipsSimulator.getContentPane().add(btnCpuFullSpeed);
		
		JButton btnTerminate = new JButton("Terminate");
		btnTerminate.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		btnTerminate.setForeground(Color.RED);
		btnTerminate.setBounds(244, 524, 99, 23);
		frmMipsSimulator.getContentPane().add(btnTerminate);
		
		JLabel lblNewLabel_2 = new JLabel("800");
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblNewLabel_2.setBounds(61, 0, 34, 15);
		lblNewLabel_2.setText(MIPSCPU.WIDTH+"");
		frmMipsSimulator.getContentPane().add(lblNewLabel_2);
		
		JLabel label = new JLabel("*");
		label.setBounds(89, 0, 18, 15);
		frmMipsSimulator.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("600");
		label_1.setFont(new Font("Century Gothic", Font.BOLD, 12));
		label_1.setBounds(105, 0, 54, 15);
		label_1.setText(MIPSCPU.getHeight()+"");
		frmMipsSimulator.getContentPane().add(label_1);
		
		JLabel lblDebugInformation = new JLabel("Debug Information");
		lblDebugInformation.setFont(new Font("Century Gothic", Font.BOLD, 12));
		lblDebugInformation.setBounds(664, 0, 111, 15);
		frmMipsSimulator.getContentPane().add(lblDebugInformation);
		
		JPanel panel = new JPanel();
		panel.setBounds(664, 10, 121, 200);
		frmMipsSimulator.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel lblCurrentInstruction = new JLabel("Current Instruction");
		sl_panel.putConstraint(SpringLayout.NORTH, lblCurrentInstruction, 10, SpringLayout.NORTH, panel);
		panel.add(lblCurrentInstruction);
		lblCurrentInstruction.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		textField = new JTextField();
		textField.setEditable(false);
		sl_panel.putConstraint(SpringLayout.NORTH, textField, 32, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblCurrentInstruction, 0, SpringLayout.WEST, textField);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblCurrentInstruction, -6, SpringLayout.NORTH, textField);
		sl_panel.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, panel);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Next Instruction");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 6, SpringLayout.SOUTH, textField);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, panel);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		sl_panel.putConstraint(SpringLayout.NORTH, textField_1, 6, SpringLayout.SOUTH, lblNewLabel_1);
		sl_panel.putConstraint(SpringLayout.WEST, textField_1, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, panel);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPc = new JLabel("PC");
		sl_panel.putConstraint(SpringLayout.NORTH, lblPc, 6, SpringLayout.SOUTH, textField_1);
		sl_panel.putConstraint(SpringLayout.WEST, lblPc, 0, SpringLayout.WEST, panel);
		panel.add(lblPc);
		lblPc.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		sl_panel.putConstraint(SpringLayout.NORTH, textField_2, 6, SpringLayout.SOUTH, lblPc);
		sl_panel.putConstraint(SpringLayout.WEST, textField_2, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, panel);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		btnCpuStep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cpu.execute(false)!= -1)
					textArea.append(""+cpu.PC+"\n");
				else textArea.append("Execution Complete");
			}
		});
		
		btnCpuFullSpeed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			    while(cpu.execute(false)!=-1)
			    {
			    	textArea.setText("");
				String[] VRAMDATA=cpu.getVramData();
				for(int i=0;i<MIPSCPU.getHeight();i++)
				{
					textArea.append(VRAMDATA[i]+"\n");
				}
			    }
			}
		});
	}
	
	public static void main(String[] args)
	{
		 try { 

		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//Windows风格 

		         //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel") ; //Mac风格 

		        // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel") ;//Java默认风格 

		     } catch (ClassNotFoundException ex) { 

		         ex.printStackTrace(); 

		     } catch (InstantiationException ex) { 

		         ex.printStackTrace(); 

		     } catch (IllegalAccessException ex) { 

		         ex.printStackTrace(); 

		     } catch (UnsupportedLookAndFeelException ex) { 

		         ex.printStackTrace(); 

		     }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMipsSimulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
