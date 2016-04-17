package com.jpm.assignments.persistenceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jpm.assignments.model.Stock;
import com.jpm.assignments.model.Trade;
import com.jpm.assignments.persistence.StocksEntityHolder;

/**
 * @author Bharath Vivekanandan
 *
 */
public class StocksEntityHolderImpl implements StocksEntityHolder {

	private Logger logger = Logger.getLogger(StocksEntityHolderImpl.class);
	
	private List<Trade> trades = new ArrayList<Trade>();

	private Map<String, Stock> stocks = new HashMap<String, Stock>();
	
	
	
	/* (non-Javadoc)
	 * @see com.jpm.assignments.persistence.StocksEntityHolder#recordTrade(com.jpm.assignments.model.Trade)
	 */
	public boolean recordTrade(Trade trade) throws Exception {
		
		boolean isAdded = false;
		try {
			isAdded = trades.add(trade);
		} catch (Exception e) {
			throw new Exception("Exception caught while recording a trade", e);
		}
		return isAdded;
	}

	/* (non-Javadoc)
	 * @see com.jpm.assignments.persistence.StocksEntityHolder#getTrades()
	 */
	public List<Trade> getTrades() {
		return trades;
	}

	/* (non-Javadoc)
	 * @see com.jpm.assignments.persistence.StocksEntityHolder#getStockBySymbol(java.lang.String)
	 */
	public Stock getStockBySymbol(String stockSymbol) {
		
		Stock stock = null;
		try {
			if(stockSymbol != null) {
				stock = stocks.get(stockSymbol);
			}
		} catch (Exception e) {
			logger.error("Exception caught while fetching the stock object"
					+ " for the stock symbol: "+stockSymbol+". ", e);
		}
		return stock;
	}

	/* (non-Javadoc)
	 * @see com.jpm.assignments.persistence.StocksEntityHolder#getStocks()
	 */
	public Map<String, Stock> getStocks() {
		return stocks;
	}
	
	

}
