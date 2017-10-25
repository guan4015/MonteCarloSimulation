package MonteCarloSimulation;

import org.junit.Test;

import junit.framework.TestCase;

public class TestVectorGenerator extends TestCase {

	@Test
	public void testNormalRandomGenerator() {
		NormalRandomVectorGenerator n1 = new NormalRandomVectorGenerator(252);
		double[] norm = n1.getVector();
		for (int i = 0; i < norm.length; i++) {
			StdOut.printf("%.2f, ", norm[i]);
			if ((i+1) % 20 == 0 ) {
				StdOut.println("\n");				
			}
		}
	}
	
	public void testAntiTheticGenerator() {
		
		NormalRandomVectorGenerator n1 = new NormalRandomVectorGenerator(252);
		AntiTheticRandomVectorGenerator n2 = new AntiTheticRandomVectorGenerator(n1);
		double[] norm1 = n2.getVector();
		double[] norm2 = n2.getVector();
		for (int i = 0; i < norm1.length; i++) {
			StdOut.printf("%.2f, %.2f\n", norm1[i], norm2[i]);
			assertTrue(norm1[i] + norm2[i] == 0);
		}
	}
	
	

}
