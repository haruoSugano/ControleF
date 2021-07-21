package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemCadastrarRemover;
	
	@FXML
	private MenuItem menuItemAdicionarRemover;
	
	@FXML
	private MenuItem menuItemDarBaixa;
	
	@FXML
	private MenuItem menuItemPlanilha;
	
	@FXML
	private MenuItem menuItemCalendario;
	
	@FXML
	private MenuItem menuItemSobre;
	
	/**
	 * Tratar os eventos dos itens do menu:
	 */
	@FXML
	public void onMenuItemCadastrarRemoverAction() {
		System.out.println("onMenuItemRegistrarUsuarioAction");
	}
	
	@FXML
	public void onMenuItemAdicionarRemoverAction() {
		System.out.println(" onMenuItemAdicionarRemover");
	}
	
	@FXML
	public void onMenuItemDarBaixaAction() {
		System.out.println("onMenuItemDarBaixa");
	}
	
	@FXML
	public void onMenuItemPlanilhaAction() {
		System.out.println("onMenuItemPalnilha");
	}
	
	@FXML
	public void onMenuItemCalendarioAction() {
		System.out.println("onMenuItemCalendario");
	}
	
	@FXML
	public void onMenuItemSobreAction() {
		System.out.println("onMenuItemAjuda");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

}
