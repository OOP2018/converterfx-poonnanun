package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
	
	/**
	 * When start program add item to combo box.
	 */
	@FXML 
	public void initialize(){
		if(from != null && to != null){
			from.getItems().addAll(Length.values());
			to.getItems().addAll(Length.values());
		}
	}
	
	/**
	 * Event that use for convert length
	 * @param e
	 */
	@FXML
	public void handleConvert(ActionEvent e){
		
		if(before.getText().equals("")) {
			//Check in case convert from b to a
			if(!after.getText().equals("")){
				
				//Start case b to a
				double beforeNumber = Double.parseDouble(after.getText().trim());
				double afterNumber = 0;
				
				//Check if combo box is null
				if(from.getValue() == null || to.getValue() == null){
					before.setText("Select type!!!");
					after.setText("Select type!!!"); 
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
					before.setText(String.valueOf(afterNumber));
					return;
				}
				//In case that something wrong
				else{
					before.setText("Error!!!");
					after.setText("Error!!!");
					return;
				}
			}
			//In case that both are null
			before.setText("Enter the value!"); 
			after.setText("Enter the value!"); 
			return;
		}
		//Start case a to b
		double beforeNumber = Double.parseDouble(before.getText().trim());
		double afterNumber = 0;
		
		//Check if combo box is null
		if(from.getValue() == null || to.getValue() == null){
			before.setText("Select type!!!");
			after.setText("Select type!!!"); 
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
			after.setText(String.valueOf(afterNumber));
		}
		
		//in case that something went wrong
		else{
			before.setText("Error!!!");
			after.setText("Error!!!");
		}
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
