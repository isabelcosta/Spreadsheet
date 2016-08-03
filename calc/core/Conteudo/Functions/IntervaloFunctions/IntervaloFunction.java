
package calc.core.Conteudo.Functions.IntervaloFunctions;

import calc.core.*;
import calc.core.Conteudo.*;

public abstract class IntervaloFunction extends Funcao{
	
	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = -2734865507684319374L;
	
	protected Intervalo _intervalo;


	public Intervalo getMyIntervalo(){

		return _intervalo;
	}

	public abstract Integer getValue();


}