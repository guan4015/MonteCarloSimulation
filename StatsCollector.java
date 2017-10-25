/**
 * To catch up with the statistics of the array
 */
package MonteCarloSimulation;

/**
 * @author xiaog
 *
 */
import java.util.ArrayList;

public class StatsCollector {
	
	private ArrayList<Double> _pricelist;
	private double _mean ; 
	private double _secondMoment ;
	private double _std ; // standard deviation
	

	public StatsCollector() {
		this._pricelist = new ArrayList<Double>();
	}
	
	// Add the data to the existing data array
	public void update(double data) {
		this._pricelist.add(data);		
		int size = this._pricelist.size();
		
		if(size > 1) {
			this._mean = ((size - 1.0) * _mean + data) / size;
			this._secondMoment = ((size - 1.0) * _secondMoment + data * data) / size;
			this._std = Math.sqrt(_secondMoment * size / (size - 1)- _mean * _mean * size / (size - 1));
		} else {
		this._mean = data;
		this._secondMoment =  data * data;
		this._std = data;
		}
	}
	
	// Getters.
	public double getStd() {
		return this._std;
	}
	
    // Get means
	public double getMean() {
		return this._mean;
	}
	
	// get count
	public int getCount() {
		return this._pricelist.size();
	}

}
