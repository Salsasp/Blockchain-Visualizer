import java.io.FileNotFoundException;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class TitlePage {
	public static JFrame frame;

	public TitlePage()
	{
		frame = new JFrame("Dakota Gee");
		frame.setSize(1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		JPanel panel = new JPanel();
		String[] options = 
			{"--select chart--", "Unique Miners", "Transaction Cost", "Time Difference"};
		JComboBox<String> comboBox = new JComboBox<String>(options);
		panel.add(comboBox);
		frame.add(panel);
	}
	
	public JFrame getFrame()
	{
		return frame;
	}

}
