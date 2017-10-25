/**
 * This class defines a data structure that wrap up date and price (Tier 2)
 */
package MonteCarloSimulation;

/**
 * @author xiaog
 *
 */

public class pairPrice<TypeI, TypeII> {
	
	private TypeI _time;
	private TypeII _price;

	public pairPrice(TypeI time, TypeII price){
		this._time = time;
		this._price = price;
	}
	
    // Setters
	public TypeI getTime() {
		return _time;
	}

	public TypeII getPrice() {
		return _price;
	}
	
	// Modifiers
	public void setTime(TypeI time) {
		this._time = time;
	}
	
	public void setPrice(TypeII price) {
		this._price = price;
	}

}
