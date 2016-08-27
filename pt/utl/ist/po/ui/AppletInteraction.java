//-*- coding: utf-8 -*-

package pt.utl.ist.po.ui;

import java.util.*;
import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Applet interaction.
 * 
 * @author Programação com Objectos
 */
public class AppletInteraction extends JApplet implements Interaction { 

	/**
	 * Current AppletPanel.
	 */
	private AppletPanel _panel;

	/**
	 * Start rotine called by the internet browser.
	 * This rotines fetches the <b>mainClass</B> parameter,
	 * builds the <code>main()</code> arguments from the <b>mainArgs</b> parameter
	 * and calls the application's main rotine: <b>mainClass.main(mainArgs)</b>.
	 * The <b>AppletInteraction</b> is then called back by the <b>UserInteraction</b>.
	 */
	@SuppressWarnings("unchecked")
	private void main() {
	    String mainClass = getParameter("mainClass");
	    if (mainClass == null) {
	      add(new JLabel("no 'mainClass' <param> for this Applet"));
	      return;
	    }
	    String[] args = new String[] { };
	    String mainArgs = getParameter("mainArgs");
	    if (mainArgs != null) args = mainArgs.split(";");
	    Class[] cArray = new Class[] { String[].class };
	    Object[] oArray = new Object[] { args };
	    try {
	      Class c = Class.forName(mainClass);
	      c.getMethod("main",cArray).invoke(null, oArray);
	    // } catch (ClassNotFoundException e) {
	    // } catch (InvocationTargetException e) {
	    } catch (Exception e) {
	      add(new JLabel("ERROR: "+e));
	    }
	}

	/**
	 * Initialize Applet.
	 */
	public void init() {
	    UserInteraction.IO = new UserInteraction(this);
	    try {
		// SwingUtilities.invokeLater(new Runnable() {
		Runnable r = new Runnable() {
		    public void run() { main(); };
		};
		Thread t = new Thread(r, "application.Main");
		t.start();
	    } catch (Exception e) {
		System.err.println("main() error: "+e);
	    }
	    try { Thread.currentThread().sleep(100); } catch (Exception e) {}
	}
	// public void start() { System.out.println("start"); } // debug
	// public void stop() { System.out.println("stop"); } // debug
	// public void destroy() { System.out.println("destroy"); } // debug

	/**
	 * Close all I/O channels.
	 */
	public void close() {
	  super.destroy();
	  // System.out.println("close");
	}

	/**
	 * Set the Interaction title.
	 */
	public void setTitle(String title) {
	  setName(title);
	}

	/**
	 * Display a Menu.
	 */
	public void menu(Menu m) {
		int option;
		do {
			AppletPanel menu;
			add(menu = new AppletPanel(m));
			menu.await();
			option = menu.option();
			remove(menu);
			if (option == 0) break;
			try {
				m.entries()[option - 1].execute();
			} catch (InvalidOperation oi) {
				message(m.entries()[option - 1].title() + ": " + oi, m.title()); //$NON-NLS-1$
			}
		} while (!m.entries()[option - 1].isLast());
	}

	/**
	 * Display a Form.
	 */
	public void form(Form f) {
		AppletPanel form;
		add(form = new AppletPanel(f));
		do form.await();
		while (!form.parse());
		remove(form);
	}

	/**
	 * Display a Message.
	 */
	public void message(Display d) {
	  message(d.getText(), d.getTitle());
	}

	/**
	 * Display a Message.
	 */
	private void message(String s, String title) {
		AppletPanel mesg;
		add(mesg = new AppletPanel(s, title));
		mesg.await();
		remove(mesg);
	}

	/**
	 * The class manages the body of the window, that can be used to
	 * select an option (Command) from a Menu, fill the Input values
	 * of a Form, or output a text message
	 */
	public class AppletPanel extends JPanel implements ActionListener {
	    /** The menu option selected */
	    private int _opt;
	    /** The OK button was pressed or a menu option selected */
	    private boolean _end;
	    /** Assoction between user Input instances and Applet's JTextField values */
	    private Map<Input,JTextField> _ins;

	    /** implements ActionListener
	     * is called when a menu option is selected
	     * or when th OK button is pressed is Forms or messages.
	     * @param evt the ActionEvent to be processed
	     */
	    public void actionPerformed(ActionEvent evt) {
		_opt = Integer.parseInt(evt.getActionCommand());
		_end = true;
		try { Thread.currentThread().sleep(100); } catch (Exception e) {}
	    }

