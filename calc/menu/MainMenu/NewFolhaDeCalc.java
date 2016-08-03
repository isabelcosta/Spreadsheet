
package calc.menu.MainMenu;

import calc.core.FolhaDeCalculo;
import calc.textui.*;
import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputBoolean;
import pt.utl.ist.po.ui.InputInteger;
import pt.utl.ist.po.ui.InvalidOperation;
import calc.textui.main.*;

public class NewFolhaDeCalc extends Command<Calc>{
	
	public NewFolhaDeCalc(Calc app){
		super(MenuEntry.NEW, app);
	}

	public final void execute() throws InvalidOperation {
		Form f = new Form(title());
		InputBoolean response = null;
		if(entity().isOpen())
			if(!(entity().getFolha().isSaved()))
				response = new InputBoolean(f, Message.saveBeforeExit());

		f.parse();
		
		if((response != null) && (response.value() == true)){
			menu().entry(2).execute();
		}	

		Form f2 = new Form(title());		
		
		InputInteger line = new InputInteger(f2, Message.linesRequest());
		InputInteger column = new InputInteger(f2, Message.columnsRequest());

		f2.parse();
		
		FolhaDeCalculo folha = new FolhaDeCalculo(line.value(), column.value());
		entity().openFolha();
		entity().setFolha(folha);
		
		menu().entry(2).visible();
		menu().entry(3).visible();
		menu().entry(4).visible();
		menu().entry(5).visible();	

	}	


}