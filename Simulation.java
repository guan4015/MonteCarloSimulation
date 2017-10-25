/**
 * This class simulate the paths of stock and compute the prices of options
 */
package MonteCarloSimulation;

/**
 * @author xiaog
 *
 */

 
public class Simulation {
	
	@SuppressWarnings("rawtypes")
	
	// Define member data
	protected Option _option;
	protected double _startPrice;
	protected double _strikePrice;
	protected double _probability;
	protected double _interestRate;
	protected double _error;
	protected String _type;
	protected int _length = 252;
	protected int _seed;
	protected int _num_trial = 0;
	
	// Default constructor
	public Simulation() {}
	
	// Constructor receives parameters
	public Simulation(Option<?,?> option, int length, double probability, double error) {
		this._startPrice = option.getStartPrice(); // initial underlying price
		this._strikePrice = option.getStrikePrice();  // strike price of the option
		this._probability = probability;  // p-value (threshold)
		this._error = error;  // two sided statistics error
		this._length = length;  // time period
		this._type = option.getPayOutType();  // payout type "European" or "Asian"
		this._option = option;
		this._interestRate = option.getInterestRate();  // Interest rate

	}
	


	public double simulate() {
		// compute the stopping criteria 
		StatsCollector collector = new StatsCollector();
		// two-sided criteria (z score)
		double criteria = NormalCDFInverse(_probability + (1 - _probability) / 2.0);
		
		double error = Double.MAX_VALUE; 
		
		// While error is greater than 0 and error 

		while (error > this._error || error == 0.0) {
			
			// generate the standard normal random samples.
			NormalRandomVectorGenerator normpath = new NormalRandomVectorGenerator(this._length);
			// generate the stock path based on the standard normal random samples
			StockPathExponentialBrownian BrownianPath = new StockPathExponentialBrownian(this._option, normpath);
			// trial plus 1
			++ this._num_trial;
			// generate payout class
			CallPayOut payout = new CallPayOut(this._option.getStrikePrice(), this._option.getPayOutType());
			// compute the profit
			double profit1 = payout.getPayout(BrownianPath);
			// adding the new data to the collector
			collector.update(profit1);
			// compute the error (In this case, we assume that there exists cases such that error is not zero)
			error = criteria * collector.getStd() / Math.sqrt(this._num_trial);
			++ this._num_trial;
			// Call the second time to use the antithetic paths
			double profit2 = payout.getPayout(BrownianPath);
			// update the statistics
			collector.update(profit2);
			error = criteria * collector.getStd() / Math.sqrt(this._num_trial);
			
		}
        double price = (collector.getMean()) * Math.exp(-this._interestRate * this._length);
		StdOut.println("Final option price: " + price);
		return collector.getMean();
	}
	
	/**
	 * 
	 * @param t
	 * @return the rational approximation of z-score
	 */
	double RationalApproximation(double t)
	{

	    double c[] = {2.515517, 0.802853, 0.010328};
	    double d[] = {1.432788, 0.189269, 0.001308};
	    return t - ((c[2]*t + c[1])*t + c[0]) /
	                (((d[2]*t + d[1])*t + d[0])*t + 1.0);
	}
	/**
	 * 
	 * @param p
	 * @return compute the two-sided z-score of normal distribution
	 */
	double NormalCDFInverse(double p)
	{
	    if (p <= 0.0 || p >= 1.0)
	    {
	    	throw new IllegalArgumentException("p must be between 0.0 and 1.0");
	    }
	 
	    // See article above for explanation of this section.
	    if (p < 0.5)
	    {
	        return -RationalApproximation( Math.sqrt(-2.0 * Math.log(p)) );
	    }
	    else
	    {
	        // F^-1(p) = G^-1(1-p)
	        return RationalApproximation( Math.sqrt(-2.0 * Math.log(1-p)) );
	    }
	}
	
	public static void main(String[] args) {
		
		// Define two objects
		Option<Integer, Integer> IBM_eu = new Option<Integer, Integer>("IBM","European",0.0001,152.35,0.01,165);
		Option<Integer, Integer> IBM_as = new Option<Integer, Integer>("IBM","Asian",0.0001,152.35,0.01,164);
		// p-value, error and length
		double probability = 0.96;		
		double error = 0.1;
		int period = 252;
		// Simulate European option
        StdOut.println("Case 1 European Option Price:");
		Simulation IBM_european = new Simulation(IBM_eu, period, probability, error/2);
		IBM_european.simulate();
		
		// Simulate Asian option
        StdOut.println("Case 2 Asian Option Price:");
        Simulation IBM_asian = new Simulation(IBM_as, period, probability, error/2);
		IBM_asian.simulate();

	}



}
