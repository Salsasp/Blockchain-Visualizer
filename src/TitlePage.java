import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
public class TitlePage extends JFrame{;
	Page1 page1;
	Page2 page2;
	Page3 page3;
	GridBagLayout layout;
	GridBagConstraints gbc;
	JLayeredPane layered;
	public TitlePage() throws FileNotFoundException
	{
		super("Dakota Gee");
		Blocks.readFile("ethereumP1data.txt");
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		layered = new JLayeredPane();
		layout = new GridBagLayout();
		gbc = new GridBagConstraints();
		page1 = new Page1();
		page2 = new Page2();
		page3 = new Page3();
		initializeComboBox();
		add(layered);
		
	}
	public void initializeComboBox()
	{
		JPanel panel = new JPanel();
		String[] options = 
			{"--select chart--", "Unique Miners", "Transaction Cost", "Time Difference"};
		JComboBox<String> comboBox = new JComboBox<String>(options);
		comboBox.setVisible(true);
		
		comboBox.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent event) {
		    	  if(comboBox.getSelectedIndex() == 0)
		    	  {
		    		layered.remove(page1);
		    		layered.remove(page2);
		    		layered.remove(page3);
		    		revalidate();
		    		repaint();
		    	  }
		    	  if(comboBox.getSelectedIndex() == 1)
		    	  {
		    		 layered.remove(page1);
		    		 layered.remove(page2);
		    		 layered.remove(page3);
		    		 layered.add(page1);
		    		 page1.setBounds(0, 50, 1000, 800);
		    		 revalidate();
		    	  }
		    	  if(comboBox.getSelectedIndex() == 2)
		    	  {
		    		  layered.remove(page1);
		    		  layered.remove(page2);
		    		  layered.remove(page3);
		    		  layered.add(page2);
		    		  page2.setBounds(0, 50, 1000, 800);
		    		  revalidate();
		    		  repaint();
		    	  }
		    	  if(comboBox.getSelectedIndex() == 3)
		    	  {
		    		  layered.remove(page1);
		    		  layered.remove(page2);
		    		  layered.remove(page3);
		    		  layered.add(page3);
		    		  page3.setBounds(0, 50, 1000, 800);
		    		  revalidate();
		    		  repaint();
		    	  }
		      }
		    });
		layered.add(comboBox);
		comboBox.setBounds(500, 10, 400, 20);
		
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		TitlePage main = new TitlePage();
	}

}
