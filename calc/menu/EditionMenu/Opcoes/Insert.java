
package calc.menu.EditionMenu.Opcoes;


import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;

import calc.textui.edit.InvalidCellRange;
import calc.textui.edit.MenuEntry;
import calc.textui.edit.Message;
import calc.core.*;
import calc.core.Conteudo.Conteudo;

public class Insert extends Command<FolhaDeCalculo> {
	/**
	 * Construtor do comando Insert (Inserir).
	 * 
	 * @param folhaCalculo
	 *            Folha de Calculo activa na aplicacao.
	 */
	public Insert(FolhaDeCalculo folha) {
		super(MenuEntry.INSERT, folha);
	}


	/**
	 * Executa o comando. Pede ao utilizador uma gama (intervalo ou celula 'unica) e um conteudo e procede
	 * 'a insercao de um conteudo numa gama especifica.
	 * 
	 * @throws InvalidOperation
	 *             caso aconteca algum erro.
	 */
	@Override
	public final void execute() throws InvalidOperation{
		Form f = new Form(title());
		
		InputString gama = new InputString(f, Message.addressRequest());
		InputString content = new InputString(f, Message.contentsRequest());
		f.parse();
		 
		Intervalo intervalo;
		Conteudo c;
		
		try{
			if(gama.value().contains(":"))
				intervalo = Parser.parseIntervalo(entity(), gama.value());
			else
				intervalo = Parser.parseIntervalo(entity(), ""+gama.value()+":"+gama.value());
			c = Parser.parseConteudo(entity(),content.value());
			
		}catch(Exception e){
			throw new InvalidCellRange(gama.value());
		}
		
		for(Celula cell: intervalo.getIntervalo()){
			entity().inserirNaFol(cell.getLinha(), cell.getColuna(), c);
		}
		entity().setStateFolha(false);
	}
}