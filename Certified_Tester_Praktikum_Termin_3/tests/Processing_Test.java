import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import logic.Processing;
import logic.Storage;

public class Processing_Test {
	private static final double EPS = 0.000000000000000000000001;
	private Processing pUnit;
	private Storage store;
	
    @Before
    public void setUp() throws Exception {
    	store = new Storage();
    	pUnit = new Processing(store);
    }

    @After
    public void tearDown() throws Exception {
    	pUnit = null;
    	store = null;
    }
    
    @Test
    public void basicMinus(){
    	double subtrahend = 3.0,
    			minuend = 5.0,
    			expected = 2.0,
    			actual;
    	actual = pUnit.minus( minuend, subtrahend);
    	assertEquals(expected, actual, EPS);
    }
    
    @Test
    public void guiMinus(){
    	String minuend = "7",
    			subtrahend = "5",
    			expected = "2.0",
    			actual;
    	char operation = '-';
    	pUnit.appendDigit(minuend);
    	pUnit.operation(operation);
    	pUnit.appendDigit(subtrahend);
    	pUnit.equal();
    	actual = pUnit.getNumberLine();
    	assertEquals(expected, actual);
    	
    }
    
    
}