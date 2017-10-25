/**
 * Defines the interface for generating stock path (Tier 2)
 */
package MonteCarloSimulation;

/**
 * @author xiaog
 *
 */
/* Import necessary library */
import java.util.List;
import java.util.ArrayList;

public interface StockPath {
	
	public ArrayList<pairPrice<Integer, Double>> getPrices();
}
