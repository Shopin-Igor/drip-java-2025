package Task2;
import org.junit.Test;
import java.math.BigInteger;
import static org.junit.Assert.assertEquals;

public class FactorialCalculatorTest {

    @Test
    public void testComputeFactorial() throws Exception {
        assertEquals(BigInteger.valueOf(120), FactorialCalculator.computeFactorial(5, 2));
        assertEquals(BigInteger.valueOf(3628800), FactorialCalculator.computeFactorial(10, 4));
    }



    @Test(expected = IllegalArgumentException.class)
    public void testNegativeInput() throws Exception {
        FactorialCalculator.computeFactorial(-1, 2);
    }


    @Test
    public void testEdgeCases() throws Exception {
        assertEquals(BigInteger.ONE, FactorialCalculator.computeFactorial(0, 1));
        assertEquals(BigInteger.ONE, FactorialCalculator.computeFactorial(1, 3));
    }
}