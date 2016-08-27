//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

/** Request an integer within a Form */
public class InputInteger extends Input {
  /** The value to request */
  private int _int;
  /** Build an InputInteger
   * @param form the Form used to request the value
   * @param prompt the description used in the request
   */
  public InputInteger(Form form, String prompt) { super(form, prompt); }
  /** Parse a value as an integer
   * @param in the String containing the value
   * @return true if the String is a valid integer
   */
  public boolean parse(String in) {
    try {
      _int = Integer.parseInt(in);
      dirty();
    } catch (NumberFormatException e) {
      // println(Messages.invalidNumber(e));
      return false;
    }
    return true;
  }
  /** Set the value */
  public void set(int f) { _int = f; dirty(); }
  /** @return the value as a String */
  public String toString() { return ""+_int; }
  /** @return the value as an integer */
  public int value() { return _int; }
  /** @return the regular expression used to validate the Input */
  public String regex() { return "[0-9]*"; }
}
