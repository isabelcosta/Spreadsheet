
package calc.core;

import java.util.*;

public class Intervalo{
	
	private Celula _firstCelula;
	private Celula _lastCelula;
	private List<Celula> _celulas;
	
	/**
	 * Construtor
	 */
	public Intervalo(Celula c1, Celula c2, FolhaDeCalculo folha){
		_celulas = new ArrayList<Celula>();
		_firstCelula = c1;
		_lastCelula = c2;
		int i=0;
		if (c1.getLinha() == c2.getLinha()){
			if (c1.getColuna()<=c2.getColuna())
		 		for(i = c1.getColuna(); i <= c2.getColuna(); i++){
		 			_celulas.add(folha.getCelulaEspecifica(c1.getLinha(), i));
		 		}
		 }
		else if (c1.getColuna() == c2.getColuna()){	
		 	if (c1.getLinha() <= c2.getLinha())
		 		for(i = c1.getLinha(); i <= c2.getLinha(); i++){
		 			_celulas.add(folha.getCelulaEspecifica(i, c1.getColuna()));
		 		}
		}
	}

	public List<Celula> getIntervalo(){
		return _celulas;
	}

	public Celula getFirstCel(){
		return _firstCelula;
	}

	public Celula getLastCel(){
		return _lastCelula;
	}

	public int intervaloSize(){
		return _celulas.size();
	}
	
	public Celula getCellDoIntervalo(int pos){
		return _celulas.get(pos);
	}
	
	public String toString() {
		return "" + getFirstCel().getLinha()+";" + getFirstCel().getColuna() + ":" + getLastCel().getLinha()+";" + getLastCel().getColuna();
	}	
	
}
