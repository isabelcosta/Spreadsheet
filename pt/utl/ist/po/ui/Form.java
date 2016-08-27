//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

import java.util.*;
import static pt.utl.ist.po.ui.UserInteraction.IO;

/**
 * A Form is collection of Input requests.
 * The values are requested to user collectively.
 * The requested can be sequential or random,
 * depending on the implementation of the UserInteraction used.
 */
public class Form {
  /** The graphical driver used to dialog with the user */
  private UserInteraction _ui;
  /** The Form's title (optional) */
  private String _title;
  /** A list of Input requests */
  private List<Input> _entries;
  /** use default values: static IO and no title */
  public Form() { this(IO, null); }
  /** provide a title and use the static IO
   * @param title the title
   */
  public Form(String title) { this(IO, title); }
  /** Build a Form
   * @param ui the dialog driver
   * @param title the title
   */
  public Form(UserInteraction ui, String title) {
    _ui = ui;
    _entries = new ArrayList<Input>();
    _title = title;
  }
  /** @return the title of the Form (can be null) */
  /* package */ String title() { return _title; }
  /** @return a copy of the Input requests of the Form */
  /* package */ Collection<Input> entries() { return Collections.unmodifiableCollection(_entries); }
  /** Add an Input to the end of the Form
   * @param in the Input to add
   */
  public void add(Input in) { _entries.add(in); }
  /** Add an Input to a given position in the Form
   * @param pos the position (0 is first) in the Form
   * @param in the Input to add
   */
  public void add(int pos, Input in) { _entries.add(pos, in); }
  /** Request the parsing of the value, clearing previous Input values() */
  public void parse() { parse(true); }
  /** Request the parsing of the value
   * @param clear if true Input values are first cleared and the request
   * 		  otherwise previous Input values are used as Input values by omission
   */
  public void parse(boolean clear) {
    if (clear) for (Input in: _entries) in.clear();
    _ui.action().form(this);
  }
}
