package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.UsuarioService;

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
		loadView("/gui/UsuarioList.fxml", (UsuarioListController controller) -> {
			controller.setUsuarioService(new UsuarioService());
			controller.updateTableView();
		});
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
		loadView("/gui/about.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	/**
	 * Carregar uma tela
	 * @param absoluteName
	 */
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			/**
			 * Ativar a função initializingAction:
			 */
			T Controller = loader.getController();
			initializingAction.accept(Controller);
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
