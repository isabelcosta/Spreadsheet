package calc.core.Conteudo.Functions.BinaryFunctions;

import calc.core.Conteudo.*;

public class Div extends BinaryFunction{

	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = -7285149804214349784L;

	public Div(Operando o1, Operando o2){
		_operando1 = o1;
		_operando2 = o2;
	}

	public Integer getValue(){
		if(_operando1.getValue() == null || _operando2.getValue() == null)
			return null;
		if(_operando2.getValue() == 0)	//trata da divisao por zero
			return null;
		return new Integer(_operando1.getValue() / _operando2.getValue());
	}

	public String toString() {
		return "=DIV" + super.toString();
	}	
	

}