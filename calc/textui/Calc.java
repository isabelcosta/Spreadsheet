package calc.textui;

import static pt.utl.ist.po.ui.UserInteraction.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import pt.utl.ist.po.ui.Menu;
import calc.AppMenu;
import calc.core.FolhaDeCalculo;
import calc.core.Parser;

/**
* @author Isabel Costa 76394
* @author Inês Garcia 76417
* @author Eliana Silva 76455
*/

public class Calc implements Serializable{
	
	/**
	 * Serial number for serialization
	 */
	private static final long serialVersionUID = -6057555101405162927L;

	/**
	 * Folha de Calculo da aplicacao
	 */
	private FolhaDeCalculo _folhaCalculo = null;
	
	/**
	 * Variavel de estado que indica se a folha esta aberta
	 */
	private boolean _isOpen = false;
	
	
	public boolean isOpen(){
		return _isOpen;
	}
	
	public void openFolha(){
		_isOpen = true;
	}
	
	public FolhaDeCalculo getFolha(){
		return _folhaCalculo ;
	}

	public void setFolha(FolhaDeCalculo f){
		_folhaCalculo = f;

	}
	
	public void setMyFolha(FolhaDeCalculo folha){
		_folhaCalculo = folha;	
	}
		
	public void loadFolha(String name) throws ClassNotFoundException, FileNotFoundException, IOException {
				ObjectInputStream in = new ObjectInputStream (new FileInputStream(name));
				_folhaCalculo = (FolhaDeCalculo) in.readObject();
				in.close();
				_folhaCalculo.setName(name);
				openFolha();
		}
	
	public void saveFolha(String name){
		try{
			_folhaCalculo.setName(name);
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(name));  
			out.writeObject(_folhaCalculo);
			out.close();
			_folhaCalculo.setStateFolha(true);
		} catch (IOException i){
			System.err.println(i.getMessage());
		}
	}

	public static void main(String args[]){

		Calc app = new Calc();
		/* Read an Import file, if any */
		String filename = System.getProperty("import");
		if (filename != null){
			try {
				app.setFolha(Parser.parseFile(filename));
				app.openFolha();
				
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
				
			} catch (IOException e) {
				System.err.println(e.getMessage());
				
			} catch (Exception e){
				System.err.println(e.getMessage());
			}
		}
		
		IO.setTitle("");
		Menu m = new AppMenu(app);
		m.open();
		IO.close();

	}

}

