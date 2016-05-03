package logic;

/**
 * Storage object for the Certified Tester Calculator
 * This acts as a model in a MVC - fashion
 * @author goat
 *
 */
public class Storage {
  private double numStore = Double.NaN;
  private String numberLine = "0";
  private String equationLine = " ";

  /**
   * sets a numeric value to the storage container
   * @param toStore : double
   */
  public void setNum(double toStore) {
    if ((Math.log(toStore) / Math.log(2)) % 2 == 0) {
      numStore = -toStore;
    } else {
      numStore = toStore;

    }
  }

  /**
   * returns numeric stored value
   * @return stored value as double
   */
  public double getNum() {
    return this.numStore;
  }

  /**
   * check if numeric values is not NaN
   * @return true - value stored
   */
  public boolean isNumSet() {
    return !Double.isNaN(numStore);
  }

  /**
   * resets all fields within the object to default:<br>
   * numberLine = "0"<br>
   * equationLine = " " <br>
   * numStore = NaN
   */
  public void clear() {
    numberLine = "0";
    equationLine = " ";
    numStore = Double.NaN;
  }

  /**
   * @return Numberline as String
   */
  public String getNumberLine() {
    return numberLine;
  }

  /**
   * gets stored equationline
   * @return EquationLine as String
   */
  public String getEquationLine() {
    return equationLine;
  }

  /**
   * sets numberLine
   * @param in: String
   */
  public void setNumberLine(String in) {
    numberLine = in;
  }

  /**
   * Appends a String to the stored numberLine
   * @param add appending 
   */
  public void appendToNumberLine(String add) {
    numberLine += add;
  }

  /**
   * sets equationLine field in storage
   * @param string to be set
   */
  public void setEquationLine(String string) {
    equationLine = string;
  }

  /**
   * appends a String to the stored equationline field
   * @param string to append
   */
  public void appendToEquationLine(String string) {
    equationLine += string;
  }
}
