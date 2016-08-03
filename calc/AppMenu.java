
package calc;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import calc.menu.ConsultMenu.ConsultMenu;
import calc.menu.EditionMenu.EditionMenu;
import calc.menu.MainMenu.NewFolhaDeCalc;
import calc.menu.MainMenu.OpenFolhaCalc;
import calc.menu.MainMenu.SaveFolha;
import calc.menu.MainMenu.SaveFolhaAs;
import calc.textui.Calc;
// import pt.utl.ist.po.ui.Display;
// import calc.textui.main.*;
// import calc.textui.search.*;
// import calc.textui.edit.*;
// import calc.menu.ConsultMenu.*;
// import calc.menu.EditionMenu.*;


public class AppMenu extends Menu{


	public AppMenu(final Calc app) {
		super(calc.textui.main.MenuEntry.TITLE, new Command<?>[] {
		    new NewFolhaDeCalc(app),
		    new OpenFolhaCalc(app),
		    new SaveFolha(app),
		    new SaveFolhaAs(app),
			new Command<Calc> (calc.textui.edit.MenuEntry.TITLE, app) {
				public final void execute() {
					final Menu m = new EditionMenu(app);
					m.open();
				}
			},
		    new Command<Calc> (calc.textui.search.MenuEntry.TITLE, app) {
				public final void execute() {
					final Menu m = new ConsultMenu(app);
					m.open();
				}
			}
		});
		if(!(app.isOpen())){
			entry(2).invisible();
			entry(3).invisible();
			entry(4).invisible();
			entry(5).invisible();
		}
	}
	

}