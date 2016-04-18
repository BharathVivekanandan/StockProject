# Super Simple Stocks

# 1. Assignment

# 1.1 Requirements
    1.	Provide working source code that will :-
   
        a.	For a given stock, 
            i.	calculate the dividend yield
            ii.	calculate the P/E Ratio
            iii.	record a trade, with timestamp, quantity of shares, buy or sell indicator and price
            iv.	Calculate Stock Price based on trades recorded in past 15 minutes
        b.	Calculate the GBCE All Share Index using the geometric mean of prices for all stocks


# 1.2 Constraints & Notes
1.	Written in one of these languages:
    - Java, C#, C++, Python
2.	No database or GUI is required, all data need only be held in memory
3.	Formulas and data provided are simplified representations for the purpose of this exercise

# 2. Design

A service layer is provided with the required operations. So that this serivce layer can be integrated with other services.

A service named 'StockService' is created with the operations to calculate the dividend yield, P/E Ratio, stock price, and record trades for a given stock.
This also has the operation to calculate the GBCE all Share Index of the all stocks.

The service 'StocksEntityHolder' is created with the operations to hold the stock and trade realted data and to retrieve those data. 
The 'StockService' serice uses the 'StockEntityHolder' service as a persistence layer.

The 'StockServiceImpl' class implements the operations which are provided in the 'StockService' interface.

Ex:  
stockService.recordTrade(trade);

The above example shows the stockSerive's api to record a trade. This service calls the StocksEntityHolder to record the trade data.

### 3. Unit Test
The following test classes are created with junit framework.

* RecordTradeTest.java - This class has the unit test method to record the trades.
* StockServicesTest.java  - 
 This class has the unit test methods to calculate dividend yield, P/E ratio,  stock price for the given stock and to calculate the GBCE allShareIndex.


### 4. Run

The build tool 'MAVEN' is used in this application.

The pom.xml is configured with the requried dependecies like log4j and junit.

Running MAVEN 'clean install' will pull the required dependency jars from the maven central repository and will compile and create the 'Super-Simple-Stocks.jar'.

TEST Report: This will also generate the surfire-reports for the test execution result.








