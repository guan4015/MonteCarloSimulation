/**
 * This class defines the payout of call option
 */
package MonteCarloSimulation;

/**
 * @author xiaog
 *
 */
import java.util.ArrayList;
import java.util.List;

public class CallPayOut implements PayOut{
	protected double _StrikePrice; 
	protected String _type; // Define the type of options "Asian" or "European"
	
	/* constructors */
	public CallPayOut() {
		
	}
	
	public CallPayOut(double StrikePrice, String type) {
		this._StrikePrice = StrikePrice;
		this._type = type;
	}
	
	// Modifiers
	public void setCallType(String type) {
		this._type = type;
	}
	
	public void setCallStrike(double StrikePrice) {
		this._StrikePrice = StrikePrice;
	}
	
	// Selecters
	public String getCallType() {
		return this._type;
	}
	
	public double getCallStrike() {
		return this._StrikePrice;
	}
	
	public void ToString() {
		StdOut.printf("This call option is of type %s and the strike is %d.\n", this._type, this._StrikePrice);
	}
	
	@Override
	public double getPayout(StockPath path) {
		// Obtain the price list
		ArrayList<pairPrice<Integer, Double>> pricelist =  path.getPrices();
		if (this._type.equals("Asian")){
			// For Asian option, we compute the average less the strike price
			double average = pricelist.get(0).getPrice();
			for(int i_1 = 1; i_1 < pricelist.size(); ++ i_1) {
				average = (i_1 * average + pricelist.get(i_1).getPrice())/(i_1 + 1);
			}
			
			return Math.max(average - this._StrikePrice, 0.0);
		} else if (this._type.equals("European")) {
			double endPrice = pricelist.get(pricelist.size()-1).getPrice();
			// to guarantee any number returned is greater or equal to zero
			return Math.max(endPrice - this._StrikePrice,0);
			
		} else {
			StdOut.printf("The type of the options is not included.\n");
			return 0.0;
		}

	}

}
