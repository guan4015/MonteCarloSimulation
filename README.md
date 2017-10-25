# MonteCarloSimulation
This project simulates the assets' prices and computes the options prices. The simulation takes the option object, length of the period, z-score and
error permission as input and compute the options price.

## Function/Class Description

This project consists of five .java files.

*  The RandomVectorGenerator.java defines the interface to generate random vectors.
*  The NormalRandomVectorGenerator.java defines the class to generate a sequence of standard normal random numbers using ArrayList.
*  The AntitheticRandomVectorGenerator.java defines the class as decorator to generate the reflection of path.
*  The StockPath.java defines the interface that can generate stock prices path.
*  The StockPathExponentialBrownian.java extends StockPath.java and it defines the class the stock prices path that follows the geometric brownian motion.
*  The PayOut.java defines the interface to compute the payoff.
*  The CallPayOut.java extends PayOut.java and it defines the class that compute the payoff of options based on the stock prices path.
*  The pairPrice.java defines a data structure that pair the price and date. In this assignment, we assume that the date is an integer.
*  The Option.java defines the option class that encapsulate basic member data.
*  The StatsCollector.java defines the class that compute the standard deviation and mean for a sequence of number incrementally.
*  The Simulation.java defines the class that simulate the stock prices and compute the payoff for the option.

## Monte Carlo Simulation Implementation

The simulation.java is used to compute the option prices. 

### Calling the function.

To run the simulation, we first need to create a option, for example, 

```
Option<Integer, Integer> IBM_eu = new Option<Integer, Integer>("IBM","European",0.0001,152.35,0.01,165);
```
In this example, we create a option with two parameters Integer, which means that the starttime and endtime are integers as well as duration.
The name of the option is "IBM", whose underlying asset is the equity from IBM traded on NASDAQ. The type of the option is European. The interest rate
is 0.0001 per day. The initial underlying price is 152.35. The volatility of the underlying is 0.01 per day. The strike price is 165.

Aftermath, we should specify the error of the result, the confidence level and length of the period. 

```
Simulation IBM_european = new Simulation(IBM_eu, period, probability, error/2);
IBM_european.simulate();
```
Probability specifies the p-value we would like to use. The reason that we divide error by 2 is that we would like to test the two sided error.
It means that the absolute value of the distance between the estimated value and true value is less than error/2 has probability p-value.
Finally, we obtain the result that 
```
European = 6.2131487545584925
Asian = 2.1785994932744974

```

## Authors

* **Xiao Guan** - *Initial work* - [KMeans](https://github.com/guan4015/KMeans)


## Acknowledgments

The author thanks Professor Lee Maclin for his help on this assignment.
