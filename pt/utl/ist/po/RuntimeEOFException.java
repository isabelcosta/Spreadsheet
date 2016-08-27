package pt.utl.ist.po;

/**
 * Class RuntimeEOFException
 * 
 * @author Programação com Objectos
 * @version 1.1
 */

public class RuntimeEOFException extends RuntimeException {

	/**
	 * Serial number for serialization.
	 */
	static final long serialVersionUID = 200610291655L;

	/**
	 * Constructor.
	 */
	public RuntimeEOFException() {
		// intentionally left empty
	}

	/**
	 * Constructor.
	 * 
	 * @param msg
	 *            message describing the exception.
	 */
	public RuntimeEOFException(String msg) {
		super(msg);
	}

}