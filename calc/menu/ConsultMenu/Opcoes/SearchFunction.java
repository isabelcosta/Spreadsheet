package calc.menu.ConsultMenu.Opcoes;


import java.util.List;

import pt.utl.ist.po.ui.*;
import calc.textui.search.Message;
import pt.utl.ist.po.ui.InputString;
import calc.textui.search.MenuEntry;
import calc.core.*;

public class SearchFunction extends Command<FolhaDeCalculo> {
	/**
	 * Construtor do comando SearchFunction (Procura funcao).
	 * 
	 * @param folhaCalculo
	 *            Folha de Calculo a activa na aplicacao.
	 */
	public SearchFunction(FolhaDeCalculo folhaCalculo) {
		super(MenuEntry.SEARCH_FUNCTIONS, folhaCalculo);
	}


	/**
	 * Executa o comando. Procede 'a procura da funcao e apresenta feedback/resultados da procura
	 * ao utilizador.
	 * 
	 */
	@Override
	public final void execute() {
		Form f = new Form(title());
		InputString funcao = new InputString(f, Message.searchFunction());
		f.parse();
	
		List<Celula> searchRes = entity().procuraFuncao(funcao.value());
		
		if(!(searchRes.isEmpty())){		// sera necessario?? o for n fara tudo o q e' necessario
			Display d = new Display(title());
			for(Celula cell: searchRes)
				d.addNewLine(cell.toString());
			d.display();
		}	 

	}
}