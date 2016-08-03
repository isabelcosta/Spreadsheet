
package calc.menu.EditionMenu.Opcoes;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;
import calc.textui.edit.InvalidCellRange;
import calc.textui.edit.MenuEntry;
import calc.textui.edit.Message;
import calc.core.*;


public class Erase extends Command<FolhaDeCalculo> {
	/**
	 * Construtor do comando Apagar.
	 * 
	 * @param folhaCalculo
	 *            Folha de Calculo activa na aplicacao.
	 */
	public Erase(FolhaDeCalculo folha) {
		super(MenuEntry.DELETE, folha);
	}


	/**
	 * Executa o comando. Pede ao utilizador uma gama e apaga o conteudo desta gama
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
		
		for(Celula cell: intervalo.getIntervalo()){
			entity().apagarDaFol(cell.getLinha(), cell.getColuna());
		}
		entity().setStateFolha(false);

	}
}