package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Usuario;
import model.services.UsuarioService;

public class UsuarioFormController implements Initializable {
	
	private Usuario entity;
	
	private UsuarioService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtIdade;
	
	@FXML
	private Label labelErrorNome;
	
	@FXML
	private Button btSalvar;
	
	@FXML
	private Button btCacelar;
	
	@FXML
	public void onBtSalvarAction(ActionEvent event) {
		if (entity == null) {
			throw new IllegalStateException("A entidade está nula");
		}
		if (service == null) {
			throw new IllegalStateException("O service está nula");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			notifyDataChangeListernes();
			Utils.currentStage(event).close();
			//Alerts.showAlert("Informando o cadastro", null, "Usuário cadastrado com sucesso!", AlertType.INFORMATION);
		}
		catch(DbException e) {
			Alerts.showAlert("Erro ao salvar!", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	
	/**
	 * Emitir eventos
	 * Notificação de que os dados foram alterados.
	 */
	private void notifyDataChangeListernes() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	/**
	 * Instância do Usuario
	 * @param entity
	 */
	public void setUsuario(Usuario entity) {
		this.entity = entity;
	}
	
	public void setUsuarioService(UsuarioService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	/**
	 * Método usado para pegar os dados no textFiled e instanciar o usuário
	 * @return os dados do usuário digitado 
	 */
	private Usuario getFormData() {
		Usuario usuario = new Usuario();
		
		usuario.setId(Utils.tryParseToInt(txtId.getId()));
		usuario.setNome(txtNome.getText());
		usuario.setIdade(Utils.tryParseToInt(txtIdade.getText()));
		
		return usuario;
	}

	/**
	 * Controlador
	 * Controlando a quantidade de caractere inserido no campo de texto ou o tipo do texto.
	 */
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 30);
		Constraints.setTextFieldMaxLength(txtIdade, 3);
	}
	
	/**
	 * Inserir os dados do usuário no formulário
	 */
	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entidade está nula");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());
		txtIdade.setText(String.valueOf(entity.getIdade()));
	}
}
