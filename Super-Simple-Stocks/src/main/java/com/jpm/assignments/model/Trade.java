package com.jpm.assignments.model;

import java.util.Date;

/**
 * This class represents the Trade.
 * 
 * @author Bharath Vivekanandan
 *
 */
public class Trade {

	private Date timeStamp = null;
	
	private Stock stock = null;
	
	private BuySellIndicator indicator = BuySellIndicator.BUY;
	
	private int sharesQuantity = 0;
	
	private double price = 0.0;

	/**
	 * @return timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return stock
	 */
	public Stock getStock() {
		return stock;
	}

	/**
	 * @param stock
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	/**
	 * @return indicator
	 */
	public BuySellIndicator getIndicator() {
		return indicator;
	}

	/**
	 * @param indicator
	 */
	public void setIndicator(BuySellIndicator indicator) {
		this.indicator = indicator;
	}

	/**
	 * @return sharesQuantity
	 */
	public int getSharesQuantity() {
		return sharesQuantity;
	}

	/**
	 * @param sharesQuantity
	 */
	public void setSharesQuantity(int sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}

	/**
	 * @return price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		String pattern = "Trade Object [timeStamp: %tF %tT, stock: %s, indicator: %s, shares quantity: %7d, price: %8.2f]";
		return String.format(pattern, timeStamp,timeStamp, stock, indicator, sharesQuantity, price);
	}
}
