package com.jpm.assignments.persistence;

import java.util.List;
import java.util.Map;

import com.jpm.assignments.model.Stock;
import com.jpm.assignments.model.Trade;

/**
 * This StocksEntityHolder interface has the apis to process the stock related data in memory.
 * 
 * @author Bharath Vivekanandan
 *
 */
public interface StocksEntityHolder {
	

	/** This method records the given <code>Trade</code> object.
	 * @param trade <code>Trade</code> 
	 * @return True when the trade is recorded.
	 * @throws Exception
	 */
	public boolean recordTrade(Trade trade) throws Exception;
	
	/**
	 *This method returns the trades which are in memory.
	 * 
	 * @return the list of <code>Trade</code>
	 */
	public List<Trade> getTrades();
	
	/**
	 * This method returns the <code>Stock</code> for the given stock symbol.
	 * 
	 * @param stockSymbol Stock Symbol
	 * @return
	 */
	public Stock getStockBySymbol(String stockSymbol);
	
	/** 
	 * This method returns the stocks which are in memory.
	 * 
	 * @return A map that holds the stock symbol and <code>Stock</code>.
	 */
	public Map<String, Stock> getStocks(); 
	
}
