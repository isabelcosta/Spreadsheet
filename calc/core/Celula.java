package calc.core;


import java.io.Serializable;
import calc.core.Conteudo.*;


public class Celula implements Serializable{

	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = -742793240587091284L;

	private int _linha;
	private int _coluna;
	private Conteudo _content;

	/** Construtor */
	public Celula(int lin, int col){
		_linha = lin;
		_coluna = col;
		_content = null;
	}


	public int getLinha(){
		return _linha;
	}

	public int getColuna(){
		return _coluna;
	}

	public Conteudo getConteudo(){
		return _content;
	}

	public void setConteudo(Conteudo c){
		_content = c;
	}
	
	public String verCelula(){
		String str = ""+getLinha()+";"+getColuna()+"|";

		if(this.getConteudo() == null)
			return str;
		
		else if( (this.getConteudo().toString()).contains("=") ){
			
			if(this.getConteudo().getValue() == null)
				str += "#VALUE";
			else
				str += "" + this.getConteudo().getValue().intValue();
		}		
		return str + this.getConteudo().toString();
	}

	public String toString() {
		return this.verCelula();
	}

}