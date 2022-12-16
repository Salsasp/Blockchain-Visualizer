import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection; 

public class Page3 extends JPanel{
	int lowerBound = 15049308; //lowest block number in blocks list
	int upperBound = 15049407; //highest block number in blocks list
	Set<CategoryDataset> datasets;
	JPanel panel;
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
	public Page3() throws FileNotFoundException
	{
		super();
		//Blocks.readFile("ethereumP1data.txt");
		panel = new JPanel();
		JFreeChart barChart = ChartFactory.createBarChart("Time Difference Between Blocks", "Blocks", "Time Units (hrs, mins, secs)", createDataset(), PlotOrientation.VERTICAL
				, true, true, false);
		panel = new ChartPanel(barChart);
		add(panel, BorderLayout.EAST);
		add(initializeBoxes(),BorderLayout.WEST);
		setVisible(true);
		
	}
	private CategoryDataset createDataset()
	{
		//implement logic to distinguish time values from getTime array
		double[] timeDiffValues = Blocks.timeDiff(Blocks.getBlockByNumber(lowerBound), Blocks.getBlockByNumber(upperBound));
		//hours data
		dataset.addValue(timeDiffValues[0], "Hours", (String.valueOf(lowerBound) + " - " + String.valueOf(upperBound)));
		//minutes data
		dataset.addValue(timeDiffValues[1], "Minutes", (String.valueOf(lowerBound) + " - " + String.valueOf(upperBound)));
		//seconds data
		dataset.addValue(timeDiffValues[2], "Seconds", (String.valueOf(lowerBound) + " - " + String.valueOf(upperBound)));
		return dataset;
	}
	
	public JPanel initializeBoxes()
	{
		JPanel buttonPanel = new JPanel();
		JTextField text1 = new JTextField();
		text1.setSize(50, 10);
		text1.setText("15049308");
		JTextField text2 = new JTextField();
		text2.setSize(50, 10);
		text2.setText("15049407");
		JButton button = new JButton("Add");
		
		
		button.addActionListener(new ActionListener() {
			@Override
		      public void actionPerformed(ActionEvent event) {
		    	  Page3.this.lowerBound = Integer.parseInt(text1.getText());
		    	  Page3.this.upperBound = Integer.parseInt(text2.getText());
		    	  Page3.this.remove(panel);
		    	  panel = new ChartPanel(ChartFactory.createBarChart("Time Difference Between Blocks", "Blocks", "Time Units (hrs, mins, secs)", createDataset()));
		    	  Page3.this.add(panel);
		    	  Page3.this.repaint();
		    	  Page3.this.revalidate();
		      }
		    });
		buttonPanel.add(text1);
		buttonPanel.add(text2);
		buttonPanel.add(button);
		return buttonPanel;
	}
	public static void main(String[] args) throws FileNotFoundException
	{

	}
}
