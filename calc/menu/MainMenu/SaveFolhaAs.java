
package calc.menu.MainMenu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import calc.textui.*;
import calc.textui.main.*;

public class SaveFolhaAs extends Command<Calc>{
	
	public SaveFolhaAs(Calc app){
		super(MenuEntry.SAVE_AS, app);
	}

	public final void execute() {
		Form f = new Form(title());
		InputString name = new InputString(f, Message.saveAs());
		f.parse();

		// ESTA A GUARDAR A FOLHA (SERIALIZACAO) 
		entity().saveFolha(name.toString());
	}
	
}	