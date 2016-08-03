package calc.core.Conteudo.Functions.IntervaloFunctions;

import calc.core.*;

public class Avg extends IntervaloFunction{
	
	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = -7676782489224011822L;

	public Avg(Intervalo i){
		_intervalo = i;
	}

	public Integer getValue(){
		int res = 0;
		int n = 0;
		for(Celula c: _intervalo.getIntervalo()){
			if (c.getConteudo() == null)
				return null;
			if (c.getConteudo().getValue() == null)
				return null;
			res += c.getConteudo().getValue();
			n++;
		}
		return res/n;
	}

	public String toString() {
		return "=AVG(" + _intervalo.toString() + ")";
	}	
	
}