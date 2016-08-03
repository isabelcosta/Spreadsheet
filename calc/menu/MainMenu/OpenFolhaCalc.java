package calc.menu.MainMenu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InputBoolean;
import pt.utl.ist.po.ui.InvalidOperation;
import java.io.*;
import calc.textui.Calc;
import calc.textui.main.*;


public class OpenFolhaCalc extends Command<Calc>{
	
	public OpenFolhaCalc(Calc app){
		super(MenuEntry.OPEN, app);
	}

	public final void execute() throws InvalidOperation {
		Form f = new Form(title());
		InputBoolean response = null;
		if(entity().isOpen())
			if(!(entity().getFolha().isSaved()))
				response = new InputBoolean(f, Message.saveBeforeExit());
		InputString name = new InputString(f, Message.openFile());
		f.parse();

		if(response != null && response.value() == true)
			menu().entry(2).execute();
				
				
		// ESTA A ABRIR A FOLHA (DESSERIALIZACAO) 
		try{
			entity().loadFolha(name.value());
			if(entity().isOpen()){
				menu().entry(2).visible();
				menu().entry(3).visible();
				menu().entry(4).visible();
				menu().entry(5).visible();
			}
		}catch(ClassNotFoundException e){
			System.err.println(e.getMessage());
		}catch(FileNotFoundException e){
			(new Display(title())).add(Message.fileNotFound()).display();
		}catch(IOException e){
			System.err.println(e.getMessage());
		}

	}
	
}	