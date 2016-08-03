package calc.core;

import java.util.ArrayList;
import java.util.List;


public class CutBuffer{
	private List<Celula> _buffer = new ArrayList<Celula>(); //classe generica, estamos a construir um objeto generico da classe
	private boolean _horizontal;

	public CutBuffer(Intervalo intervaloBuffer) { 

		int i;
		
		if (intervaloBuffer.getFirstCel().getLinha() == intervaloBuffer.getLastCel().getLinha()){
			_horizontal = true;
		}
		else
			_horizontal = false;
		
		for(i=0; i < intervaloBuffer.intervaloSize(); i++){
			if(_horizontal)
				_buffer.add(new Celula(1,i+1));
			else
				_buffer.add(new Celula(i+1,1));
			_buffer.get(i).setConteudo(intervaloBuffer.getCellDoIntervalo(i).getConteudo());
		}
		
	}
	
	public boolean getDirecao(){
		return _horizontal;
	}
	
	public List<Celula> getBufferCopiado(){
		return _buffer;
	}
	
	public int getCutBufferSize(){
		return _buffer.size();
	}
	
	public Celula getCelulaFromCutBuffer(int pos){
		return _buffer.get(pos);
	}
}
