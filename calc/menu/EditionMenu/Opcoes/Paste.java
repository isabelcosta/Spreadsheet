
package calc.menu.EditionMenu.Opcoes;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;
import calc.core.CutBuffer;
import calc.core.FolhaDeCalculo;
import calc.core.Intervalo;
import calc.core.Parser;
import calc.textui.edit.InvalidCellRange;
import calc.textui.edit.MenuEntry;
import calc.textui.edit.Message;

public class Paste extends Command<FolhaDeCalculo> {
	/**
	 * Construtor do comando Paste (Colar).
	 * 
	 * @param folhaCalculo
	 *            Folha de Calculo activa na aplicacao.
	 */
	public Paste(FolhaDeCalculo folha) {
		super(MenuEntry.PASTE, folha);
	}


	/**
	 * Executa o comando. Pede ao utilizador uma gama (intervalo ou celula 'unica), para copiar o conteudo 
	 * do cutbuffer para a gama recebida
	 * 
	 * @throws InvalidOperation
	 *             caso aconteca algum erro.
	 */
	@Override
	public final void execute() throws InvalidOperation{
		Form f = new Form(title());
		InputString gama = new InputString(f, Message.addressRequest());
		f.parse();
		
		if (entity().getCutBufferC() == null)
			return;
		
		Intervalo intervalo;
		
		try{
			if(gama.value().contains(":"))
				intervalo = Parser.parseIntervalo(entity(), gama.value());
			else
				intervalo = Parser.parseIntervalo(entity(), ""+gama.value()+":"+gama.value());
		}catch(Exception e){
			throw new InvalidCellRange(gama.value());
		}
		
		CutBuffer buffer = entity().getCutBufferC();
		int i, j;
		
		if(intervalo.intervaloSize() == buffer.getCutBufferSize()){
			boolean _direcaoHorizontal = buffer.getDirecao();
			if(_direcaoHorizontal)
				for(i = intervalo.getFirstCel().getColuna(), j=0; i <= entity().getColunas() && i <= buffer.getCutBufferSize(); i++, j++){
					entity().inserirNaFol(intervalo.getFirstCel().getLinha(), i, buffer.getCelulaFromCutBuffer(j).getConteudo());
				}			
			else
				for(i = intervalo.getFirstCel().getLinha(), j=0; i <= entity().getLinhas(); i++, j++){
					entity().inserirNaFol(i, intervalo.getFirstCel().getColuna(), buffer.getCelulaFromCutBuffer(j).getConteudo());
				}
		}
			
		else if (intervalo.intervaloSize() == 1){
			boolean _direcaoHorizontal = buffer.getDirecao();
			if(_direcaoHorizontal)
				for(i = intervalo.getFirstCel().getColuna(), j=0; j< buffer.getCutBufferSize() && i <= entity().getColunas(); i++, j++){
					entity().inserirNaFol(intervalo.getFirstCel().getLinha(), i, buffer.getCelulaFromCutBuffer(j).getConteudo());
				}
			else
				for(i = intervalo.getFirstCel().getLinha(), j=0; j< buffer.getCutBufferSize() && i <= entity().getLinhas(); i++, j++){
					entity().inserirNaFol(i, intervalo.getFirstCel().getColuna(), buffer.getCelulaFromCutBuffer(j).getConteudo());
				}
		}
			

		entity().setStateFolha(false);
	

	}
}