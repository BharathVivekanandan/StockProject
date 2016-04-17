package com.jpm.assignments.services;

import com.jpm.assignments.model.Trade;

/**
 * This StockService interface has the apis to perform different stock related to operations.
 * 
 * @author Bharath Vivekanandan
 *
 */
public interface StockService {
	
	/**
	 * This method calculates the dividend yield for the given stock.
	 * 
	 * @param stockSymbol Stock Symbol
	 * @return A double value which represents the dividend yield.
	 * @throws Exception
	 */
	public double calculateDividendYield(String stockSymbol) throws Exception;
	
	/**
	 * This method calculates the P/E Ratio for the given stock.
	 * 
	 * @param stockSymbol Stock Symbol
	 * @return A double value which represents the P/E ratio.
	 * @throws Exception
	 */
	public double calculatePERatio(String stockSymbol) throws Exception;
	
	/**
	 * This method records the given trade. This will hold the trade value in the memory.
	 * 
	 * @param trade The <code>Trade</code> object to record.
	 * @return true if the trade is recorded.
	 * @throws Exception
	 */
	public boolean recordTrade(Trade trade) throws Exception;
	
	/**
	 * This method calculates the stock price for the given stock which is traded 
	 * in the last given minutes.
	 * 
	 * @param stockSymbol Stock Symbol
	 * @param mins The minutes to find the recently recorded trades. 
	 * @return A double value which represents the stock price.
	 * @throws Exception
	 */
	public double calculateStockPriceForPeriod(String stockSymbol, int mins) throws Exception;
	
	/**
	 * This method calculates the GBCE All Share Index using 
	 * the geometric mean of prices for all stocks.
	 * 
	 * @return A double value which represents the allShareIndex.
	 * @throws Exception
	 */
	public double calculateGBCEAllShareIndex() throws Exception;
}
