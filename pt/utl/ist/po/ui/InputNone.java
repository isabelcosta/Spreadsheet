//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

/** Request no value, used as a separator within a Form */
public class InputNone extends Input {
  /** Build an InputNone
   * @param form the Form used to request the value
   * @param prompt the description used in the separator
   */
  public InputNone(Form form, String prompt) { super(form, prompt); }
  /** Parse no value
   * @param in the String containing the value
   * @return true if the String is valid
   */
  public boolean parse(String in) { return true; }
  /** @return the regular expression used to validate the Input */
  public String regex() { return null; }
}
