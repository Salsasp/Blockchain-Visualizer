

public class Transaction implements Comparable<Transaction>{
	
	private int blockNumber;
	private int index;
	private int gasLimit;
	private long gasPrice;
	private String returnString;
	private String fromAdr;
	private String toAdr;
	
	public Transaction(int number, int index, int gas, long price, String fromAdr, String toAdr) {
		blockNumber = number;
		this.index = index;
		gasLimit = gas;
		gasPrice = price;
		this.fromAdr = fromAdr;
		this.toAdr = toAdr;
		
		returnString = "Transaction " + this.index + " for Block " + blockNumber;
	}
	
	public int getBlockNumber() {
		return blockNumber;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getGasLimit() {
		return gasLimit;
	}
	
	public long getGasPrice() {
		return gasPrice;
	}
	
	public String getFromAddress() {
		return this.fromAdr;
	}
	
	public String getToAddress() {
		return this.toAdr;
	}
	
	public double transactionCost() {
		// price in terms of GWEI
		double price = this.gasPrice / 1000000000.0;
		// cost in GWEI
		double cost = this.gasLimit * price;
		// cost in ETH
		cost = cost / 1000000000.0;
		
		return cost;
	}
	
	public String toString() {
		return returnString;
	}
	
	@Override
	public int compareTo(Transaction t) {
		// compare based on a transactions index
		Integer x = index;
		Integer y = t.getIndex();
		return x.compareTo(y);
	}
	
}
