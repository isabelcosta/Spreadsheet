//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

/**
 * Interaction interface
 * 
 * @author Programação com Objectos
 */
public interface Interaction {

	/**
	 * Display a Menu.
	 */
	public void menu(Menu m);

	/**
	 * Fill a Form.
	 */
	public void form(Form f);

	/**
	 * Display a Message.
	 */
	public void message(Display d);

	/**
	 * Set the Interaction title.
	 */
	public void setTitle(String title);

	/**
	 * Close all I/O channels.
	 */
	public void close();
}
