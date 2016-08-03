package calc.core.Conteudo.Functions.BinaryFunctions;

import calc.core.Conteudo.*;

public abstract class BinaryFunction extends Funcao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4279279834089348902L;
	protected Operando _operando1;
	protected Operando _operando2;


	public Operando getFirstOperando(){
		return _operando1;
	}

	public Operando getSecondOperando(){
		return _operando2;
	}

	public abstract Integer getValue();

	public String toString(){
		String op1 = _operando1.toString();
		String op2 =  _operando2.toString();
		
		if (op1.contains("="))
			op1 = op1.substring(1);
		if(op2.contains("="))
			op2 = op2.substring(1);
	
		return "(" + op1 + "," + op2 + ")";
	}

}