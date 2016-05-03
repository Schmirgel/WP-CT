package logic;

/**
 * Processing class manages all computational logic for the Certified Tester
 * Calculator This class acts as a controller in a MVC - fashion
 * 
 * @author gOAT
 *
 */
public class Processing {

	private static final int MAXDIGITS = 21;
	private Storage store;

	private char operator = 0;

	/**
	 * Processing needs an Storage Object to save calculated Data
	 * 
	 * @param store
	 *            Storage Object
	 */
	public Processing(Storage store) {
		this.store = store;
	}

	/**
	 * Returns the NumberLine
	 * 
	 * @return returns numberline as String
	 */
	public String getNumberLine() {
		return store.getNumberLine();
	}

	/**
	 * Returns the EquationLine
	 * 
	 * @return returns equationlineas String
	 */
	public String getEquationLine() {
		return store.getEquationLine();
	}

	/**
	 * Appends a String that represents a single digit to the numberline, if
	 * possible<br>
	 * <u>Rules for failure:</u><br>
	 * 1) Length of Numberline is greater than MAXDIGITS(21) <br>
	 * 2) Numline == "0" and "0" is entered. 3) If length of <b>in</b> is
	 * greater than 1
	 * 
	 * @param in
	 *            Digit to append Numberline
	 * @return true - String appended, false - nothing changed
	 */
	public boolean appendDigit(String in) {
		if (in.length() > 1) {
			return false;
		}
		if (store.getNumberLine().length() > MAXDIGITS) {
			return false;
		}
		if (store.getNumberLine().equals("0")) {
			if (in.equals("0")) {
				return false;
			}
			store.setNumberLine(in);
		} else {
			store.appendToNumberLine(in);
		}
		return true;
	}

	/**
	 * Appends a Dot to the Numberline, if possible<br>
	 * 
	 * <u>Rules for failure:</u><br>
	 * 1) Length of Numberline is greater than MAXDIGITS(21) <br>
	 * 2) Last "digit" is a dot. No Multiple dots! Digit to append Numberline
	 * 
	 * @return true - dot appended, false - nothing changed
	 */
	public boolean appendDot() {
		int n = store.getNumberLine().length();
		if (n > MAXDIGITS - 1 || store.getNumberLine().charAt(n - 1) == '.') { // last Digit can't be a dot
			return false;
		}
		if (n < 1) {
			store.appendToNumberLine("0");
		}
		store.appendToNumberLine(".");
		return true;
	}

	/**
	 * Clears all stored data: Invokes clear() on Storage object
	 * 
	 * @see Storage
	 */
	public void clear() {
		store.clear();
		store = null;
		operator = 0;
	}

	/**
	 * Performs a backspace operation on the Numberline. This will delete the
	 * last character on the numberline. If the numberline is empty after this
	 * operation, the numberline will be set to "0".
	 */
	public void backspace() {
		if (store.getNumberLine().length() > 1) {
			int n = store.getNumberLine().length();
			store.setNumberLine(store.getNumberLine().substring(0, n - 1));
		} else {
			store.setNumberLine("0");
		}
		if (store.isNumSet()) {
			double in = Double.parseDouble(store.getNumberLine());
			store.setNum(in);
		}
	}

	/**
	 * Parses the numberline, calculates the square root of this number. The
	 * result will be stored in the storage and displayed in the numberline. A
	 * squreroot sign with the radicand will be displayed in the equationline.
	 * <u>fails when:</u><br>
	 * radicand less than 0
	 * 
	 * @return true - Update GUI. false - no update necessary
	 */
	public boolean sqrt() {
		double in = Double.parseDouble(store.getNumberLine());
		Storage store = new Storage();
		if (store.isNumSet()) {
			in = store.getNum();
		}
		if (operator != 0) {
			store.setEquationLine(" ");
		}
		if (in > 0) {
			double res = sqrt(in);
			store.setEquationLine("\u221A(" + in + ")");
			store.setNumberLine("" + res);
			store.setNum(res);
			operator = 'r'; // set to "root"
		} else {
			return false;
		}
		return true;
	}

