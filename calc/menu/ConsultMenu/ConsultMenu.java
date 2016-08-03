package calc.menu.ConsultMenu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import calc.menu.ConsultMenu.Opcoes.SearchFunction;
import calc.menu.ConsultMenu.Opcoes.SearchValue;
import calc.textui.Calc;
import calc.textui.search.MenuEntry;



public class ConsultMenu extends Menu {

    /**
     * Construtor do menu de consulta da folha de calculo
     *
     * @param app
     * 				a aplicacao que a suporta a folha de calculo.
     */
	public ConsultMenu(Calc app) {
		super(MenuEntry.TITLE, new Command[] {

		    new SearchValue(app.getFolha()),
			
            new SearchFunction(app.getFolha())
		});
    }
}
