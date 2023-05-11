# Crypto-urrencyWatcher
REST service for viewing cryptocurrency quotes
Functionality:

View a list of available cryptocurrencies (REST method).
View the current price for a specified cryptocurrency (REST method - the currency code is passed by the user).
Log a message if the price changes by more than 1%. To do this, the user registers themselves using the "notify" REST method and passes their username and cryptocurrency code. At the time of registration, the current price of the specified cryptocurrency is saved with a reference to the user. When the actual price for the specified currency changes by more than 1%, a WARN level message is output to the server log, which specifies the currency code, username, and percentage price change since registration.
Requirements:

You can use Java.
Use Spring Boot.
Store current prices in a relational database Mysql.
The list of available cryptocurrencies is predetermined and is part of the server configuration.
List of currencies: [{"id":"90","symbol":"BTC"}, {"id":"80","symbol":"ETH"}, {"id":"48543","symbol":"SOL"}]
Once a minute, current prices for available cryptocurrencies are requested from the external source CoinLore and stored in the database.
To obtain the current price for a cryptocurrency code, use the open API Crypto API | CoinLore, Method Ticker (Specific Coin): https://api.coinlore.net/api/ticker/?id=90 (BTC)
