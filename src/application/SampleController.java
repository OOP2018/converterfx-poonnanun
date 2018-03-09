package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

/**
 * Class that use for controll the application event action.
 * @author Poonnanun Poonnopathum
 *
 */
public class SampleController {
	
	@FXML
	private Button convert; 
	
	@FXML
	private Button clear;
	
	@FXML
	private TextField before;
	
	@FXML
	private TextField after;
	
	@FXML
	private ComboBox<Length> from;
	
	@FXML
	private ComboBox<Length> to;
	
	private static int check = 1;
	private static Alert error = new Alert(Alert.AlertType.WARNING);
	
	/**
	 * When start program add item to combo box.
	 * and set the enter pressed to run converter.
	 */
	@FXML 
	public void initialize(){
		if(from != null && to != null){
			from.getItems().addAll(Length.values());
			from.getSelectionModel().select(0);
			to.getItems().addAll(Length.values());
			to.getSelectionModel().select(0);
		}
		before.setOnKeyPressed(event -> {
			after.clear();
			check = 1;
			if (event.getCode().equals(KeyCode.ENTER)){
				convert.fire();
			}
		});
		after.setOnKeyPressed(event -> {
			before.clear();
			check = 2;
			if (event.getCode().equals(KeyCode.ENTER)){
				convert.fire();
			}
		});
		
	}
	
	/**
	 * go to methos form last edit field by check local attribute
	 * @param e
	 */
	@FXML
	public void converterClick(ActionEvent e){
		if(check == 1) handleConvertLR();
		if(check == 2) handleConvertRL();
	}
	/**
	 * Event that use for convert length from left to right
	 * @param e
	 */
	public void handleConvertLR(){
		
		if(before.getText().equals("")) {
			//In case that both are null
			error.setHeaderText("Input your value");
			error.showAndWait();
			return;
		}
		//Start case a to b
		double beforeNumber = Double.parseDouble(before.getText().trim());
		double afterNumber = 0;
		
		//Check if length is lower than zero
		if(beforeNumber < 0){
			error.setHeaderText("Length should not be lower than zero");
			error.showAndWait();
			return;
		}
		
		//Check if combo box is null
		if(from.getValue() == null || to.getValue() == null){
			error.setHeaderText("Select the convert type!!");
			return;
		}
		
		//Get the convertor type
		String from = this.from.getValue().toString().trim();
		String to = this.to.getValue().toString().trim();
		
		//get the value for convertor
		double multiplyNum = -1;
		double divideNum = -1;
		Length[] lengths = Length.values();
		for(Length a: lengths){
			if(from.equals(a.toString())){
				multiplyNum = a.getValue();
			}
			if(to.equals(a.toString())){
				divideNum = a.getValue();
			}
		}
		
		//Calculate in formular
		if(multiplyNum != -1 || divideNum != 1){
			afterNumber = (beforeNumber * multiplyNum) / divideNum;
		}
		
		//Check if answer isn't null
		if(afterNumber != 0){
			after.setText(String.format("%.4g", afterNumber));
		}
		
		//in case that something went wrong
		else{
			error.setHeaderText("Error!!!");
			error.showAndWait();
		}
	}
	

	/**
	 * Event that use for convert length from right to left
	 * @param e
	 */
	public void handleConvertRL(){
		if(!after.getText().equals("")){
			
			//Start case b to a
			double beforeNumber = Double.parseDouble(after.getText().trim());
			double afterNumber = 0;
			
			//Check if length is lower than zero
			if(beforeNumber < 0){
				error.setHeaderText("Length should not be lower than zero");
				error.showAndWait();
				return;
			}
			
			//Check if combo box is null
			if(from.getValue() == null || to.getValue() == null){
				error.setHeaderText("Select the convert type!!");
				return;
			}
			
			//Get the convertor type in the reverse order 
			String to = this.from.getValue().toString().trim();
			String from = this.to.getValue().toString().trim();
			
			//get the value for convertor
			double multiplyNum = -1;
			double divideNum = -1;
			Length[] lengths = Length.values();
			for(Length a: lengths){
				if(from.equals(a.toString())){
					multiplyNum = a.getValue();
				}
				if(to.equals(a.toString())){
					divideNum = a.getValue();
				}
			}
			
			//Calculate in formular
			if(multiplyNum != -1 || divideNum != 1){
				afterNumber = (beforeNumber * multiplyNum) / divideNum;
			}
			//Check if answer is not null
			if(afterNumber != 0){
				before.setText(String.format("%.4g",afterNumber));
				return;
			}
			//In case that something wrong
			else{
				error.setHeaderText("Error!!!");
				error.showAndWait();
				return;
			}
		}
		error.setHeaderText("Input your value");
		error.showAndWait();
	}
	/**
	 * Event that clear every thing in textField
	 * @param e
	 */
	@FXML
	public void handleClear(ActionEvent e){
		before.clear();
		after.clear();
	}
}
