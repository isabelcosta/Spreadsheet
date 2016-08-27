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
 * Text interaction (either through the keyboard or files).
 * 
 * @author Programação com Objectos
 */
public class TextInteraction implements Interaction {

	/**
	 * UserInteraction reference.
	 */
	private UserInteraction _ui;

	/**
	 * Input channel.
	 */
	private BufferedReader _in;

	/**
	 * Output channel.
	 */
	private PrintStream _out;

	/**
	 * Log channel.
	 */
	private PrintStream _log;

	/**
	 * Copy input to output?
	 */
	private boolean _writeInput;

	/**
	 * Constructor (package).
	 */
	TextInteraction(UserInteraction ui) {
		_ui = ui;
		String nome = System.getProperty(Messages.inputChannel());

		if (nome != null) {
			try {
				_in = new BufferedReader(new FileReader(nome));
			} catch (FileNotFoundException fn) {
				println(Messages.inputError(fn));
				nome = null; // uses the default value
			}
		}

		if (nome == null)
			_in = new BufferedReader(new InputStreamReader(System.in));

		nome = System.getProperty(Messages.outputChannel());

		if (nome != null) {
			try {
				PrintStream pr = new PrintStream(new FileOutputStream(nome));
				if (Boolean.getBoolean(Messages.bothChannels())) {
					CompositePrintStream out = new CompositePrintStream();
					out.add(pr);
					out.add(System.out);
					_out = out;
				} else
					_out = pr;
			} catch (FileNotFoundException fn) {
				println(Messages.outputError(fn));
				nome = null; // uses the default value
			}
		}

		if (nome == null)
			_out = System.out;

		nome = System.getProperty(Messages.logChannel());
		if (nome != null) {
			try {
				_log = new PrintStream(new FileOutputStream(nome));
			} catch (FileNotFoundException fn) {
				println(Messages.logError(fn));
				nome = null; // uses the default value
			}
		}

		if (nome == null)
			_log = null;

		_writeInput = Boolean.getBoolean(Messages.writeInput());
	}

	/**
	 * Close all I/O channels.
	 */
	public void closeDown() {
		if (System.out != _out)
			_out.close();
		try {
			String nome = System.getProperty(Messages.inputChannel());
			if (nome != null)
				_in.close();
		} catch (IOException ioe) {
			println(Messages.errorClosingInput(ioe));
		}

		if (_log != null)
			_log.close();
	}

	/**
	 * Display a Menu.
	 */
	public void menu(Menu m) {
		int option = 0, i;

		while (true) {
			println(m.title());
			for (i = 0; i < m.entries().length; i++)
				if (m.entries()[i].isVisible())
					println((i + 1) + " - " + m.entries()[i].title()); //$NON-NLS-1$
			println(pt.utl.ist.po.ui.Menu.Messages.exit());

			try {
				option = readInteger(pt.utl.ist.po.ui.Menu.Messages.selectAnOption());
				if (option == 0)
					return;

				if (option < 0 || option > i || !m.entries()[option - 1].isVisible()) {
					println(pt.utl.ist.po.ui.Menu.Messages.invalidOption());
				} else {
					m.entries()[option - 1].execute();
					if (m.entries()[option - 1].isLast())
						return;
				}
			} catch (InvalidOperation oi) {
				println(m.entries()[option - 1].title() + ": " + oi); //$NON-NLS-1$
			} catch (EOFException eof) {
				println(pt.utl.ist.po.ui.Messages.errorEOF(eof));
				return;
			} catch (IOException ioe) {
				println(pt.utl.ist.po.ui.Messages.errorIO(ioe));
			} catch (NumberFormatException nbf) {
				println(pt.utl.ist.po.ui.Messages.errorInvalidNumber(nbf));
			} catch (RuntimeEOFException e) {
				// Não devia ser preciso mas há alunos que apanham
				// IOException e não fazem nada.
				println(pt.utl.ist.po.ui.Messages.errorREOF(e));
				return;
			}
		}
	}

	/**
	 * Display a Form.
	 */
	public void form(Form f) {
		// if (f.title() != null) println(f.title()); // No title in text mode
		try {
			for (Input in: f.entries()) {
				if (in.regex() != null) {
				        // if (in.cleared()) ... [ no support for default values, yet ]
					while (!in.parse(readString(in.prompt())))
						;
				}
				else
					println(in.prompt());
			}
		} catch (EOFException eof) {
			println(pt.utl.ist.po.ui.Messages.errorEOF(eof));
			return;
		} catch (IOException ioe) {
			println(pt.utl.ist.po.ui.Messages.errorIO(ioe));
		} catch (NumberFormatException nbf) {
			println(pt.utl.ist.po.ui.Messages.errorInvalidNumber(nbf));
		} catch (RuntimeEOFException e) {
			// Não devia ser preciso mas há alunos que apanham
			// IOException e não fazem nada.
			println(pt.utl.ist.po.ui.Messages.errorREOF(e));
			return;
		}
	}

