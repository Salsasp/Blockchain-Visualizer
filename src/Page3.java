import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection; 

public class Page3 extends JFrame{
	int lowerBound;
	int upperBound;
	JPanel panel;
	
	public Page3() throws FileNotFoundException
	{
		super("Time Difference Between Blocks");
		Blocks.readFile("ethereumP1data.txt");
		panel = new JPanel();
		JFreeChart barChart = ChartFactory.createBarChart("Time Difference Between Blocks", "Blocks", "Time Units (hrs, mins, secs)", createDataset());
		
	}
	private CategoryDataset createDataset()
	{
		
	}
	
	public void initializeBoxes()
	{
		JTextField text1 = new JTextField();
		JTextField text2 = new JTextField();
		JButton button = new JButton("Add");
		
		
		button.addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent event) {
		    	  Page3.this.lowerBound = Integer.parseInt(text1.getText());
		    	  Page3.this.upperBound = Integer.parseInt(text2.getText());
		    	  //create a bar chart data set consisting of the values in the text fields
		    	  
		      }
		    });
	}
}
