package com.jpm.assignments.services.test;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.jpm.assignments.model.Trade;

/**
 * This class has the unit test method to record the trades.
 * 
 * @author Bharath Vivekanandan
 *
 */
public class RecordTradeTest extends AbstractServiceTest {
	
	Logger logger = Logger.getLogger(RecordTradeTest.class);
	
	@Test
	public void recordTradeTest() {

		logger.info("Entering into recordTradeTest()");
		Assert.assertNotNull(stockService);
		Assert.assertNotNull(tradeList);
		logger.info("tradeList size- "+tradeList.size());
		
		int currentTradeCounts = stocksEntityHolder.getTrades().size();
		Assert.assertEquals(currentTradeCounts, 0);
		logger.info("currentTradeCounts-"+currentTradeCounts);
		
		try {
			// Record the trades
			for(Trade trade: tradeList) {
				boolean isRecorded = stockService.recordTrade(trade);
				Assert.assertTrue(isRecorded);
			}
			//The entity holder's trades count should be equal to the given input trade's count.
			currentTradeCounts =  stocksEntityHolder.getTrades().size();
			Assert.assertEquals(currentTradeCounts, tradeList.size());
			
		} catch (Exception e) {
			logger.error(e);
			Assert.assertTrue(false);
		}
		logger.info("Exiting from recordTradeTest() \n");
	}

}
