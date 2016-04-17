package com.jpm.assignments.services.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.jpm.assignments.model.BuySellIndicator;
import com.jpm.assignments.model.Stock;
import com.jpm.assignments.model.StockType;
import com.jpm.assignments.model.Trade;
import com.jpm.assignments.persistence.StocksEntityHolder;
import com.jpm.assignments.persistence.impl.StocksEntityHolderImpl;
import com.jpm.assignments.services.StockService;
import com.jpm.assignments.services.impl.StockServiceImpl;

/**
 * This abstract test class has the method to get the stock, trade data and stockService object. 
 *  
 * 
 * @author Bharath Vivekanandan
 *
 */
public abstract class AbstractServiceTest {
	
	Logger logger = Logger.getLogger(AbstractServiceTest.class);
	
	static StockService stockService = null;
	static StocksEntityHolder stocksEntityHolder = null;
	static Map<String, Stock> stocks = null;
	static List<Trade> tradeList = null;
	
	
	@BeforeClass
	public static void loadInputs() {
		getStockService();
		getTrades();
	}
	
	
	public static void getStockService() {
		
		Stock stockTEA = new Stock();
		stockTEA.setStockSymbol("TEA");
		stockTEA.setStockType(StockType.COMMON);
		stockTEA.setLastDividend(0);
		stockTEA.setFixedDividend(0);
		stockTEA.setParValue(100);
		
		Stock stockPOP = new Stock();
		stockPOP.setStockSymbol("POP");
		stockPOP.setStockType(StockType.COMMON);
		stockPOP.setLastDividend(8);
		stockPOP.setFixedDividend(0);
		stockPOP.setParValue(100);
		
		Stock stockALE = new Stock();
		stockALE.setStockSymbol("ALE");
		stockALE.setStockType(StockType.COMMON);
		stockALE.setLastDividend(23);
		stockALE.setFixedDividend(0);
		stockALE.setParValue(60);
		
		Stock stockGIN = new Stock();
		stockGIN.setStockSymbol("GIN");
		stockGIN.setStockType(StockType.PREFERRED);
		stockGIN.setLastDividend(8);
		stockGIN.setFixedDividend(0.02);
		stockGIN.setParValue(100);
		
		Stock stockJOE = new Stock();
		stockJOE.setStockSymbol("JOE");
		stockJOE.setStockType(StockType.COMMON);
		stockJOE.setLastDividend(13);
		stockJOE.setFixedDividend(0);
		stockJOE.setParValue(250);
		
		stocks = new HashMap<String, Stock>();
		stocks.put("TEA", stockTEA);
		stocks.put("POP", stockPOP);
		stocks.put("ALE", stockALE);
		stocks.put("GIN", stockGIN);
		stocks.put("JOE", stockJOE);
		
		stocksEntityHolder = new StocksEntityHolderImpl();
		stocksEntityHolder.getStocks().putAll(stocks);
		
		stockService = new StockServiceImpl(stocksEntityHolder);
	}
	
	public static void getTrades()
	{
		Trade trade1 = new Trade();
		trade1.setTimeStamp(getAdjustedTime(-18));
		trade1.setStock(stocks.get("TEA"));
		trade1.setIndicator(BuySellIndicator.SELL);
		trade1.setSharesQuantity(20);
		trade1.setPrice(10.50);
		
		Trade trade2 = new Trade();
		trade2.setTimeStamp(getAdjustedTime(-12));
		trade2.setStock(stocks.get("POP"));
		trade2.setIndicator(BuySellIndicator.BUY);
		trade2.setSharesQuantity(80);
		trade2.setPrice(7.56);
		
		Trade trade3 = new Trade();
		trade3.setTimeStamp(getAdjustedTime(-8));
		trade3.setStock(stocks.get("GIN"));
		trade3.setIndicator(BuySellIndicator.BUY);
		trade3.setSharesQuantity(10);
		trade3.setPrice(10.20);
		
		
		Trade trade4 = new Trade();
		trade4.setTimeStamp(getAdjustedTime(-4));
		trade4.setStock(stocks.get("GIN"));
		trade4.setIndicator(BuySellIndicator.BUY);
		trade4.setSharesQuantity(10);
		trade4.setPrice(5);
		
		Trade trade5 = new Trade();
		trade5.setTimeStamp(getAdjustedTime(-2));
		trade5.setStock(stocks.get("ALE"));
		trade5.setIndicator(BuySellIndicator.SELL);
		trade5.setSharesQuantity(40);
		trade5.setPrice(20.00);
		
		tradeList = new ArrayList<Trade>();
		tradeList.add(trade1);
		tradeList.add(trade2);
		tradeList.add(trade3);
		tradeList.add(trade4);
		tradeList.add(trade5);
	}
	
	public static Date getAdjustedTime(int minutes){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		return now.getTime();
	}
	
	@AfterClass
	public static void clearInputs()
	{
		stocks = null;
		tradeList = null;
		stocksEntityHolder = null;
		stockService = null;
	}
	

}
