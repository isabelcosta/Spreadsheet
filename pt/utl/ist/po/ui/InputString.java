//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

/** Request a String within a Form */
public class InputString extends Input {
  /** The value to request */
  private String _text;
  /** Build an InputString
   * @param form the Form used to request the value
   * @param prompt the description used in the request
   */
  public InputString(Form form, String prompt) { super(form, prompt); }
  /** Parse a value as a String
   * @param in the String containing the value
   * @return true if the String is a valid String
   */
  public boolean parse(String in) { _text = in; dirty(); return _text.matches(regex()); }
  /** Set the value */
  public void set(String s) { _text = s; dirty(); }
  /** @return the value as a String */
  public String toString() { return _text; }
  /** @return the value as a String */
  public String value() { return _text; }
  /** @return the regular expression used to validate the Input */
  public String regex() { return ".*"; }
}
