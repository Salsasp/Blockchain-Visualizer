import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TimeZone;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
/**
 * @author Dakota Gee
 *
 */
public class Blocks implements Comparable<Blocks>{
	private int number;
	private String miner;
	private long timestamp;
	private ArrayList<Transaction> transactions;
	private int transactionCount;
	private static ArrayList<Blocks> blocks = new ArrayList<Blocks>();
	private final static char DELIMITER = ',';
	private String outputString;

	/**
	 * Class constructor
	 */
	public Blocks()
	{
		outputString = "Empty Block";
	}
	public Blocks(int number)
	{
		this.number = number;
		outputString = "Block Number: " + number;
	}
	public Blocks(int number, String miner)
	{
		this.number = number;
		this.miner = miner;
		outputString = "Block Number: " + number + " Miner Address: " + miner;
	}
	public Blocks (int number, String miner, long timestamp, int transactionCount)
	{
		this.number = number;
		this.miner = miner;
		this.timestamp = timestamp;
		this.transactionCount = transactionCount;
		outputString = "Block Number: " + number + " Miner Address: " + miner;
		try {
			this.readTransactions("ethereumtransactions1.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * getter method for the number field
	 * @return the Blocks number field
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * getter method for the miner field
	 * @return the Blocks miner field
	 */
	public String getMiner() {
		return miner;
	}
	/**
	 * getter method for the transactions field
	 * @return the Blocks transaction field
	 */
	public ArrayList<Transaction> getTransactions()
	{
		ArrayList<Transaction> toReturn = new ArrayList<Transaction>();
		toReturn.addAll(transactions);
		return toReturn;
	}
	public int getTransactionCount()
	{

		return transactionCount;
	}
	/**
	 * Calls and returns the convertTimestamp method.
	 * @return A string returned by the convertTimestamp method
	 */
	public String getDate()
	{
		return convertTimestamp(timestamp);
	}
	/**
	 * getter method for the timestamp field
	 * @return the Blocks timestamp field
	 */
	public long getTimestamp()
	{
		return timestamp;
	}
	@SuppressWarnings("unchecked")
	/**
	 * Returns a copy of the ArrayList field 'Blocks' using a down-casted clone method
	 * @return a copy of the ArrayList field 'Blocks'
	 */
	public static ArrayList<Blocks> getBlocks() {
		ArrayList<Blocks>copy = new ArrayList<Blocks>();
		copy = (ArrayList<Blocks>) blocks.clone();
		return copy;
	}
	public String getOutputString()
	{
		return this.outputString;
	}
/**
 * This method prints all unique miners from the data, and the amount of times each one occurs. Nothing is returned. Takes no arguments.
 */
	public static HashMap<Blocks, Integer> calUniqMiners()
	{
		int dupeCount = 1;
		HashMap<Blocks, Integer> miners = new HashMap<Blocks, Integer>();
		ArrayList<Blocks> dupeBlocks = new ArrayList<Blocks>(100);
		ArrayList<Integer> numDupes = new ArrayList<Integer>(100);
		
		for(int i = 0; i < blocks.size(); i++)
		{
			for(int j = i+1; j < blocks.size(); j++)
			{
				if(blocks.get(i).getMiner().equals(blocks.get(j).getMiner()))
				{
					dupeCount++;
				}
			}
			if(!containsMiner(dupeBlocks, blocks.get(i)))
			{
				dupeBlocks.add(blocks.get(i));
				numDupes.add(dupeCount);
			}
			dupeCount = 1;
		}
		//for loop to output to console
		for(int i = 0; i < dupeBlocks.size(); i++)
		{
			miners.put(dupeBlocks.get(i), numDupes.get(i));
		}
		return miners;
	}
	/**
	 * Returns a primitive integer that is the difference between two Block object parameters, A and B
	 * @param A
	 * @param B
	 * @return the integer difference between Block A and Block B
	 */
	public static int blockDiff(Blocks A, Blocks B)
	{
		return A.getNumber() - B.getNumber();
	}
	
	/**
	 * Takes a number as an integer number as an argument and uses it to retrieve a blocks from the blocks arraylist
	 * @param num: the number of the block the user wishes to get
	 * @return The block specified by the int num
	 */
	public static Blocks getBlockByNumber(int num)
	{
		for(int i = 0; i < blocks.size(); i++)
		{
			if(blocks.get(i).getNumber() == num)return blocks.get(i);
		}
		return null;
	}
	
	public String toString()
	{
		return this.getOutputString();
	}
	/**
	 * This method parses specific data (miner, number) out of a text file, and stores the values in class data.
	 * @param filename
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static void readFile(String filename) throws FileNotFoundException
	{
		Scanner fileScan = new Scanner(new File(filename));
		String line;
		ArrayList<Blocks> copyBlocks = new ArrayList<Blocks>();
		int delimCounter = 0;
		int[] delimIndices = new int[18];
		int number;
		String miner;
		long timestamp;
		int transactions;
		
		//loops through every line in the .txt file
		while(fileScan.hasNextLine())
		{
			line = fileScan.nextLine();
			
			//loops through every character in a line to find delimiter indices
			for(int i = 0; i < line.length(); i++)
			{
				if(line.charAt(i) == DELIMITER)
				{
					delimIndices[delimCounter] = i;
					delimCounter++;
				}
			}
			number = Integer.parseInt(line.substring(0, delimIndices[0]));

			miner = line.substring(delimIndices[8] + 1, delimIndices[9]);
			
			timestamp = Long.parseLong(line.substring(delimIndices[15]+1, delimIndices[16]));
			
			transactions = Integer.parseInt(line.substring(delimIndices[16]+1, delimIndices[17]));

			Blocks block = new Blocks(number, miner, timestamp, transactions);
			copyBlocks.add(block);
			delimCounter = 0;
		}
		blocks = (ArrayList<Blocks>) copyBlocks.clone();
	}
	
	public static void sortBlocksByNumber()
	{
		Collections.sort(blocks);
	}
	
	/**
	 * This method prints the long difference between the times of two blocks to the console
	 * @param first: a Blocks object
	 * @param second: a Blocks object
	 */
	public static void timeDiff(Blocks first, Blocks second)
	{
		long secDiff;
		long hours;
		long minutes;
		long seconds;
		try {
		if(first.getTimestamp() > second.getTimestamp())
		{
			secDiff = first.getTimestamp()-second.getTimestamp();
		}
		else if (second.getTimestamp() > first.getTimestamp())
		{
			secDiff = second.getTimestamp()-first.getTimestamp();
		}
		else secDiff = 0;
		hours = secDiff/3600;
		minutes = (secDiff%3600)/60;
		seconds = ((secDiff%3600)%60);
		
		String hoursString = " hours, ";
		if(hours == 1)hoursString = " hour, ";
		String minutesString = " minutes, ";
		if(minutes == 1)minutesString = " minute, ";
		String secondsString = " seconds.";
		if(seconds == 1)secondsString = " second.";
		
		System.out.println("The difference in time between Block " + first.getNumber() + " and Block " + second.getNumber()
				 + " is " + hours + hoursString + minutes + minutesString + "and " + seconds + secondsString);
		}
		catch(NullPointerException e) {System.out.println("A given Block is null.");}
	}
	
	/**
	 * This method calculates the amount of transactions that have occurred between two blocks.
	 * If the blocks are adjacent, 0 is returned. A NullPointerException is thrown if the parameters are null.
	 * @param first: a Blocks object
	 * @param second: a Blocks object
	 * @return The total amount of transactions that have occurred between two blocks. 0, -1, are returned for exceptions.
	 */
	public static int transactionDiff(Blocks first, Blocks second)
	{
		sortBlocksByNumber();
		boolean inRange = false;
		int lowerBound, upperBound;
		int total = 0;
		try {
		if(first == null || second == null)throw new NullPointerException();
		}
		catch(NullPointerException e) {return -1;}
		if(first.getNumber() < second.getNumber())
		{
			lowerBound = first.getNumber();
			upperBound = second.getNumber();
		}
		else if(second.getNumber() == first.getNumber()+1)
		{
			return 0;
		}
		else return -1;
		
		for(Blocks block : blocks)
		{
			if(block.getNumber() == lowerBound)
			{
				inRange = true;
				continue;
			}
			if(block.getNumber() == upperBound)break;
			if(inRange)total+=block.getTransactionCount();
		}
		return total;
		
	}
	/**
	 * This is a helper method that is an alternative to the Collections contains() method. I found this necessary because the contains() method uses the == operator, while I wanted to compare strings.
	 * @param blocks
	 * @param block
	 * @return A boolean true if given array list parameter contains the miner value of the given block parameter, and false if not
	 */
	public static boolean containsMiner(ArrayList<Blocks> blocks, Blocks block)
	{
		for(int i = 0; i < blocks.size(); i++)
		{
			if(blocks.get(i).getMiner().equals(block.getMiner()))return true;
		}
		return false;
	}
	
	/**
	 * This method uses the SimpleDateFormat class to convert a unix timestamp to a readable date.
	 * @param timestamp: a long number
	 * @return A formatted string based on the SimpleDateFormat class.
	 */
	public String convertTimestamp(long timestamp)
	{
		Date date = new Date(timestamp*1000);
		SimpleDateFormat sdf = new SimpleDateFormat("E, d MMMM YYYY kk:mm:ss z");
		sdf.setTimeZone(TimeZone.getTimeZone("CST"));
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
	@Override
	public int compareTo(Blocks block) {
		if(this.getNumber() > block.getNumber())return 1;
		if(this.getNumber() < block.getNumber())return -1;
		return 0;
	}
	
	/**
	 * This method parses info out of a .txt and assigns the data to class data
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public void readTransactions(String filename) throws FileNotFoundException
	{
		transactions = new ArrayList<Transaction>();
		Scanner fileScan = new Scanner(new File(filename));
		String line;
		Set<Transaction> transactionSet = new HashSet<Transaction>();
		int delimCounter = 0;
		int[] delimIndices = new int[18];
		int blockNumber, index, gasLimit;
		long gasPrice;
		String fromAdr, toAdr;
		
		//loops through every line in the .txt file
		while(fileScan.hasNextLine())
		{
			line = fileScan.nextLine();
			if(line.isEmpty())continue;
			//loops through every character in a line to find delimiter indices
			for(int i = 0; i < line.length(); i++)
			{
				if(line.charAt(i) == DELIMITER)
				{
					delimIndices[delimCounter] = i;
					delimCounter++;
				}
			}
			blockNumber = Integer.parseInt(line.substring(delimIndices[2] + 1, delimIndices[3]));
			if(blockNumber != this.getNumber())
			{
				delimCounter = 0;
				continue;
			}
			index = Integer.parseInt(line.substring(delimIndices[3] + 1, delimIndices[4]));
			
			gasLimit = Integer.parseInt(line.substring(delimIndices[7]+1, delimIndices[8]));
			
			gasPrice = Double.valueOf(line.substring(delimIndices[8]+1, delimIndices[9])).longValue();
			
			fromAdr = line.substring(delimIndices[4]+1, delimIndices[5]);
			
			toAdr = line.substring(delimIndices[5]+1, delimIndices[6]);

			Transaction transaction = new Transaction(blockNumber, index, gasLimit, gasPrice, fromAdr, toAdr);
			transactionSet.add(transaction);
			delimCounter = 0;
		}
		transactions.addAll(transactionSet);
		Collections.sort(transactions);
		
	}
	/**
	 * This method calculates the average transaction cost of all transactions of a block
	 * @return the average transaction cost of all transactions of a block
	 */
	public double avgTransactionCost()
	{
		int count = 0;
		double sum = 0;
		for(Transaction transaction : transactions)
		{
			sum += transaction.transactionCost();
			count++;
		}
		return sum/count;
	}
	/**
	 * This method prints out every unique fromAddress in a blocks's transaction array coupled with every transaction that shares that fromAddress. It also prints the total cost of transactions.
	 */
	public void uniqFromTo()
	{
		ArrayList<String> from = new ArrayList<>();				// holds unique from addresses
		ArrayList<ArrayList<String>> to = new ArrayList<>();	// holds ArrayLists that hold each to address
		ArrayList<Double> cost = new ArrayList<>();
		double total = 0.0;
		
		String address;	// holds address 
		
		// first find each unique from address
		for (int i = 0; i < this.transactionCount; ++i) {
			address = this.transactions.get(i).getFromAddress();
			// add the address if it is not already there
			if (!(from.contains(address))) {
				from.add(address);
			}
		}
		
		// then fill each ArrayList with unique to addresses
		// loop over from ArrayList 
		for (int i = 0; i < from.size(); ++i) {
			// initialize ArrayList
			ArrayList<String> addr = new ArrayList<String>();
			// loop over all transactions
			for (int j = 0; j < this.transactionCount; ++j) {
				// enter if the transactions from address matches the unique from address
				if (from.get(i).equals(this.transactions.get(j).getFromAddress())) {
					// add to address to the ArrayList in index i of to
					addr.add(this.transactions.get(j).getToAddress());
					// add the cost of this transaction to total
					total += this.transactions.get(j).transactionCost();
				}
			}
			// add ArrayList to ArrayList 
			to.add(addr);
			// add total to cost ArrayList
			cost.add(total);
			// reset total
			total = 0.0;
		}
		
		// visualize which address sent to another unique address "0x___ -> 0x___"
		System.out.println("Each transaction by from address for Block " + this.number + ":\n");
		for (int i = 0; i < from.size(); ++i) {
			System.out.println("From " + from.get(i)); // + " : " + cost.get(i) + " ETH");
			
			Iterator<String> itr = to.get(i).iterator();
			while (itr.hasNext()) {
				System.out.println(" -> " + itr.next());
			}
			// print to 8 decimal points
			System.out.printf("Total cost of transactions: %.8f ETH\n", cost.get(i));
			System.out.println();
			
			
			
		}
	}
}