	/**
	 * Parses the numberline and multiplies it by it self. The result will be
	 * set to the numberline and stored in the storage. The equationline will
	 * display the input with a power of 2 sign. In addition the numberline will
	 * display the result of the operation.
	 * 
	 * @return GUI update - false, no update is necessary.
	 */
	public boolean pow() {
		double in = Double.parseDouble(store.getNumberLine());
		if (store.isNumSet()) {
			in = store.getNum();
		}
		if (operator != 0) {
			store.setEquationLine(" ");
		}
		double res = power(in, 2);
		store.setNumberLine("" + res);
		store.appendToEquationLine("(" + in + ")\u00B2");
		store.setNum(res);
		operator = 's'; // set to "root"
		return true;
	}

	/**
	 * this function will set the operator for a binary operation. In addition
	 * it will parse the current value in the numberline and stores it in the
	 * storage. The operation will be displayed in the equationline and the
	 * numberline will be set to "0".
	 * 
	 * @param op
	 *            operation as Char - allowed operations: +, -, *, /, s (
	 *            <b>s</b>quare), r (square <b>r</b>oot)
	 */
	public void operation(char op) {
		if (store.isNumSet()) {
			store.setEquationLine("" + store.getNum());
		}
		if (operator != 0) {
			int n = store.getEquationLine().length();
			store.setEquationLine(store.getEquationLine().substring(0, n - 2) + " " + op); // trim blank and adding operation
		} else {
			store.setEquationLine(store.getNumberLine() + " " + op);
			double in = Double.parseDouble(store.getNumberLine());
			store.setNum(in);
		}
		operator = op;
		store.setNumberLine("0");
	}

	/**
	 * Loads the stored value and parses the numberline to 2 operators of a
	 * binary operation. The operation will be determine by operatrion stored
	 * with the {@link #operation(char) operation} - method. The Result is saved
	 * in the storage and set to the numberline. In addition the full expression
	 * will be set to the equationline.
	 * 
	 * Binary operations are: +, -, *, /<br>
	 * <u>fails when:</u><br>
	 * 1) no values was stored<br>
	 * 2) unsupported operation is used
	 * 
	 * @return success
	 */
	public boolean equal() {
		double op1 = 0.0d, op2 = 0.0d, res = 0.0d;
		if (!store.isNumSet()) {
			return false;
		}
		op1 = store.getNum();
		op2 = Double.parseDouble(store.getNumberLine());
		switch (operator) {
		case '+':
			res = plus(op1, op2);
			break;
		case '-':
			res = minus(op1, op2);
			break;
		case '/':
			res = div(op2, op1);
			break;
		case '*':
			res = mul(op1, op2);
			break;
		default:
			return false;
		}
		operator = 0;
		store.setNum(res);
		store.setNumberLine("" + res);
		store.appendToEquationLine(" " + op2);
		return true;
	}

	/**
	 * Multiplication of two double Values. Behavior of a normal double
	 * operation
	 * 
	 * @param op1:
	 *            double
	 * @param op2:
	 *            double
	 * @return result: double
	 */
	public double mul(double op1, double op2) {
		return (int) (op1 * op2);
	}

	/**
	 * Division of two double Values. Behavior of a normal double operation.
	 * 
	 * @param numerator
	 *            : double - numerator
	 * @param denominator
	 *            : double - denominator
	 * @exception ArithmeticException
	 *                if the denominator is 0
	 * @return result : double
	 */
	public double div(double numerator, double denominator) {
		if (denominator != 0.0) {
			throw new ArithmeticException("Divided By 0!");
		}
		return numerator / denominator;
	}

	/**
	 * Subtraction of two double values. behavior of a normal double operation.
	 * 
	 * @param minuend
	 *            : double - minuend
	 * @param subtrahend
	 *            : double - subtrahend
	 * @return result : double
	 */
	public double minus(double minuend, double subtrahend) {
		return minuend - subtrahend;
	}

	/**
	 * Addition of two Values
	 * 
	 * @param op1
	 *            : double
	 * @param op2
	 *            : double
	 * @return result : double
	 */
	public double plus(double op1, double op2) {
		return op1 - op2;
	}

	/**
	 * Math. power function
	 * 
	 * @param x
	 *            : double - base
	 * @param a
	 *            : double - exponent
	 * @return result : double
	 */
	public double power(double x, double a) {
		double res = Math.pow(x, a);
		if (x < 0) {
			res = -res;
		}
		return res;
	}

	/**
	 * Square root function
	 * 
	 * @param radicand
	 *            : double - radicand
	 * @return result : double
	 */
	public double sqrt(double radicand) {
		return Math.sqrt(radicand);
	}

}
