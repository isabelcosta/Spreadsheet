
package calc.menu.EditionMenu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import calc.menu.EditionMenu.Opcoes.Copy;
import calc.menu.EditionMenu.Opcoes.Cut;
import calc.menu.EditionMenu.Opcoes.Erase;
import calc.menu.EditionMenu.Opcoes.Insert;
import calc.menu.EditionMenu.Opcoes.Paste;
import calc.menu.EditionMenu.Opcoes.Visualize;
import calc.menu.EditionMenu.Opcoes.VisualizeCutBuffer;
import calc.textui.Calc;
import calc.textui.edit.MenuEntry;
//import calc.textui.search.Message;

public class EditionMenu extends Menu {

    /**
     * Construtor do menu de edicao da folha de calculo
     *
     * @param app
     * 				a aplicacao que a suporta a folha de calculo.
     */
	public EditionMenu(Calc app) {
		super(MenuEntry.TITLE, new Command[] {

            new Visualize(app.getFolha()),
		   
            new Insert(app.getFolha()),
			
            new Copy(app.getFolha()),
            
            new Erase(app.getFolha()),

            new Cut(app.getFolha()),

            new Paste(app.getFolha()),

            new VisualizeCutBuffer(app.getFolha())

		});
    }
}
