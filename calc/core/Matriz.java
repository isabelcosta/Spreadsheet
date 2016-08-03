package calc.core;

import java.io.Serializable;
import java.util.*;

import calc.core.Conteudo.*;

public class Matriz implements PortaCelula, Serializable{
	
	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = 1059154995254782172L;
	private int _maxLinhas;
	private int _maxColunas;	
	private Celula[][] _formCelulas;		

	public Matriz(int lines, int columns){
		_formCelulas = new Celula[lines][columns];
		int i, j;
		for(i=0; i < lines; i++)
			for(j=0; j< columns; j++)
			{
				_formCelulas[i][j] = new Celula(i+1, j+1);
			}
		_maxLinhas = lines;
		_maxColunas = columns;

	}

	public void inserirNaFol(int linha, int coluna, Conteudo content){
		_formCelulas[linha-1][coluna-1].setConteudo(content);
	}

	public void apagarDaFol(int linha, int coluna){
		_formCelulas[linha-1][coluna-1].setConteudo(null);
	}
	
	public String visualizarFol(int linha, int coluna){
		return ""+_formCelulas[linha-1][coluna-1].toString();
	}
	
	public Celula getCelulaEspecifica(int linha, int coluna){
		return _formCelulas[linha-1][coluna-1];
	}
	
	public List<Celula> procuraValor(int number){
		int i=0, j=0;
		List<Celula> auxiliar = new ArrayList<Celula>();
		for(i=0; i < _maxLinhas; i++)
			for(j=0; j< _maxColunas; j++)
			{
				if(_formCelulas[i][j].getConteudo() != null)
					if( _formCelulas[i][j].getConteudo().getValue() == number)
						auxiliar.add(_formCelulas[i][j]);
			}
		return auxiliar;
	}
	
	public List<Celula> procuraFuncao(String function){
		int i=0, j=0;
		List<Celula> auxiliar = new ArrayList<Celula>();
		for(i=0; i < _maxLinhas; i++)
			for(j=0; j< _maxColunas; j++)
			{
				if(_formCelulas[i][j].getConteudo() != null)
					if(_formCelulas[i][j].getConteudo().toString().contains(function))
						auxiliar.add(_formCelulas[i][j]);
			}
		return auxiliar;
	}

}
