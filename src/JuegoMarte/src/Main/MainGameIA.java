package Main;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import GUI.Applet;

public class MainGameIA {

	public static void main(String[] args) {
		// Look & Feel del SO.
		try
		{
		    JFrame.setDefaultLookAndFeelDecorated(true);
		    JDialog.setDefaultLookAndFeelDecorated(true);
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}

		JFrame frame = new JFrame();
		Applet applet = new Applet();
		applet.init();

		
		frame.add(applet);
		frame.setTitle("Robot en Marte");
		frame.setSize(700, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
//		frame.setExtendedState(6); // Maximizar ventana
	}

}