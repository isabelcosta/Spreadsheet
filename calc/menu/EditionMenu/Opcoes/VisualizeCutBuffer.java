
package calc.menu.EditionMenu.Opcoes;

import java.util.List;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import calc.core.Celula;
import calc.core.FolhaDeCalculo;
import calc.textui.edit.MenuEntry;

public class VisualizeCutBuffer extends Command<FolhaDeCalculo> {
	/**
	 * Construtor do comando VisualizeCutBuffer (Mostrar cut buffer).
	 * 
	 * @param folhaCalculo
	 *            Folha de Calculo a activa na aplicacao.
	 */
	public VisualizeCutBuffer(FolhaDeCalculo folha) {
		super(MenuEntry.SHOW_CUT_BUFFER, folha);
	}


	/**
	 * Executa o comando. Procede 'a procura do valor e apresenta feedback/resultados ao
	 * utilizador.
	 * 
	 * //@throws OperacaoInvalida
	 *             caso aconteca algum erro.
	 */
	@Override
	public final void execute() {

		Display d = new Display(title());

		if(entity().getCutBufferC() == null)
			return;
		
		List<Celula> cutBuffer = entity().getCutBufferC().getBufferCopiado();
		
		for(Celula c: cutBuffer){

			d.addNewLine(c.verCelula());
			
		}
		d.display();
	}
}