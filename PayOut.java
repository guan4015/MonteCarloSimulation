/**
 * Defines the interface for computing the payoff (Tier 3)
 */
package MonteCarloSimulation;

/**
 * @author xiaog
 *
 */
public interface PayOut {
	
	public double getPayout(StockPath path);

}
