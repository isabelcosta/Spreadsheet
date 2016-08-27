//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

/** Request a float within a Form */
public class InputFloat extends Input {
  /** The value to request */
  private float _real;
  /** Build an InputFloat
   * @param form the Form used to request the value
   * @param prompt the description used in the request
   */
  public InputFloat(Form form, String prompt) { super(form, prompt); }
  /** Parse a value as a float
   * @param in the String containing the value
   * @return true if the String is a valid float
   */
  public boolean parse(String in) {
    try {
      _real = Float.parseFloat(in);
      dirty();
    } catch (NumberFormatException e) {
      // println(Messages.invalidNumber(e));
      return false;
    }
    return true;
  }
  /** Set the value */
  public void set(float f) { _real = f; dirty(); }
  /** @return the value as a String */
  public String toString() { return ""+_real; }
  /** @return the value as a float */
  public float value() { return _real; }
  /** @return the regular expression used to validate the Input */
  public String regex() { return "[0-9]+\\.[0-9]+[eE][+-][0-9]+"; }
}
