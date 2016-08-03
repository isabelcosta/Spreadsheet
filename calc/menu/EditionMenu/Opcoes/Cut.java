
package calc.menu.EditionMenu.Opcoes;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;
import calc.core.Celula;
import calc.core.FolhaDeCalculo;
import calc.core.Intervalo;
import calc.core.Parser;
import calc.textui.edit.InvalidCellRange;
import calc.textui.edit.MenuEntry;
import calc.textui.edit.Message;

public class Cut extends Command<FolhaDeCalculo> {
	/**
	 * Construtor do comando Cut (Cortar).
	 * 
	 * @param folhaCalculo
	 *            Folha de Calculo a activa na aplicacao.
	 */
	public Cut(FolhaDeCalculo folha) {
		super(MenuEntry.CUT, folha);
	}


	/**
	 * Executa o comando. Procede 'a procura do valor e apresenta feedback/resultados ao
	 * utilizador.
	 * 
	 * //@throws OperacaoInvalida
	 *             caso aconteca algum erro.
	 */
	@Override
	public final void execute() throws InvalidOperation{
		 Form f = new Form(title());
		 InputString gama = new InputString(f, Message.addressRequest());
		 f.parse();
		 
		 Intervalo intervalo;
		 
		try{
			if(gama.value().contains(":"))
				intervalo = Parser.parseIntervalo(entity(), gama.value());
			else
				intervalo = Parser.parseIntervalo(entity(), ""+gama.value()+":"+gama.value());
		}catch(Exception e){
			throw new InvalidCellRange(gama.value());
		}
			
		entity().copiarParaBuffer(intervalo);
		
		for(Celula cell: intervalo.getIntervalo()){
			entity().apagarDaFol(cell.getLinha(), cell.getColuna());
		}
		 entity().setStateFolha(false); // A FOLHA FOI ALTERADA

	}
}