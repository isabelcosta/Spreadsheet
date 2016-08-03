package calc.core.Conteudo.Functions.BinaryFunctions;



import calc.core.Conteudo.*;

public class Mul extends BinaryFunction implements Operando{

	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = -970330271329086828L;

	public Mul(Operando o1, Operando o2){
		_operando1 = o1;
		_operando2 = o2;
	}

	public Integer getValue(){
		if(_operando1.getValue() == null || _operando2.getValue() == null)
			return null;
		return new Integer(_operando1.getValue() * _operando2.getValue());
	}

	public String toString() {
		return "=MUL" + super.toString();
	}	
	

}