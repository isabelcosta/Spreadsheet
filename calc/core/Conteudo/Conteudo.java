package calc.core.Conteudo;

import java.io.Serializable;

public abstract class Conteudo implements Serializable{

	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = 8882472411770959544L;

	public abstract Integer getValue();
}