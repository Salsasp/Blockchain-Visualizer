import java.awt.BasicStroke;
import java.awt.BorderLayout;
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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;  

public class Page2 extends JFrame{
	boolean box1checked = false;
	boolean box2checked = false;
	boolean box3checked = false;
	JPanel panel;
	
	public Page2() throws FileNotFoundException
	{
		super("Dakota Gee");
		Blocks.readFile("ethereumP1data.txt");
		panel = createChartPanel();
		add(panel, BorderLayout.CENTER);
		add(this.initializeCheckboxes(), BorderLayout.SOUTH);
		setSize(1000,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	private JPanel createChartPanel() throws FileNotFoundException
	{
		String chartTitle = "Ethereum Transaction Data";
		String xAxisLabel = "Blocks";
		String yAxisLabel = "Transaction Cost (ETH)";
		
		XYDataset dataset = createDataset();
		
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);
		
		customizeChart(chart);
		
		return new ChartPanel(chart);
	}
	
	private XYDataset createDataset() throws FileNotFoundException
	{
		XYSeriesCollection datasets = new XYSeriesCollection();
		Blocks blocks = new Blocks();
		blocks.readTransactions("ethereumtransactions1.txt");
		ArrayList<Blocks> transactionData = new ArrayList<Blocks>();
		XYSeries series1 = new XYSeries("Average Transaction Cost");
		XYSeries series2 = new XYSeries("Lowest Transaction Cost");
		XYSeries series3 = new XYSeries("Highest Transaction Cost");
		
		for(Blocks block : Blocks.getBlocks())
		{
			if(block.getNumber() > 15049307 && block.getNumber() < 15049323)transactionData.add(block);
		}
		
		//creating average transaction cost data set
		for(Blocks block : transactionData)
		{
			series1.add(block.getNumber(), block.avgTransactionCost());
		}
		
		//creating lowest transaction cost data set
		double lowest = Double.MAX_VALUE;
		for(Blocks block : transactionData)
		{
			for(Transaction transaction : block.getTransactions())
			{
				if(transaction.transactionCost() < lowest)lowest = transaction.transactionCost();
			}
			series2.add(block.getNumber(),lowest);
		}
		
		//creating highest transaction cost data set
		double highest = 0;
		for(Blocks block : transactionData)
		{
			for(Transaction transaction : block.getTransactions())
			{
				if(transaction.transactionCost() > highest)highest = transaction.transactionCost();
			}
			series3.add(block.getNumber(),highest);
		}
		datasets.addSeries(series1);
		datasets.addSeries(series2);
		datasets.addSeries(series3);
		return datasets;
	}
	
	public JPanel initializeCheckboxes()
	{
		JPanel panel = new JPanel();
		JCheckBox test = new JCheckBox();
		JCheckBox box1 = new JCheckBox("Average Transaction Cost", false);
		JCheckBox box2 = new JCheckBox("Lowest Transaction Cost", false);
		JCheckBox box3 = new JCheckBox("Highest Transaction Cost", false);
		box1.addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent event) {
		        if(event.getStateChange() == 1)Page2.this.box1checked = true;
		        else Page2.this.box2checked = false;
		        Page2.this.remove(Page2.this.panel);
		        try {
		        	Page2.this.panel = Page2.this.createChartPanel();
					Page2.this.add(Page2.this.panel);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		        Page2.this.revalidate();
		        Page2.this.repaint();
		      }
		    });
		box2.addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent event) {
		        if(event.getStateChange() == 1)Page2.this.box2checked = true;
		        else Page2.this.box2checked = false;
		        Page2.this.remove(Page2.this.panel);
		        try {
		        	Page2.this.panel = Page2.this.createChartPanel();
					Page2.this.add(Page2.this.panel);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		        Page2.this.revalidate();
		        Page2.this.repaint();
		      }
		    });
		box3.addItemListener(new ItemListener() {
		      public void itemStateChanged(ItemEvent event) {
		        if(event.getStateChange() == 1)Page2.this.box3checked = true;
		        else Page2.this.box3checked = false;
		        Page2.this.remove(Page2.this.panel);
		        try {
		        	Page2.this.panel = Page2.this.createChartPanel();
					Page2.this.add(Page2.this.panel);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		        Page2.this.revalidate();
		        Page2.this.repaint();
		      }
		    });
		
		panel.add(box1);
		panel.add(box2);
		panel.add(box3);
		panel.setAlignmentY(TOP_ALIGNMENT);
		return panel;
	}
	
	private void customizeChart(JFreeChart chart)
	{
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		
		//set colors
		renderer.setSeriesPaint(0, Color.magenta);
		renderer.setSeriesPaint(1, Color.blue);
		renderer.setSeriesPaint(2, Color.green);
		
		//set background
		plot.setBackgroundPaint(Color.DARK_GRAY);
		
		//set visibility
		renderer.setSeriesVisible(0, box1checked);
		renderer.setSeriesVisible(1, box2checked);
		renderer.setSeriesVisible(2, box3checked);
		
		plot.setRenderer(renderer);
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException
	{
		new Page2().setVisible(true);
	}
}
