package calc.core.Conteudo;


public class Literal extends Conteudo implements Operando{
	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = -1739310072885727516L;
	
	private int _numero;

	/**
	 * Construtor
	 */
	public Literal(int number){
		_numero = number;
	}

	public Integer getValue(){
		return new Integer(_numero);
	}

	public String toString() {
		return "" + _numero + "";
	}

}