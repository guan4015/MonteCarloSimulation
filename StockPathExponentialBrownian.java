/**
 * This class implements the method to generate exponential brownian motion
 */
package MonteCarloSimulation;

import java.awt.Window.Type;
/**
 * @author xiaog
 *
 */
/*Import necessary library*/
import java.util.ArrayList;
import java.util.List;


public class StockPathExponentialBrownian implements StockPath {
	
	private double _interestRate = 0.0001; // Daily interest rate
	private double _volatility = 0.01;  // Daily volatility
	private double _initialPrice;   // The start price of the underlying
	private RandomVectorGenerator _initialRandom;
	private RandomVectorGenerator _antithetic;
	private ArrayList<pairPrice<Integer, Double>> _path;
	
	// Constructors
	public StockPathExponentialBrownian() {}
	
	// Basic constructor receives option and the random number sequence
	public StockPathExponentialBrownian(Option<?, ?> option, RandomVectorGenerator source)
	{
		this._initialRandom = source;
		this._antithetic = new AntiTheticRandomVectorGenerator(this._initialRandom);
		this._interestRate = option.getInterestRate();
		this._volatility = option.getVolatility();
		this._initialPrice = option.getStartPrice();
	}
	
	// Set the attribute of the option
	public void setAttribute(Option<?, ?> option) {
		this._interestRate = option.getInterestRate();
		this._volatility = option.getVolatility();
		this._initialPrice = option.getStartPrice();
	}
	
	// Set the path of the source
	public void setPath(RandomVectorGenerator source) {
		this._initialRandom = source;
		this._antithetic = new AntiTheticRandomVectorGenerator(this._initialRandom);
	}

	// generate sample number from a exponential brownian motion
	private double generateExpentialBrownian(double former, double normalNumber) {
		double current = former * Math.exp(this._interestRate - this._volatility * this._volatility / 2.0 + this._volatility * normalNumber);
		return current;
	}

	@Override
	public ArrayList<pairPrice<Integer, Double>> getPrices() {
		
		// generate random normal number sequence
		double[] antiRandomVec = this._antithetic.getVector();
		// generate price list
		ArrayList<pairPrice<Integer, Double>> pricelist = new ArrayList<pairPrice<Integer, Double>>();
		
		// Input the initial elements
		pricelist.add(new pairPrice<Integer,Double>(0, this._initialPrice));
		
		for (int i_1 = 1; i_1 <= antiRandomVec.length; ++ i_1) {
			double sample = generateExpentialBrownian(pricelist.get(i_1 - 1).getPrice(), antiRandomVec[i_1 - 1]);
			pairPrice<Integer, Double> pair = new pairPrice<Integer, Double>(i_1, sample);
			pricelist.add(pair);
		}
		_path = pricelist;
		return pricelist;
	}
	
	public ArrayList<pairPrice<Integer, Double>> getInternalResult(){
		if (!_path.isEmpty()) {
			return _path;
		}
		return null; 
	}

}
