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
    
    @Test
    public void basicMul() {
    	double op1 = 2.0,
    			op2 = 2.0,
    			expected = 4.0,
    			actual;
    	actual = pUnit.mul(op1, op2);
    	assertEquals(expected, actual, EPS);
    }
    
    @Test
    public void basicDiv() {
    	double numerator = 2.0,
    			denominator = 2.0,
    			expected = 1.0,
    			actual;
    	actual = pUnit.div(numerator, denominator);
    	assertEquals(expected, actual, EPS);
    }
    
    @Test(expected = Exception.class)
    public void divByZero() {
    	double numerator = 2.0,
    			denominator = 0.0;
    	pUnit.div(numerator, denominator);
    }
    
    @Test
    public void basicPlus() {
    	double op1 = 1.0,
    			op2 = 1.0,
    			expected = 2.0,
    			actual;
    	actual = pUnit.plus(op1, op2);
    	assertEquals(expected, actual, EPS);
    }
    
    @Test
    public void basicPower() {
    	double x = 2.0,
    			a = 2.0,
    			expected = 4.0,
    			actual;
    	actual = pUnit.power(x, a);
    	assertEquals(expected, actual, EPS);
    	
    	x = -1.0;
    	expected = 1.0;
    	actual = pUnit.power(x, a);
    	assertEquals(expected, actual, EPS);
    }
    
    @Test
    public void basicSqrt() {
    	double radicand = 9.0,
    			expected = 3.0,
    			actual;
    	actual = pUnit.sqrt(radicand);
    	assertEquals(expected, actual, EPS);
    }
    
    @Test
    public void getEquationLine() {
    	String expected = "1.0",
    			actual;
    	store.setEquationLine(expected);
    	actual = pUnit.getEquationLine();
    	assertEquals(expected, actual);
    }
    
    @Test
    public void appedDigit() {
    	String in = "10";
    	boolean actual;
    	
    	actual = pUnit.appendDigit(in);
    	assertFalse(actual);
    	
    	String numberLineString = "1234567890123456789012";
    	in = "1";
    	
    	store.setNumberLine(numberLineString);
    	actual = pUnit.appendDigit(in);
    	assertFalse(actual);
    	
    	numberLineString = "0";
    	in = "0";
    	
    	store.setNumberLine(numberLineString);
    	actual = pUnit.appendDigit(in);
    	assertFalse(actual);
    	
    	numberLineString = "1";
    	in = "1";
    	
    	store.setNumberLine(numberLineString);
    	actual = pUnit.appendDigit(in);
    	assertTrue(actual);
    }
    
    //appendDot mit einem String der l�nge <1 wird der Branch nicht erreicht
    @Test
    public void appendDot() {
    	String numberLineString = "123456789012345678901";
    	boolean actual;
    	
    	store.setNumberLine(numberLineString);
    	actual = pUnit.appendDot();
    	assertFalse(actual);
    	
    	numberLineString = "0.";
    	
    	store.setNumberLine(numberLineString);
    	actual = pUnit.appendDot();
    	assertFalse(actual);
    	
    	numberLineString = "0";
    	
    	store.setNumberLine(numberLineString);
    	actual = pUnit.appendDot();
    	assertTrue(actual);
    }
    
    @Test
    public void clear() {
    	String expectedNumberLine = "0",
    			expectedEquationLine = " ",
    			actualNumberLine,
    			actualEquationLine;
    	Double expectedNumStore = Double.NaN,
    			actualNumStore;
    	
    	pUnit.clear();
    	
    	actualEquationLine = pUnit.getEquationLine();
    	assertEquals(expectedEquationLine, actualEquationLine);
    	
    	actualNumberLine = pUnit.getNumberLine();
    	assertEquals(expectedNumberLine, actualNumberLine);
    }
    
    @Test
    public void backspace() {
    	String numberLineString = "123",
    			expected = "12",
    			actual;
    	Double expectedNumLine = 0.0,
    			actualNumLine;
    	
    	store.setNumberLine(numberLineString);
    	pUnit.backspace();
    	
    	actual = pUnit.getNumberLine();
    	assertEquals(expected, actual);
    	
    	numberLineString = "";
    	expected = "0";
    	
    	store.setNum(1.0);
    	store.setNumberLine(numberLineString);
    	pUnit.backspace();
    	
    	actual = pUnit.getNumberLine();
    	assertEquals(expected, actual);
    	
    	actualNumLine = store.getNum();
    	assertEquals(expectedNumLine, actualNumLine, EPS);
    }
    
    @Test
    public void sqrt() {
    	String numberLineString = "9";
    	boolean actual;
    	
    	store.setNumberLine(numberLineString);
    	actual = pUnit.sqrt();
    	assertTrue(actual);
    	
    	numberLineString = "0";
    	
    	store.setNumberLine(numberLineString);
    	actual = pUnit.sqrt();
    	assertFalse(actual);
    }
    
    @Test
    public void pow() {
    	String numberLineString = "3",
    			expectedNumberLine = "9.0",
    			actualNumberLine;
    	
    	boolean actual;
    	
    	store.setNumberLine(numberLineString);
    	actual = pUnit.pow();
    	assertTrue(actual);
    	
    	actualNumberLine = pUnit.getNumberLine();
    	assertEquals(expectedNumberLine, actualNumberLine);
    	
    	double num = 2.0;
    	expectedNumberLine = "4.0";
    	
    	store.setNum(num);
    	actual = pUnit.pow();
    	assertTrue(actual);
    	
    	actualNumberLine = pUnit.getNumberLine();
    	assertEquals(expectedNumberLine, actualNumberLine);
    }
    
    @Test
    public void operation() {
    	char op = '+';
    	String numberLineString = "1.0",
    			expectedNumberLine = "0",
    			expectedEquationLine = "1.0 +",
    			actualNumberLine,
    			actualEquationLine;
    
    	store.setNumberLine(numberLineString);
    	
    	pUnit.operation(op);
    	actualNumberLine = pUnit.getNumberLine();
    	actualEquationLine = pUnit.getEquationLine();
    	assertEquals(expectedNumberLine, actualNumberLine);
    	assertEquals(expectedEquationLine, actualEquationLine);
    	
    	double num = 3.0;
    	
    	store.setNum(num);
    	pUnit.operation(op);
    	
    	actualNumberLine = pUnit.getNumberLine();
    	actualEquationLine = pUnit.getEquationLine();
    	assertEquals(expectedNumberLine, actualNumberLine);
    }
    
    @Test
    public void equal() {
    	String numberLineSring = "2.0",
    			expectedNumberLine = "4.0",
    			expectedEquationLine = "2.0 + 2.0",
    			actualNumberLIne,
    			actualEquationLine;
    	double num = 2.0;
    	char op = '+';
    	boolean actual;
   
    	actual = pUnit.equal();
    	assertFalse(actual);
    	
    	pUnit.operation(op);
    	store.setNum(num);
    	store.setNumberLine(numberLineSring);
    	actual = pUnit.equal();
    	
    	assertTrue(actual);
    	
    	actualNumberLIne = pUnit.getNumberLine();
    	actualEquationLine = pUnit.getEquationLine();
    	assertEquals(expectedNumberLine, actualNumberLIne);
    	assertEquals(expectedEquationLine, actualEquationLine);
    }
    
    @Test
    public void equalsMul() {
    	String numberLineSring = "2.0",
    			expectedNumberLine = "4.0",
    			expectedEquationLine = "2.0 * 2.0",
    			actualNumberLIne,
    			actualEquationLine;
    	boolean actual;
    	double num = 2.0;
    	char op = '*';
    	
    	pUnit.operation(op);
    	store.setNum(num);
    	store.setNumberLine(numberLineSring);
    	actual = pUnit.equal();
    	
    	assertTrue(actual);
    	
    	actualNumberLIne = pUnit.getNumberLine();
    	actualEquationLine = pUnit.getEquationLine();
    	assertEquals(expectedNumberLine, actualNumberLIne);
    	assertEquals(expectedEquationLine, actualEquationLine);
    }
    
    @Test public void equalsDiv() {
    	String numberLineSring = "2.0",
    			expectedNumberLine = "1.0",
    			expectedEquationLine = "2.0 / 2.0",
    			actualNumberLIne,
    			actualEquationLine;
    	boolean actual;
    	double num = 2.0;
    	char op = '/';
    	pUnit.operation(op);
    	store.setNum(num);
    	store.setNumberLine(numberLineSring);
    	actual = pUnit.equal();
    	
    	assertTrue(actual);
    	
    	actualNumberLIne = pUnit.getNumberLine();
    	actualEquationLine = pUnit.getEquationLine();
    	assertEquals(expectedNumberLine, actualNumberLIne);
    	assertEquals(expectedEquationLine, actualEquationLine);
    }
    
    @Test public void equalsInvOp() {
    	char op = 'a';
    	boolean actual;
    	
    	pUnit.operation(op);
    	actual = pUnit.equal();
    	assertFalse(actual);
    }
}