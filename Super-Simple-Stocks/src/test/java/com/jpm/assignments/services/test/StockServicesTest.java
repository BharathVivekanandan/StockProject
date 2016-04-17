package com.jpm.assignments.services.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jpm.assignments.model.Trade;

/**
 * This class has the unit test methods to calculate dividend yield, P/E ratio,
 *  stock price for the given stock and to calculate the GBCE allShareIndex.
 *  
 *  The ensureTradesRecorded() method should be executed before the other methods.
 *  So that the other methods will get the inputs like TickePrice, Trade shares quantity, etc. 
 * 
 * @author Bharath Vivekanandan
 *
 */
public class StockServicesTest extends AbstractServiceTest {

	Logger logger = Logger.getLogger(StockServicesTest.class);
	
	@Before
	public void ensureTradesRecorded() {
	
		logger.info("Entering into ensureTradesRecorded()");
		int currentTradeCounts = stocksEntityHolder.getTrades().size();
		logger.info("currentTradeCounts-"+currentTradeCounts);
		if(currentTradeCounts == 0) {
			try {
				// Record the trades
				for(Trade trade: tradeList) {
					boolean isRecorded = stockService.recordTrade(trade);
					logger.info("isRecorded-"+isRecorded);
				}
				currentTradeCounts =  stocksEntityHolder.getTrades().size();
				logger.info("After trades are recorded, currentTradeCounts-"+currentTradeCounts);
				
			} catch (Exception e) {
				logger.error(e);
			}
		}
		logger.info("Exiting from ensureTradesRecorded() \n");
	}
	
	
	@Test
	/**
	 * This method is used to unit test the dividendYield calculation.
	 * 
	 */
	public void dividendYieldTest()
	{
		logger.info("Entering into dividendYieldTest()");
		Assert.assertNotNull(stockService);
		
		List<String> stockSymbols = new ArrayList<String>();
		stockSymbols.add("TEA");
		stockSymbols.add("ALE");
		stockSymbols.add("GIN");
		
		try {
			for(String stockSymbol: stockSymbols)
			{
				double dividendYield = stockService.calculateDividendYield(stockSymbol);
				logger.info("dividendYield for stock '" + stockSymbol + "' : " + dividendYield);
				Assert.assertTrue(dividendYield >= 0.0);
			}
		} catch (Exception e) {
			logger.error(e);
			Assert.assertTrue(false);
		}
		logger.info("Exiting from dividendYieldTest() \n");
	}
	
	@Test
	/**
	 * This method is used to unit test the P/E ratio calculation.
	 * 
	 */
	public void calculatePERatioTest()
	{
		logger.info("Entering into calculatePERatioTest()");
		Assert.assertNotNull(stockService);
		
		List<String> stockSymbols = new ArrayList<String>();
		stockSymbols.add("TEA");
		stockSymbols.add("ALE");
		stockSymbols.add("GIN");
		
		try {
			for(String stockSymbol: stockSymbols)
			{
				double peRatio = stockService.calculatePERatio(stockSymbol);
				logger.info("peRatio for stock '" + stockSymbol + "' : " + peRatio);
				Assert.assertTrue(peRatio >= 0.0);
			}
		} catch (Exception e) {
			logger.error(e);
			Assert.assertTrue(false);
		}
		logger.info("Exiting from calculatePERatioTest() \n");
	}
	
	@Test
	/**
	 * This method is used to unit test the stock price calculation.
	 */
	public void calculateStockPriceTest() {
		logger.info("Entering into calculateStockPriceTest()");
		Assert.assertNotNull(stockService);
		
		List<String> stockSymbols = new ArrayList<String>();
		stockSymbols.add("GIN");
		
		try {
			for(String stockSymbol: stockSymbols)
			{
				double stockPrice = stockService.calculateStockPriceForPeriod(stockSymbol, 15);
				logger.info("stockPrice for stock '" + stockSymbol + "' : " + stockPrice);
				Assert.assertTrue(stockPrice >= 0.0);
			}
		} catch (Exception e) {
			logger.error(e);
			Assert.assertTrue(false);
		}
		
		logger.info("Exiting from calculateStockPriceTest() \n");
	}
	
	@Test
	/**
	 * This method is used to unit test the allShareIndex calculation.
	 */
	public void calculateGBCEAllShareIndexTest() {
		
		logger.info("Entering into calculateGBCEAllShareIndexTest()");
		Assert.assertNotNull(stockService);
		
		try {
			double allShareIndex = stockService.calculateGBCEAllShareIndex();
			logger.info("GBCE All Share Index: " + allShareIndex);
		} catch (Exception e) {
			logger.error(e);
			Assert.assertTrue(false);
		}
		logger.info("Exiting from calculateGBCEAllShareIndexTest() \n");
	}
}
