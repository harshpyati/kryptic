**Kryptic**

_A simple crypto currency price and portfolio tracker_

**Current Features:** 

1. Add an investment
2. Track prices of the coins you are invested in.

**Technical Details** 

1. REST Server: SpringBoot
2. DB: Postgres 

**Flow:** 

1. User creates an account
2. Adds an investment in it. 
3. Portfolio Details: Total Amount Invested, Net Profit / Net Loss. 

**Enhancements that can be done:** 

1.  Auto add investment either by 
    1. Email Crawling
    2. Integrations with 3rd Party APIs like WazirX, Coinswitch. 

2. Add other investment details apart from Crypto, like Mutual Funds, Stocks etc. 
3. Create a holistic portfolio of total investments done. 
4. Integrate AIs to provide suggestions on where I can I invest/rebalance my funds available. 
5. Divide the whole project into individual microservices that can be auto scaled as and when needed. 
6. Add other technical stuff like Messaging Queues, Caching using Redis etc. 
7. Create a client that can consume this. [UI or Command Line]
8. Document APIs using swagger.json 