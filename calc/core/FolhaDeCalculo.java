
package calc.core;

import java.io.Serializable;
import java.util.List;
import calc.core.Conteudo.Conteudo;

/**
* Classe representa a folha de calculo, os seus atributos e funcionalidades, de uma aplicacao. 
* Implementa a interface PortaCelula que permite a flexibilidade do formato da folha de calculo.
* Implementa a interface Serializable que permite guardar, assim como restaurar o estado e os dados da folha de calculo.
*/

public class FolhaDeCalculo implements PortaCelula, Serializable{

	/**
	 * Serial number for serialization.
	 */
	private static final long serialVersionUID = 8457098195649950124L;

	/** 
	 * numero de linhas da folha de calculo 
	 */
	private final int _nLinhas;

	/** 
	 * numero de colunas da folha de calculo 
	 */	
	private final int _nColunas;

	/** 
	 * suporte das celulas da folha de calculo 
	 */	
	private PortaCelula _celulas;

	/** 
	 * cutBuffer da folha de calculo
	 */	
	private CutBuffer _cutBufferC = null;

	/** 
	 * nome da folha de calculo 
	 */	
	private String _folhaName = null;

	/**
	 * Variavel de estado da aplicacao que indica se a folha foi guardada
	 */
	private boolean _saved = false;

	/** 
	* Construtor
	*
	*
	* @param linhas
	*		numero maximo de linhas da folha
	* @param
	* 		numero maximo de colunas da folha
	*/
	public FolhaDeCalculo(int linhas, int colunas){
		_nLinhas = linhas;
		_nColunas = colunas;
		_celulas = new Matriz(linhas, colunas);
	}	// as folhas de caluculo têm um tamanho fixo


	/**
	* Devolve o numero de linhas que a folha tem
    * 
	* @return numero maximo de linhas da folha
	*/
	public int getLinhas(){
		return _nLinhas;	
	}
	
	/**
	* Devolve um valor boolean que indica se a folha esta guardada ou
    * se foi alterada sem ser guardada
	* @return true, se a folha estiver guardada; false se a folha nao estiver guardada 
	*/	
	public boolean isSaved(){
		return _saved;
	}

	/**
	* Modifica o estado da folha, recebe true se a folha foi guardada
	* recebe false se a folha tiver sido alterada
	* @param estado
	* 			indica se a folha foi gravada ou alterada 
	*/
	public void setStateFolha(boolean estado){
		_saved = estado;
	}
	
	/**
	* Devolve o numero de colunas que a folha tem
    * 
	* @return numero maximo de colunas da folha
	*/	
	public int getColunas(){
		return _nColunas;	
	}


	/**
	* Devolve o nome da folha de calculo
    * 
	* @return nome da folha
	*/	
	public String getName(){
		return _folhaName;
	}


	/**
	* Da o nome da folha de calculo
    * 
	* @param nome da folha
	*/		
	public void setName(String name){
		_folhaName = name;
	}

	/**
	* Devolve a lista das celulas do cutBuffer
    * 
	* @return cutBuffer
	*/	
	public CutBuffer getCutBufferC(){
		return _cutBufferC;	
	}

	/**
	* Devolve o porta celulas da folha
    * 
	* @return _celulas, porta celulas da folha
	*/	
	public PortaCelula getPortaCelula(){
		return _celulas;
	}

	public Celula getCelulaEspecifica(int linha, int coluna){
		return _celulas.getCelulaEspecifica(linha, coluna);
	}	
	
	/**
	* Insere um conteudo numa celula da folha
    * 
	* @param linha
	*				linha da posicao a inserir o conteudo
	* @param coluna
	*				coluna da posicao a inserir o conteudo
	* @param content
	*				conteudo que se pretende inserir na folha
	*/	
	public void inserirNaFol(int linha, int coluna, Conteudo content){
		_celulas.inserirNaFol(linha, coluna, content);
	}


	/**
	* Apaga um conteudo da folha
    * 
	* @param linha
	*				linha da posicao do qual pretende apagar a celula
	* @param coluna
	*				coluna da posicao do qual pretende apagar a celula
	*/	
	public void apagarDaFol(int linha, int coluna){
		_celulas.apagarDaFol(linha, coluna);
	}

	/**
	* Vizualiza um conteudo indexado pela sua posicao da folha
    * 
	* @param linha
	*				linha da posicao do qual pretende visualizar o conteudo
	* @param coluna
	*				coluna da posicao do qual pretende visualiza o conteudo
	* @return conteudo
	*				string que representa o conteudo e o seu enderecamento
	*/	
	public String visualizarFol(int linha, int coluna){
		return _celulas.visualizarFol(linha, coluna);
	}

	/**
	* Vizualiza uma celula especifica do cutbuffer, indexando pela sua
	* posicao neste
    * 
	* @param posicao
	*				posicao usada para obter a celula pretendida do cutbuffer
	* @return conteudo
	*				string que representa o conteudo e o seu enderecamento
	*/	
	 public Celula visualizarCutBuffer(int posicao){
	 	return _cutBufferC.getCelulaFromCutBuffer(posicao);	// chamar num ciclo ao buffer
	 }

	/**
	* Copia uma lista de celulas para o buffer
    * 
	* @param buf
	*			buffer que pretende copiar para o cutBuffer
	*/		
	public void copiarParaBuffer(Intervalo buf){	// ver HEADFIRST JAVA pelos null's
		_cutBufferC = new CutBuffer(buf);
	}
	
	/**
	* Procura um valor na folha de calculo
    * 
	* @param number
	*			inteiro a procurar na folha
	* @return lista com todas as ocorencias do inteiro na folha
	*/	
	public List<Celula> procuraValor(int number){
		return _celulas.procuraValor(number);
	}
	
	/**
	* Procura uma funcao na folha de calculo
    * 
	* @param function, nome da funcao que se procura
	*			inteiro a procurar na folha
	* @return lista com todas as ocorencias da funcao na folha
	*/		
	public List<Celula> procuraFuncao(String function){
		return _celulas.procuraFuncao(function);
	}	
}