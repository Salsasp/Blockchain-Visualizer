import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class TitlePage extends JFrame{
	public static JFrame frame;

	public TitlePage()
	{
		super("Dakota Gee");
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(initializeComboBox(),BorderLayout.NORTH);
	}
	public JPanel initializeComboBox()
	{
		JPanel panel = new JPanel();
		String[] options = 
			{"--select chart--", "Unique Miners", "Transaction Cost", "Time Difference"};
		JComboBox<String> comboBox = new JComboBox<String>(options);
		
		comboBox.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent event) {
		    	  if(comboBox.getSelectedIndex() == 1)
		    	  {
		    		  
		    	  }
		    	  if(comboBox.getSelectedIndex() == 2)
		    	  {
		    		  
		    	  }
		    	  if(comboBox.getSelectedIndex() == 3)
		    	  {
		    		  
		    	  }
		      }
		    });
		comboBox.setVisible(true);
		panel.add(comboBox);
		return panel;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	public static void main(String[] args)
	{
		TitlePage main = new TitlePage();
	}

}
