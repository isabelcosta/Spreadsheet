package calc.textui.edit;

import pt.utl.ist.po.ui.InvalidOperation;

/**
 * Launched when an invalid cell range is used.
 */
public class InvalidCellRange extends InvalidOperation {
	/**
	 * Class serial number (serialization).
	 */
	private static final long serialVersionUID = 201308211552L;

	/** Invalid range. */
	private final String _range;

	/**
	 * @param range
	 */
	public InvalidCellRange(String range) {
		_range = range;
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	@SuppressWarnings("nls")
	public String getMessage() {
		return "A gama '" + _range + "' é inválida.";
	}
}
