
package calc.menu.ConsultMenu.Opcoes;

import java.util.List;

import pt.utl.ist.po.ui.*;
import calc.textui.search.Message;
import calc.textui.search.MenuEntry;
import calc.core.*;

public class SearchValue extends Command<FolhaDeCalculo> {
	/**
	 * Construtor do comando SearchValue (Procura por valor).
	 * 
	 * @param folhaCalculo
	 *            Folha de Calculo a activa na aplicacao.
	 */
	public SearchValue(FolhaDeCalculo folhaCalculo) {
		super(MenuEntry.SEARCH_VALUES, folhaCalculo);
	}


	/**
	 * Executa o comando. Procede 'a procura do valor e apresenta feedback/resultados da procura
	 * ao utilizador.
	 * 
	 * //@throws OperacaoInvalida
	 *             caso aconteca algum erro.
	 */
	@Override
	public final void execute() {
		Form f = new Form(title());
		InputInteger searchValue = new InputInteger(f, Message.searchValue());
		f.parse();
		 
		List<Celula> searchRes = entity().procuraValor(searchValue.value());

		if(!(searchRes.isEmpty())){		// sera necessario?? o for n fara tudo o q e' necessario
			Display d = new Display(title());
			for(Celula cell: searchRes)
			{
				d.addNewLine(cell.toString());
			}
			d.display();
		}

	}
}