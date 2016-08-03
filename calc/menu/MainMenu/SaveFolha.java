package calc.menu.MainMenu;


import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import calc.textui.Calc;
import calc.textui.main.*;

public class SaveFolha extends Command<Calc>{
	
	public SaveFolha(Calc app){
		super(MenuEntry.SAVE, app);
	}

	public final void execute(){
		Form f = new Form(title());
		if(entity().getFolha().isSaved() == false){
			if(entity().getFolha().getName() == null){
				InputString name = new InputString(f, Message.newSaveAs());
				f.parse();
				entity().saveFolha(name.toString());
				return;
			}
			entity().saveFolha(entity().getFolha().getName());
		}
	}
	
}	