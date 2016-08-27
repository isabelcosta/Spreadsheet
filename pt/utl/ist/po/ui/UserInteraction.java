//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import pt.utl.ist.po.CompositePrintStream;
import pt.utl.ist.po.RuntimeEOFException;

/**
 * User interaction (either through the keyboard or files).
 * 
 * @author Programação com Objectos
 */
public class UserInteraction {


	/** Single instance of this class. */
	public static/* final */UserInteraction IO = new UserInteraction();

	/** Interaction with sub-system (text, swing, ...). */
	private Interaction _action;

	/**
	 * Single constructor (private).
	 */
	private UserInteraction() {
	    try { // applets can not do System.getProperty()
		String nome = System.getProperty(Messages.actionChannel());
		if (nome == null) {
			try {
			  	System.in.available();
			} catch (IOException io) { nome = "swing"; }
		}

		if (nome != null && nome.equalsIgnoreCase("swing")) {
			_action = new SwingInteraction(this);
		}
		else {
			_action = new TextInteraction(this);
		}
	    } catch (SecurityException e) {
	    	System.out.println("actionChannel: "+e);
	    }
	}

	/**
	 * supply the action interface (package).
	 */
	UserInteraction(Interaction action) {
		_action = action;
	}

	/**
	 * @return selected action interface (package).
	 */
	Interaction action() { return _action; }

	/**
	 * Set the title for the main window
	 * @param title the String to define as the title
	 */
	public void setTitle(String title) { _action.setTitle(title); }

	/**
	 * Display a text message
	 * @param str the String to display
	 */
	public void message(Display d) { _action.message(d); }

	/**
	 * Close the interaction
	 */
	public void close() { _action.close(); }

        /**
         * Error Messages
         */
        @SuppressWarnings("nls")
	static final class Messages {

		/**
		 * @return action property name.
		 */
		static final String actionChannel() {
			return "ui";
		}
	}
}
