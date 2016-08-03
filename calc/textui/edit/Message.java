package calc.textui.edit;

/**
 * Messages.
 */
@SuppressWarnings("nls")
public class Message {

	/**
	 * @return string with prompt for range.
	 */
	public static final String addressRequest() {
		return "Especifique a gama (line;col ou startline;startcol:endline;endcol): ";
	}

	/**
	 * @return string with prompt for content.
	 */
	public static final String contentsRequest() {
		return "Insira o conteúdo da célula: ";
	}

}
