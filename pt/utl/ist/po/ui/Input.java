//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

/** Request an Input value in a Form */
public abstract class Input {
  /** the Form used to request the value */
  private Form _form;
  /** the description used in the request */
  private String _prompt;
  /** a new value was read */
  private boolean _clear;
  /** @return the description of the request */
  public String prompt() { return _prompt; }
  /** @return the Form used to request the value */
  public Form form() { return _form; }

  /** Build an Input without a title
   * @param form used to request the value
   */
  protected Input(Form form) { this(form, null); }
  /** Build an Input with a title
   * @param form used to request the value
   * @param prompt the description of the requested value
   */
  protected Input(Form form, String prompt) {
    _prompt = prompt;
    (_form = form).add(this);
  }
  /** Mark the Input as unread */
  public void clear() { _clear = true; }
  /** Mark the Input as read */
  protected void dirty() { _clear = false; }
  /** @return true if the Input is cleared */
  /* package */ boolean cleared() { return _clear; }

  /** Each Input must parse its own specific type of value */
  public abstract boolean parse(String in);
  /** The regular expression used to validate the Input */
  public abstract String regex();
}
