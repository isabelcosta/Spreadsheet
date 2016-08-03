package calc.core.Conteudo;

import calc.core.*;


public class Referencia extends Conteudo implements Operando{
	
	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = -144002413177677541L;
	
	private Celula _celula;

	/**
	 * Contrutor
	 */	
	public Referencia(Celula cell){
		_celula = cell;
	}

	public Integer getValue(){
		if(_celula.getConteudo() == null)
			return null;
		if(_celula.getConteudo().getValue() == null)
			return null;
		return new Integer(_celula.getConteudo().getValue());
	}

	public String toString() {
		return "=" + _celula.getLinha() + ";" + _celula.getColuna() + "";
	}	
}