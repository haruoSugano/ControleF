package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	/**
	 * Para acessar o Stage, onde o controle do evento esta
	 * ex: Se clicar no botão, ele pega o stage deste botão.
	 * @param event
	 * @return
	 */
	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node)event.getSource()).getScene().getWindow();
	}
	
	/**
	 * Converter os valores da caixa de texto em inteiros.
	 * @param str
	 * @return valor "String" convertido em um "Integer"
	 */
	public static Integer tryParseToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			return null;
		}
	}
}
