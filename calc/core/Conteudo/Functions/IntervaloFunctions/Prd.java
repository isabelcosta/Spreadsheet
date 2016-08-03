package calc.core.Conteudo.Functions.IntervaloFunctions;

import calc.core.*;


public class Prd extends IntervaloFunction{

	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = 8922933242497141731L;

	public Prd(Intervalo i){
		_intervalo = i;
	}

	public Integer getValue(){
		int res = 1;
		for(Celula c: _intervalo.getIntervalo()){
			if (c.getConteudo() == null)
				return null;
			if (c.getConteudo().getValue() == null)
				return null;
			res *= c.getConteudo().getValue();
		}
		return res;
	}

	public String toString() {
		return "=PRD(" + _intervalo.toString() + ")";
	}	

}