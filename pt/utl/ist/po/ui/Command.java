//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

/**
 * Abstract class <code>Command</code> represents an operation that can be
 * carried out by or over an entity.
 * 
 * @author Programação com Objectos
 * @version 2.0
 * @param <Entity>
 *            the entity providing the command's context.
 * @see pt.utl.ist.po.ui.Menu
 * @see pt.utl.ist.po.ui.InvalidOperation
 */
public abstract class Command<Entity> {
	/**
	 * The menu containing this Command.
	 */
	private Menu _menu;

	/**
	 * Indicates whether, in a menu, this is the last command.
	 */
	private boolean _last;

	/**
	 * The command label in a menu.
	 */
	private String _title;

	/**
	 * The entity providing the command's context.
	 */
	private Entity _entity = null;

	/**
	 * Indicates whether, in a menu, this command is visible.
	 */
	private boolean _visible;

	/**
	 * @param last
	 *            indicates whether, in a menu, this is the last command.
	 * @param title
	 *            the command label in a menu.
	 */
	public Command(boolean last, String title) {
		_last = last;
		_title = title;
		_visible = true;
	}
	public Command(String title) { this(false, title); }

	/**
	 * @param last
	 *            indicates whether, in a menu, this is the last command.
	 * @param title
	 *            the command label in a menu.
	 * @param entity
	 *            the entity providing the command's context.
	 */
	public Command(boolean last, String title, Entity entity) {
		this(last, title);
		_entity = entity;
	}
	public Command(String title, Entity entity) {
		this(false, title, entity);
	}

	/**
	 * @param set the command's menu (package).
	 */
	final void menu(Menu m) {
		_menu = m;
	}

	/**
	 * @return the command's menu.
	 */
	public final Menu menu() {
		return _menu;
	}

	/**
	 * @return the command's title.
	 */
	public final String title() {
		return _title;
	}

	/**
	 * @return the command's entity.
	 */
	public Entity entity() {
		return _entity;
	}

	/**
	 * Indicates whether, in a menu, this is the last command.
	 * 
	 * @return true if, in a menu, this is the last command.
	 */
	public boolean isLast() {
		return _last;
	}

	/**
	 * Indicates whether, in a menu, this command is visible.
	 * 
	 * @return true if, in a menu, this command is visible.
	 */
	public boolean isVisible() {
		return _visible;
	}

	/**
	 * Make this command visible, again.
	 */
	public void visible() {
		_visible = true;
	}

	/**
	 * Make this command is invisible.
	 */
	public void invisible() {
		_visible = false;
	}

	/**
	 * Executes the command.
	 * 
	 * @throws InvalidOperation
	 *             if something wrong or unexpected occurs.
	 */
	public abstract void execute() throws InvalidOperation;

}
