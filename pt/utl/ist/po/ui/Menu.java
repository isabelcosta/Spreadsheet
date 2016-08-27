//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

import java.io.EOFException;
import java.io.IOException;
import pt.utl.ist.po.RuntimeEOFException;
import static pt.utl.ist.po.ui.UserInteraction.IO;

/**
 * Class Menu manages a set of commands.
 * 
 * @author Programação com Objectos
 * @version 2.1
 * @param <Entity>
 *            the entity providing the command's context.
 * @see pt.utl.ist.po.ui.Command
 * @see pt.utl.ist.po.ui.UserInteraction
 */
@SuppressWarnings("nls")
public class Menu {

	/**
	 * Messages used by class Menu.
	 */
	static final class Messages {

		/**
		 * Prompt for menu option.
		 * 
		 * @return prompt text.
		 */
		static final String selectAnOption() {
			return "Escolha uma opção: ";
		}

		/**
		 * Massage for communicating an invalid option.
		 * 
		 * @return message text.
		 */
		static final String invalidOption() {
			return "Opção inválida!";
		}

		/**
		 * Exit option for all menus.
		 * 
		 * @return message text.
		 */
		static final String exit() {
			return "0 - Sair";
		}

	}

	/**
	 * Menu title.
	 */
	private String _title;

	/**
	 * Commands available from the menu.
	 */
	private Command<?> _commands[];

	/** Where to display the menu */
	private UserInteraction _ui;

	/**
	 * Constructor.
	 * 
	 * @param ui
	 * 	      interaction for the menu.
	 * @param title
	 *            menu title.
	 * @param commands
	 *            list of commands managed by the menu.
	 */
	public Menu(UserInteraction ui, String title, Command<?> commands[]) {
		_ui = ui;
		_title = title;
		_commands = commands;
		for (Command<?> c: _commands) c.menu(this);
	}
	public Menu(String title, Command<?> commands[]) {
		this(IO, title, commands);
	}

	/**
	 * @return the menu title (package).
	 */
	String title() {
		return _title;
	}

	/**
	 * @return the number of commands.
	 */
	public int size() {
		return _commands.length;
	}

	/**
	 * @return the n-th command.
	 */
	public Command<?> entry(int n) {
		return _commands[n];
	}

	/**
	 * @return the commands (package).
	 */
	Command<?>[] entries() {
		return _commands;
	}

	/**
	 * Main function: the menu interacts with the user and executes the
	 * appropriate commands.
	 */
	public void open() { _ui.action().menu(this); }

}
