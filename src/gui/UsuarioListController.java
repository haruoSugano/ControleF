package gui;

import java.io.Serializable;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Usuario;
import model.services.UsuarioService;

public class UsuarioListController implements Initializable {
	
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
	public void onBtNovoAction() {
		System.out.println("onBtNovoAction");
	}
	
	public void setUsuarioService(UsuarioService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
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
}
