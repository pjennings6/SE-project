package lendingTree;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class GUI extends Application 
{

	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{	
			String properties[] = {"Single Family Home", "Multi Family Home", "Townhome", "Condominium", "Mobile Home"};
			
			ToggleGroup group = new ToggleGroup();
			
			RadioButton homeLoan = new RadioButton("Home");
			RadioButton carLoan = new RadioButton("Car");
			homeLoan.setToggleGroup(group);
			carLoan.setToggleGroup(group);
			
			Label PropertyType = new Label("Type of property: ");
			PropertyType.setVisible(false);
			ComboBox<String> PropertyTypeM = new ComboBox<String>(FXCollections.observableArrayList(properties));
			PropertyTypeM.setVisible(false);
			
			homeLoan.setOnAction(event -> {
				 // show the label
				 if(homeLoan.isSelected())
				 {
					 PropertyTypeM.setVisible(true);
					 PropertyType.setVisible(true);
				 }
		    });
			carLoan.setOnAction(event -> {
				 // show the label
				 if(carLoan.isSelected())
				 {
					 PropertyTypeM.setVisible(false);
					 PropertyType.setVisible(false);
				 }
		    });
			
			Label amount = new Label("Amount Desired: ");
			TextField amountTF = new TextField();
			
			Label accountHolder = new Label("Name of account holder: ");
			TextField accountHolderTF = new TextField();
			Label bankName = new Label("Banking Institution: ");
			TextField bankNameTF = new TextField();
			Label accountType = new Label("Account Type (checking/savings): ");
			TextField accountTypeTF = new TextField();
			Label routingNumber = new Label("Routing Number:");
			TextField routingNumberTF = new TextField();
			Label accountNumber = new Label("Account Number:");
			TextField accountNumberTF = new TextField();
					
			Label creditScore = new Label("Credit Score: ");
			Slider creditScoreS = new Slider(300, 800, 550);
			Label CSValue = new Label(Double.toString(creditScoreS.getValue()));
			 
			creditScoreS.valueProperty().addListener(new ChangeListener<Number>() 
			{
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
				{
					CSValue.setText(String.format("%.2f", new_val));
		        }
		    });			
			
			Label downPayment = new Label("Down Payment: ");
			TextField downPaymentTF = new TextField();
			
			Label name = new Label("Name: ");
			TextField nameTF = new TextField();
			nameTF.setPrefWidth(400);
			nameTF.setMaxWidth(400);
			Label address = new Label("Address: ");
			TextField addressTF = new TextField();
			addressTF.setPrefWidth(400);
			addressTF.setMaxWidth(400);
			Label city = new Label("City: ");
			TextField cityTF = new TextField();
			cityTF.setPrefWidth(400);
			cityTF.setMaxWidth(400);
			Label state = new Label("State: ");
			TextField stateTF = new TextField();
			stateTF.setPrefWidth(400);
			stateTF.setMaxWidth(400);
			Label zip = new Label("Postal Code:" );
			TextField zipTF = new TextField();
			zipTF.setPrefWidth(400);
			zipTF.setMaxWidth(400);
			Label phone = new Label("Phone number");
			TextField phoneTF = new TextField();
			phoneTF.setPrefWidth(400);
			phoneTF.setMaxWidth(400);
			
			VBox root = new VBox();
			
			VBox pane1 = new VBox();
			GridPane pane2 = new GridPane();
			GridPane pane3 = new GridPane();		
			
			pane1.getChildren().add(name);
			pane1.getChildren().add(nameTF);
			pane1.getChildren().add(address);
			pane1.getChildren().add(addressTF);
			pane1.getChildren().add(city);
			pane1.getChildren().add(cityTF);
			pane1.getChildren().add(state);
			pane1.getChildren().add(stateTF);
			pane1.getChildren().add(zip);
			pane1.getChildren().add(zipTF);
			pane1.getChildren().add(phone);
			pane1.getChildren().add(phoneTF);
			
											
			pane2.add(homeLoan, 0, 1);
			pane2.add(carLoan, 1, 1);
			
			pane3.add(PropertyType, 0, 0);
			pane3.add(PropertyTypeM, 1, 0);
			pane3.add(amount, 0, 1);
			pane3.add(amountTF, 1, 1);
			pane3.add(accountHolder, 0, 2);
			pane3.add(accountHolderTF, 1, 2);
			pane3.add(bankName, 0, 3);
			pane3.add(bankNameTF, 1, 3);
			pane3.add(accountType, 0, 4);
			pane3.add(accountTypeTF, 1, 4);
			pane3.add(routingNumber, 0, 5);
			pane3.add(routingNumberTF, 1, 5);
			pane3.add(accountNumber, 0, 6);
			pane3.add(accountNumberTF, 1, 6);
			pane3.add(creditScore, 0, 7);
			pane3.add(creditScoreS, 1, 7);
			pane3.add(CSValue, 2, 7);
			pane3.add(downPayment, 0, 8);
			pane3.add(downPaymentTF, 1, 8);
			
			pane1.setSpacing(5);
			
			pane2.setHgap(10);
			pane2.setVgap(10);
			
			pane3.setVgap(5);
					
						
			root.getChildren().addAll(pane1, pane2, pane3);
			
			Scene scene = new Scene(root,800,750);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
