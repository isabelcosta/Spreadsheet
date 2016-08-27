//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

/** Request a boolean within a Form */
public class InputBoolean extends Input {
  /** The value to request */
  private boolean _val;
  /** Build an InputBoolean
   * @param form the Form used to request the value
   * @param prompt the description used in the request
   */
  public InputBoolean(Form form, String prompt) { super(form, prompt); }
  /** Parse a value as a boolean
   * @param in the String containing the value ('s' to YES and 'n' to NO)
   * @return true if the String is a valid boolean
   */
  public boolean parse(String in) {
    if (in.length() == 1 && (in.charAt(0) == 's' || in.charAt(0) == 'n')) {
      _val = in.charAt(0) == 's';
      dirty();
      return true;
    }
    return false;
  }
  /** Set the value to true */
  public void set() { _val = true; dirty(); }
  /** Set the value to false */
  public void reset() { _val = false; dirty(); }
  /** @return the value as a String */
  public String toString() { return _val ? "sim" : "nao"; }
  /** @return the value as a boolean */
  public boolean value() { return _val; }
  /** @return the regular expression used to validate the Input */
  public String regex() { return "[sn]"; }
}
