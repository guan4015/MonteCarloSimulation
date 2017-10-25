/**
 * This class defines the Option builder with two generic TypeI and TypeII, which specify the starttime and duration
 */
package MonteCarloSimulation;

/**
 * @author xiaog
 *
 */



public class Option<TypeI,TypeII> {
	private String _optionName = null;
	private double _interestRate = 0.0;
	private double _volatility = 0.0;
	private double _strikePrice = 0.0;
	private double _startPrice = 0.0;
	private TypeI _startTime = null;
	private TypeI _endTime = null;
	private TypeII _duration = null;
	private String _payOutType = null;
		 
	public Option() {}
	/**
	 * 
	 * @param optionName
	 * @param payOutType
	 * @param interestRate
	 * @param startPrice
	 * @param volatility
	 * @param strikePrice
	 */
	public Option(String optionName, String payOutType, double interestRate, double startPrice, double volatility, double strikePrice) {
		this._optionName = optionName;
		this._payOutType = payOutType;
		this._interestRate = interestRate;
		this._startPrice = startPrice;
		this._volatility = volatility;
		this._strikePrice = strikePrice;

	}

	// Modifiers and Setters
	public String getOptionName() {
		return _optionName;
	}

	public String getPayOutType() {
		return _payOutType;
	}
			
	public double getInterestRate() {
		return _interestRate;
	}

	public double getVolatility() {
		return _volatility;
	}

	public double getStrikePrice() {
		return _strikePrice;
	}
			
	public double getStartPrice() {
		return _startPrice;
	}

	public TypeI getStartTime() {
		return _startTime;
	}

	public TypeI getEndTime() {
		return _endTime;
	}

	public TypeII getDuration() {
		return _duration;
	}
	
	public void setOptionName(String name) {
		this._optionName = name;
	}
	
	public void setInterestRate(double interestRate) {
		this._interestRate = interestRate;
	}
	
	public void setVolatility(double v) {
		this._volatility = v;
	}
	
	public void setStrikePrice(double strikePrice) {
		this._strikePrice = strikePrice;
	}
	
	public void setStartPrice(double startPrice) {
		this._startPrice = startPrice;
	}
	
	public void setStartTime(TypeI startTime) {
		this._startTime = startTime;
	}
	
	public void setEndTime(TypeI endTime) {
		this._endTime = endTime;
	} 
	
	public void setDuration(TypeII duration) {
		this._duration = duration;
	}
	
	public void setPayOutType(String PayOutType) {
		this._payOutType = PayOutType;
	}
	


}
