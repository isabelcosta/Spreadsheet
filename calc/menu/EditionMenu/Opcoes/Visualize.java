
package calc.menu.EditionMenu.Opcoes;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

import calc.textui.edit.InvalidCellRange;
import calc.textui.edit.MenuEntry;
import calc.textui.edit.Message;
import calc.core.*;

public class Visualize extends Command<FolhaDeCalculo> {
	/**
	 * Construtor do comando Visualize (Visualizar).
	 * 
	 * @param folhaCalculo
	 *            Folha de Calculo a activa na aplicacao.
	 */
	public Visualize(FolhaDeCalculo folha) {
		super(MenuEntry.SHOW, folha);
	}


	/**
	 * Executa o comando. Pede ao utilizador uma gama (intervalo ou celula 'unica)
	 * e mostra o seu enderecamento e conteudo.
	 * 
	 * @throws InvalidOperation
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
			
		Display d = new Display(title());
			
		for(Celula cell: intervalo.getIntervalo())
			d.addNewLine(cell.verCelula());
		
		d.display();
	}



}