package calc.menu.EditionMenu.Opcoes;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;
import calc.core.FolhaDeCalculo;
import calc.core.Intervalo;
import calc.core.Parser;
import calc.textui.edit.InvalidCellRange;
import calc.textui.edit.MenuEntry;
import calc.textui.edit.Message;

public class Copy extends Command<FolhaDeCalculo> {
	/**
	 * Construtor do comando Copy (Copiar).
	 * 
	 * @param folhaCalculo
	 *            Folha de Calculo a activa na aplicacao.
	 */
	public Copy(FolhaDeCalculo folha) {
		super(MenuEntry.COPY, folha);
	}


	/**
	 * Executa o comando. Pede ao utilizador uma gama (intervalo ou celula 'unica)
	 * e copia o seu conteudo para o cut buffer.
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
		
		 entity().copiarParaBuffer(intervalo);

	}
}