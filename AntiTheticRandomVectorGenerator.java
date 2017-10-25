/**
 * This class implements the decorator that reflect the random vector (Tier 1)
 */
package MonteCarloSimulation;

/**
 * @author xiaog
 *
 */
public class AntiTheticRandomVectorGenerator implements RandomVectorGenerator {
	
	protected RandomVectorGenerator _vectorGenerator;  // receive a random vector from other resource
	protected Boolean _flag;   // receive the _flag
	protected double[] _currentVector;
	
	// We require a input generator to initialize the decorator
	public AntiTheticRandomVectorGenerator(RandomVectorGenerator source) {
		this._vectorGenerator = source;
		this._flag = false;
	}
	
	public double[] getCurrentVector() {
		return this._currentVector;
	}
	
	public Boolean getFlag() {
		return this._flag;
	}
	public void setFlag(Boolean flag) {
		this._flag = flag;
	}
	
	@Override
	public double[] getVector() { 
		if (this._flag) {
			// Return the flip of the current vector
			this._flag = false;
			double[] newVector = new double[this._currentVector.length];
			for (int i = 0; i < _currentVector.length; ++ i) {
				newVector[i] = (-1)*_currentVector[i];
			}

			return newVector;	
		}
		else {
			// Return the current status of source
			this._flag = true;
			this._currentVector = _vectorGenerator.getVector();	
			return _currentVector;
		}
		
	}

}