//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;
import static pt.utl.ist.po.ui.UserInteraction.IO;

/** Display lines of text. */
public class Display {
  /** The graphical driver used to dialog with the user */
  private UserInteraction _ui;
  /** The Form's title (optional) */
  private String _title;
  /** Text to display */
  private String _text;

  /** Build a Display without a title and use the static IO */
  public Display() { this(IO, null); }

  /** Build a Display with a title and use the static IO
   * @param title the title
   */
  public Display(String title) { this(IO, title); }
  /** Build a Display
   * @param ui the dialog driver
   * @param title the title
   */
  public Display(UserInteraction ui, String title) {
    _ui = ui;
    _text = "";
    _title = title;
  }

  /** Add text
   *  @param text text to be added
   */
  public Display add(String text) { _text += text; return this; }

  /** Add a line of text in a new-line.
   *  No new-line is added if there is no previous text.
   *  @param text text to be added
   */
  public Display addNewLine(String text) { return addNewLine(text, false); }

  /** Add a line of text in a new-line.
   *  A new line is previously added if force is true or
   *  if there is already some text.
   *  @param text text to be added
   *  @param force add a new-line even if no text is already present.
   */
  public Display addNewLine(String text, boolean force) {
    if (force || _text.length() > 0) _text += "\n";
    _text += text;
    return this;
  }

  /** @return the title of the display */
  /* package */ String getTitle() { return _title; }

  /** @return the text to display */
  /* package */ String getText() { return _text; }

  /** Display the text.
   * @param force display even if no text was added.
   */
  public void display(boolean force) {
    if (force || _text.length() > 0)
      _ui.action().message(this);
  }
  /** Display the text (no force).
   */
  public void display() { display(false); }
}
