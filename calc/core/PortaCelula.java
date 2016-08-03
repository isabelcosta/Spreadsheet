package calc.core;

import java.util.List;
import calc.core.Conteudo.*;

public interface PortaCelula{

	public void inserirNaFol(int linha, int coluna, Conteudo content);
	public void apagarDaFol(int linha, int coluna);
	public String visualizarFol(int linha, int coluna);
	public Celula getCelulaEspecifica(int linha, int coluna);
	public List<Celula> procuraValor(int number);
	public List<Celula> procuraFuncao(String function);

}
