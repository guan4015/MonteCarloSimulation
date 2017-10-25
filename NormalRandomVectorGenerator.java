/**
 * This class implements the method of generating normal random vectors (Tier 1)
 */
package MonteCarloSimulation;

/**
 * @author xiaog
 *
 */
/* Necessary packages */
import java.lang.Object;
import java.util.Random;


public class NormalRandomVectorGenerator implements RandomVectorGenerator {
	
	protected int _length = 1;
	protected double _mean = 0.0;
	protected double _std = 1.0;
	protected long _seed;
	protected double[] _normalVector;
	private Random _initial; 
	
	// Default constructor and constructors with parameters
	public NormalRandomVectorGenerator() {
		this._initial = new Random();
	}
	
	public NormalRandomVectorGenerator(int length) {
		this._initial = new Random();
		this._length = length;
		this._normalVector = new double[this._length];
	}
	
	public NormalRandomVectorGenerator(int length, long seed) {
		this._initial = new Random(seed);
		this._length = length;
		this._seed = seed;
		this._normalVector = new double[this._length];
	}
	
	public NormalRandomVectorGenerator(int length, double mean, double std) {
		this._initial = new Random();
		this._length = length;
		this._mean = mean;
		this._std = std;
		this._normalVector = new double[this._length];
	}
	
	public NormalRandomVectorGenerator(int length, double mean, double std, long seed) {
		this._initial = new Random(seed);
		this._length = length;
		this._mean = mean;
		this._std = std;
		this._seed = seed;
		this._normalVector = new double[this._length];
	}
	// Modifiers
	public void setMean(double mean) {
		this._mean = mean;		
	}
	
	public void setStd(double std) {
		this._std = std;
	}
	
	public void setLength(int length) {
		this._length = length;
		this._normalVector = new double[this._length];
	}
	
	public void setSeed(long seed) {
		this._initial = new Random(seed);
		this._seed = seed;
	}
	// Getters
	public double getMean() {
		return this._mean;
	}
	
	public double getStd() {
		return this._std;
	}
	
	public int getLength() {
		return this._length;
	}

	public long getSeed() {
		return this._seed;
	}
	
	public double[] getResult() {
		return _normalVector;
	}
	/**
	 * 
	 * @return The random vector with _length
	 */
	@Override
	public double[] getVector() {
		
		for (int i_1 = 0; i_1 < this._length; ++i_1) 
		{ 
			_normalVector[i_1] = this._initial.nextGaussian();
		}
		return _normalVector;
	}
	

}