	    /** Sleep the current thread so that other actions can be peformed
	     * @param n time to wait
	     */
	    private synchronized void sleep(int n) {
			    try {
				    Thread.currentThread().sleep(n);
			    } catch (InterruptedException ie) {
				    System.out.println(ie);
			    }
	    }

	    /** Wait until the OK button is pressed and the <b>_end</b>
	     * becomes <b>true</b>
	     */
	    private synchronized void await() {
		    _end = false;
		    while(!_end) { sleep(1); }
		    // should Thread.currentThread().wait(); for notifyAll
	    }

	    /** @return the select option from a menu */
	    private int option() { return _opt; }

	    /** Copy the user inputed values to the respective Input class values */
	    private boolean parse() {
	        boolean ret = true;
		for (Input in: _ins.keySet()) {
			if (in.regex() != null)
				if (!in.parse(_ins.get(in).getText())) {
			        	_ins.get(in).setText("");
					ret = false;
				}
		}
		return ret;
	    }

	    /**
	     * Build an AppletPanel to display a Menu
	     * @param m the Menu to display
	     */
	    AppletPanel(Menu m) { super(new GridLayout(m.entries().length+3,1));
		    int i;
		    JButton jb;
		    int[] key = { KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3,
				  KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6,
				  KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9 };

		    add(new JLabel(m.title(), JLabel.CENTER));
		    add(new JLabel("")); // não gosto do JSeparator

		    for (i = 0; i < m.entries().length; i++)
			    if (m.entries()[i].isVisible()) {
				    add(jb = new JButton((i + 1) + " - " + m.entries()[i].title()));
				    jb.addActionListener(this);
				    jb.setActionCommand(""+(i+1));
				    if (i < 9) jb.setMnemonic(key[i]);
			    }
			    else add(new JLabel((i + 1) + " - " + m.entries()[i].title(), JLabel.CENTER));
		    add(jb = new JButton(pt.utl.ist.po.ui.Menu.Messages.exit()));
		    jb.addActionListener(this);
		    jb.setActionCommand("0");
		    jb.setMnemonic(KeyEvent.VK_0);
	    }

	    /**
	     * Build an AppletPanel to display a Form
	     * @param f the Form to display
	     */
	    AppletPanel(Form f) { super(new GridLayout(f.entries().size()+2,2));
		    _ins = new HashMap<Input,JTextField>();
		    JTextField textField;
		    JButton jb;
		    boolean first = true;

		    if (f.title() != null) {
			    add(new JLabel(""));
			    add(new JLabel(f.title()));
		    }
		    for (Input in: f.entries()) {
			    JLabel l;
			    add(l = new JLabel(in.prompt(), JLabel.TRAILING));
			    if (in.regex() != null) {
				    if (in.cleared())
					    add(textField = new JTextField(10));
				    else
					    add(textField = new JTextField(in.toString(), 10));
				    l.setLabelFor(textField);
				    _ins.put(in, textField);
				    if (first) { first = false;
					    textField.setFocusAccelerator('1'); }
			    } else add(new JLabel(""));
		    }
		    add(new JLabel(""));
		    add(jb = new JButton("OK"));
		    jb.addActionListener(this);
		    jb.setActionCommand("0");
		    jb.setMnemonic(KeyEvent.VK_ENTER);
	    }

	    /**
	     * Build an AppletPanel to display a Message
	     * @param s the Message to display
	     */
	    AppletPanel(String s, String title) {
		    JButton jb;

		    // setLayout(new GridLayout(2,1));
		    setLayout(new BorderLayout());

		    if (title != null)
		      add(new JLabel(title, JLabel.CENTER));
		    JTextArea textArea = new JTextArea(5, 20);
		    textArea.setEditable(false);
		    textArea.append(s);
		    // textArea.setFocusAccelerator('1'); // nao editavel => no focus
		    JScrollPane scrollPane = new JScrollPane(textArea);

		    add(scrollPane, BorderLayout.CENTER);
		    add(jb = new JButton("OK"), BorderLayout.SOUTH);
		    jb.addActionListener(this);
		    jb.setActionCommand("0");
		    jb.setMnemonic(KeyEvent.VK_ENTER);
	    }
	}

	/**
	 * Error Messages
	 */
	@SuppressWarnings("nls")
	static final class Messages {

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

	}
}
