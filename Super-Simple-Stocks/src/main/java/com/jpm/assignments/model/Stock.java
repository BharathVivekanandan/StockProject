package com.jpm.assignments.model;

import org.apache.log4j.Logger;

/**
 * This class represents the stock.
 * 
 * @author Bharath Vivekanandan
 *
 */
public class Stock {

	private Logger logger = Logger.getLogger(Stock.class);
	
	private String stockSymbol = null;
	
	private StockType stockType = StockType.COMMON;
	
	private double lastDividend = 0.0;
	
	private double fixedDividend = 0.0;
	
	private double parValue = 0.0;
	
	private double tickerPrice = 0.0;
	

	/**
	 * @return stockSymbol.
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * @param stockSymbol
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	/**
	 * @return stockType
	 */
	public StockType getStockType() {
		return stockType;
	}

	/**
	 * @param stockType
	 */
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	/**
	 * @return lastDividend
	 */
	public double getLastDividend() {
		return lastDividend;
	}

	/**
	 * @param lastDividend
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * @return fixedDividend
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * @param fixedDividend
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	/**
	 * @return parValue
	 */
	public double getParValue() {
		return parValue;
	}

	/**
	 * @param parValue
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	/**
	 * @return tickerPrice
	 */
	public double getTickerPrice() {
		return tickerPrice;
	}

	/**
	 * @param tickerPrice
	 */
	public void setTickerPrice(double tickerPrice) {
		logger.debug("Setting ticker price to: "+tickerPrice);
		this.tickerPrice = tickerPrice;
		logger.debug("Ticker price for "+stockSymbol+": "+tickerPrice);
	}
	
	/**
	 * This method calculates the dividend yield for the different stock types.
	 * 
	 * @return dividendYield
	 */
	public double getDividendYield() {
		
		double dividendYield = -1.0;
		if(tickerPrice > 0.0) {
			
			if(stockType.equals(StockType.COMMON)) {
				dividendYield = lastDividend / tickerPrice;
			} else {
				dividendYield = (fixedDividend * parValue ) / tickerPrice;
			}
		}
		return dividendYield;
	}
	
	/**
	 * This method calculates the P/E Ratio for the stock.
	 *  
	 * @return peRatio 
	 */
	public double getPeRatio()
	{
		double peRatio = -1.0;
		if(tickerPrice > 0.0) {
			peRatio = tickerPrice / getDividendYield();
		}
		return peRatio;
	}
	
	@Override
	public String toString() {
		String pattern = "Stock Object [stockSymbol: %s, stockType: %s, lastDividend: %4.0f, fixedDividend: %4.2f, parValue: %5.2f]";
		return String.format(pattern, stockSymbol, stockType, lastDividend, fixedDividend, parValue);
	}
	
	
}
