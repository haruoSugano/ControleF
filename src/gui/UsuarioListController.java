package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Usuario;
import model.services.UsuarioService;

public class UsuarioListController implements Initializable, DataChangeListener {
	
	private UsuarioService service;
	
	@FXML
	private TableView<Usuario> tableViewUsuario;
	
	@FXML
	private TableColumn<Usuario, Integer> tableViewColumnId;
	
	@FXML
	private TableColumn<Usuario, String> tableViewColumnNome;
	
	@FXML
	private TableColumn<Usuario, Integer> tableViewColumnIdade;
	
	@FXML
	private Button btNovo;
	
	private ObservableList<Usuario> obsList;
	
	@FXML
	public void onBtNovoAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Usuario usuario = new Usuario();
		createDialogForm(usuario, "/gui/UsuarioForm.fxml", parentStage);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	public void setUsuarioService(UsuarioService service) {
		this.service = service;
	}

	/**
	 * Iniciar o comportamento das colunas:
	 */
	private void initializeNodes() {
		tableViewColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableViewColumnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		tableViewColumnIdade.setCellValueFactory(new PropertyValueFactory<>("Idade"));
		/**
		 * Fazer com que a tabela se ajuste com a janela:
		 */
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewUsuario.prefHeightProperty().bind(stage.heightProperty());
	}
	/**
	 * Responsável por acessar os serviços, carregar os usuarios e jogar no ObservableList:
	 */
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Usuario> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewUsuario.setItems(obsList);
	}
	
	/**
	 * Criando uma janela de diálogo
	 * @param usuario
	 * @param absoluteName
	 * @param parentStage
	 */
	public void createDialogForm(Usuario usuario, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			UsuarioFormController controller = loader.getController();
			controller.setUsuario(usuario);
			controller.setUsuarioService(new UsuarioService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Insira os dados do usuário ");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	/**
	 * Recebe o evento
	 * Atualizando a tabela após o cadastro
	 */
	@Override
	public void onDataChanged() {
		updateTableView();
	}
}