	/**
	 * Display a Message.
	 */
	public void message(Display d) { println(d.getText()); }

	/**
	 * Close all I/O channels.
	 */
	public void close() { closeDown(); }

	/**
	 * Set the Interaction title.
	 * (unused in text mode)
	 */
	public void setTitle(String title) { }

	/**
	 * Error Messages
	 */
	@SuppressWarnings("nls")
	static final class Messages {

		/**
		 * Error message for file-not-found errors (input).
		 * 
		 * @param fnfe
		 *            an exception corresponding to a file-not-found problem.
		 * @return error message.
		 */
		@SuppressWarnings("nls")
		static final String inputError(FileNotFoundException fnfe) {
			return "Erro a colocar a entrada de dados: " + fnfe;
		}

		/**
		 * Error message for file-not-found errors (output).
		 * 
		 * @param fnfe
		 *            an exception corresponding to a file-not-found problem.
		 * @return error message.
		 */
		static final String outputError(FileNotFoundException fnfe) {
			return "Erro a colocar a saída de dados: " + fnfe;
		}

		/**
		 * Error message for file-not-found errors (log).
		 * 
		 * @param fnfe
		 *            an exception corresponding to a file-not-found problem.
		 * @return error message.
		 */
		static final String logError(FileNotFoundException fnfe) {
			return "Erro a especificar o ficheiro de log: " + fnfe;
		}

		/**
		 * Error message for IO errors (closing input).
		 * 
		 * @param ioe
		 *            an IO exception.
		 * @return error message.
		 */
		static final String errorClosingInput(IOException ioe) {
			return "Erro a fechar entrada de dados: " + ioe;
		}

		/**
		 * Error message for number format errors.
		 * 
		 * @param nfe
		 *            a <code>NumberFormatException</code>.
		 * @return error message.
		 */
		@SuppressWarnings("unused")
		static final String invalidNumber(NumberFormatException nfe) {
			return "Número inválido!";
		}

		/**
		 * Error message for EOF errors.
		 * 
		 * @return error message.
		 */
		static final String endOfInput() {
			return "Fim do fluxo de dados de entrada";
		}

		/**
		 * @return input property name.
		 */
		static final String inputChannel() {
			return "in";
		}

		/**
		 * @return output property name.
		 */
		static final String outputChannel() {
			return "out";
		}

		/**
		 * Use multiple channels?
		 * 
		 * @return both channels property name.
		 */
		static final String bothChannels() {
			return "both";
		}

		/**
		 * @return log channel property name.
		 */
		static final String logChannel() {
			return "log";
		}

		/**
		 * Should input be copied to the output?
		 * 
		 * @return property name.
		 */
		static final String writeInput() {
			return "writeInput";
		}

	}

	/**
	 * Read an integer number from the input.
	 * 
	 * @param prompt
	 *            a prompt (may be null)
	 * @return the number read from the input.
	 * @throws IOException
	 *             in case of read errors
	 */
	private final int readInteger(String prompt) throws IOException {
		while (true) {
			try {
				return Integer.parseInt(readString(prompt));
			} catch (NumberFormatException e) {
				println(Messages.invalidNumber(e));
			}
		}
	}

	/**
	 * Read a string.
	 * 
	 * @param prompt
	 *            a prompt (may be null)
	 * @return the string read from the input.
	 * @throws IOException
	 *             in case of read errors
	 * @throws EOFException
	 *             in case of end of file errors
	 */
	private final String readString(String prompt) throws IOException {
		if (prompt != null)
			_out.print(prompt);
		String str = _in.readLine();
		if (str == null) {
			// infelizmente tem que ser esta excepcao pq ha
			// alunos que a apanham e nao fazem nada
			throw new RuntimeEOFException(Messages.endOfInput());
		}

		if (_log != null)
			_log.println(str);

		if (_writeInput)
			_out.println(str);

		return str;
	}

	/**
	 * Write a string.
	 * 
	 * @param text
	 *            string to write.
	 */
	private final void println(String text) {
		_out.println(text);
	}

}
