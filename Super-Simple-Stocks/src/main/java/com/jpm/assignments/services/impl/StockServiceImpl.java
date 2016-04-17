package com.jpm.assignments.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jpm.assignments.model.Stock;
import com.jpm.assignments.model.Trade;
import com.jpm.assignments.persistence.StocksEntityHolder;
import com.jpm.assignments.services.StockService;

/**
 * @author Bharath Vivekanandan
 *
 */
public class StockServiceImpl implements StockService {

	private Logger logger = Logger.getLogger(StockServiceImpl.class);
	
	private StocksEntityHolder stocksEntityHolder = null;
	
	/**
	 * @param stocksEntityHolder
	 */
	public StockServiceImpl(StocksEntityHolder stocksEntityHolder) {
		this.stocksEntityHolder = stocksEntityHolder;
	}
	
	

	/* (non-Javadoc)
	 * @see com.jpm.assignments.services.StockService#calculateDividendYield(java.lang.String)
	 */
	public double calculateDividendYield(String stockSymbol) throws Exception {
		
		logger.debug("Entering into calculateDividendYield()- stockSymbol: "+stockSymbol);
		double dividendYield = -1.0;
		
		try {
			Stock stock = stocksEntityHolder.getStockBySymbol(stockSymbol);
			if(stock == null) {
				throw new Exception("The stock symbol '"+stockSymbol+"' is not supported by the Simple Stock system.");
			}
			if(stock.getTickerPrice() <= 0.0) {
				throw new Exception("The ticker price for the stock '"+stockSymbol+"' should be greater than zero.");
			}
			dividendYield = stock.getDividendYield();
			logger.debug("dividendYield: "+dividendYield);
			
		} catch (Exception e) {
			logger.error("Error calculating Dividend Yield for the stock symbol: "+stockSymbol+". ", e);
			throw new Exception("Error calculating Dividend Yield for the stock symbol: "+stockSymbol+". ", e);
		}
		return dividendYield;
	}

	/* (non-Javadoc)
	 * @see com.jpm.assignments.services.StockService#calculatePERatio(java.lang.String)
	 */
	public double calculatePERatio(String stockSymbol) throws Exception {
		logger.debug("Entering into calculatePERatio()- stockSymbol: "+stockSymbol);
		double peRatio = -1.0;
		
		try {
			Stock stock = stocksEntityHolder.getStockBySymbol(stockSymbol);
			if(stock == null) {
				throw new Exception("The stock symbol '"+stockSymbol+"' is not supported by the Simple Stock system.");
			}
			if(stock.getTickerPrice() <= 0.0) {
				throw new Exception("The ticker price for the stock '"+stockSymbol+"' should be greater than zero.");
			}
			peRatio = stock.getPeRatio();
			logger.debug("peRatio: "+peRatio);
			
		} catch (Exception e) {
			logger.error("Error calculating P/E Ratio for the stock symbol: "+stockSymbol+". ", e);
			throw new Exception("Error calculating P/E Ratio for the stock symbol: "+stockSymbol+". ", e);
		}
		return peRatio;
	}

	/* (non-Javadoc)
	 * @see com.jpm.assignments.services.StockService#recordTrade(com.jpm.assignments.model.Trade)
	 */
	public boolean recordTrade(Trade trade) throws Exception {
		logger.debug("Entering into recordTrade()- trade: "+trade);
		boolean isRecorded = false;
		try {
			if(trade == null) {
				throw new Exception("Trade object to record should be a valid object and it's null.");
			}
			if(trade.getStock() == null){
				throw new Exception("A trade should be associated with a stock and the stock for the trade is null.");
			}
			if(trade.getSharesQuantity() <= 0){
				throw new Exception("Shares quantity in the trade to record should be greater than zero.");
			}
			if(trade.getPrice() <= 0.0){
				throw new Exception("Shares price in the trade to record should be greater than zero.");
			}
			
			isRecorded = stocksEntityHolder.recordTrade(trade);
			if(isRecorded) {
				trade.getStock().setTickerPrice(trade.getPrice());
			}
		} catch (Exception e) {
			logger.error("Error when trying to record a trade.", e);
			throw new Exception("Error when trying to record a trade.", e);
		}
		return isRecorded;
	}

	/* (non-Javadoc)
	 * @see com.jpm.assignments.services.StockService#calculateStockPriceForPeriod(java.lang.String, int)
	 */
	public double calculateStockPriceForPeriod(String stockSymbol, int mins) throws Exception {
		
		List<Trade> trades = stocksEntityHolder.getTrades();
		Collections.reverse(trades);
		
		Date currentTime = new Date();
		Date startTime = new Date(currentTime.getTime() - (mins * 60 * 1000));
		
		double sumOfPriceAndQty = 0.0;
		int sumOfQty = 0;
		
		for(Trade trade : trades)
		{
			if(trade.getTimeStamp().after(startTime)
					&& trade.getStock().getStockSymbol().equals(stockSymbol))
			{
				sumOfPriceAndQty += (trade.getPrice() * trade.getSharesQuantity());
				sumOfQty += trade.getSharesQuantity();
			}
		}
		
		double stockPrice = 0.0;
		if(sumOfQty > 0) {
			stockPrice = sumOfPriceAndQty / sumOfQty;
		}
		return stockPrice;
	}
	
	/**
	 * @param stockSymbol
	 * @return
	 * @throws Exception
	 */
	public double calculateStockPrice(String stockSymbol) throws Exception {
		
		List<Trade> trades = stocksEntityHolder.getTrades();
		Collections.reverse(trades);
		
		double sumOfPriceAndQty = 0.0;
		int sumOfQty = 0;
		
		for(Trade trade : trades)
		{
			if(trade.getStock().getStockSymbol().equals(stockSymbol))
			{
				sumOfPriceAndQty += (trade.getPrice() * trade.getSharesQuantity());
				sumOfQty += trade.getSharesQuantity();
			}
		}
		
		double stockPrice = 0.0;
		if(sumOfQty > 0) {
			stockPrice = sumOfPriceAndQty / sumOfQty;
		}
		return stockPrice;
	}

	/* (non-Javadoc)
	 * @see com.jpm.assignments.services.StockService#calculateGBCEAllShareIndex()
	 */
	public double calculateGBCEAllShareIndex() throws Exception {
		logger.debug("Entering into calculateGBCEAllShareIndex()");
		double geometricMean = 0.0;
		
		Map<String, Stock> stocks = stocksEntityHolder.getStocks();
		List<Double> stockPrices = new ArrayList<Double>();
		
		for(String stockSymbol : stocks.keySet()) {
			double stockPrice = calculateStockPrice(stockSymbol);
			if(stockPrice > 0) {
				stockPrices.add(stockPrice);
			}
		}
			
		double multiOfStockPrices = 1.0;
		for(Double stockPrice : stockPrices) {
			multiOfStockPrices *= stockPrice;
		}
		
		geometricMean = Math.pow(multiOfStockPrices, 1.0/stockPrices.size());//cast as double
		return geometricMean;
	}


}
