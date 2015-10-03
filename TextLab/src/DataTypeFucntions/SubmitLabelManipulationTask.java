package DataTypeFucntions;
import javax.swing.*;

import java.util.concurrent.*;

public class SubmitLabelManipulationTask {
	public static void main(String[] args) throws Exception{
		JFrame frame = new JFrame("Hello Swing");
		JLabel label = new JLabel("A Label");
		frame.add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		TimeUnit.SECONDS.sleep(10);
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				label.setText("Hey! This is Different");
			}
		});
	}
}
