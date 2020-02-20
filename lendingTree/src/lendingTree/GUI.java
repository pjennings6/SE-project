package lendingTree;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;


public class GUI extends Application 
{
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{			
	        Image pic = new Image("/resources/lending_tree_logo.png");
	        ImageView imageOK = new ImageView(pic);
	        imageOK.setFitWidth(400);
	        imageOK.setPreserveRatio(true);

			String properties[] = {"Single Family Home", "Multi Family Home", "Townhome", "Condominium", "Mobile Home"};
			
			// Type of Loan Radio Buttons
			ToggleGroup group = new ToggleGroup();
			Label typeOfLoan = new Label("Type of Loan: ");
			typeOfLoan.setPrefWidth(215);
			typeOfLoan.setMaxWidth(215);
			RadioButton homeLoan = new RadioButton("Home");
			RadioButton carLoan = new RadioButton("Car");
			homeLoan.setPrefWidth(80);
			homeLoan.setMaxWidth(80);
			carLoan.setPrefWidth(80);
			carLoan.setMaxWidth(80);
			homeLoan.setToggleGroup(group);
			carLoan.setToggleGroup(group);
			
			// Type of property (if auto loan) 
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
			
			// loan amount desired 
			Label amount = new Label("Amount Desired: ");
			TextField amountTF = new TextField();
			
			// bank account info
			Label accountHolder = new Label("Name of account holder: ");
			TextField accountHolderTF = new TextField();
			accountHolderTF.setPrefWidth(185);
			accountHolderTF.setMaxWidth(185);
			Label bankName = new Label("Banking Institution: ");
			TextField bankNameTF = new TextField();
			Label accountType = new Label("Account Type (checking/savings): ");
			TextField accountTypeTF = new TextField();
			Label routingNumber = new Label("Routing Number:");
			TextField routingNumberTF = new TextField();
			Label accountNumber = new Label("Account Number:");
			TextField accountNumberTF = new TextField();
					
			// credit score 
			Label creditScore = new Label("Credit Score: ");
			Slider creditScoreS = new Slider(300, 850, 550);
			Label CSValue = new Label(Double.toString(creditScoreS.getValue()));
			creditScoreS.valueProperty().addListener(new ChangeListener<Number>() 
			{
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
				{
					CSValue.setText(String.format("%.0f", new_val));
		        }
		    });			
			
			Label downPayment = new Label("Down Payment: ");
			TextField downPaymentTF = new TextField();
			
			// user info
			Label name = new Label("Name: ");
			TextField nameTF = new TextField();
			nameTF.setPrefWidth(300);
			nameTF.setMaxWidth(300);
			Label address = new Label("Address: ");
			TextField addressTF = new TextField();
			addressTF.setPrefWidth(300);
			addressTF.setMaxWidth(300);
			Label city = new Label("City: ");
			TextField cityTF = new TextField();
			cityTF.setPrefWidth(300);
			cityTF.setMaxWidth(300);
			Label state = new Label("State: ");
			TextField stateTF = new TextField();
			stateTF.setPrefWidth(300);
			stateTF.setMaxWidth(300);
			Label zip = new Label("Postal Code:" );
			TextField zipTF = new TextField();
			zipTF.setPrefWidth(300);
			zipTF.setMaxWidth(300);
			Label phone = new Label("Phone number:");
			TextField phoneTF = new TextField();
			phoneTF.setPrefWidth(300);
			phoneTF.setMaxWidth(300);
			
			VBox root = new VBox();
			VBox pane0 = new VBox(10); 
			GridPane pane1 = new GridPane(); // user info
			GridPane pane2 = new GridPane(); // type of loan 
			GridPane pane3 = new GridPane(); // bank info, etc.
			
			pane0.getChildren().add(imageOK);
			pane0.setAlignment(Pos.CENTER);

			pane1.add(name, 0, 0);
			pane1.add(nameTF, 1, 0);
			pane1.add(address, 0, 1);
			pane1.add(addressTF, 1, 1);
			pane1.add(city, 0, 2);
			pane1.add(cityTF, 1, 2);
			pane1.add(state, 0, 3);
			pane1.add(stateTF, 1, 3);
			pane1.add(zip, 0, 4);
			pane1.add(zipTF, 1, 4);
			pane1.add(phone, 0, 5);
			pane1.add(phoneTF, 1, 5);
			pane1.setAlignment(Pos.CENTER);
			
			pane2.add(typeOfLoan, 0, 1);							
			pane2.add(homeLoan, 1, 1);
			pane2.add(carLoan, 2, 1);
			pane2.setAlignment(Pos.CENTER);
			
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
			pane3.setAlignment(Pos.CENTER);
			
			pane0.setPadding(new Insets(10, 0, 50, 0));
			pane1.setVgap(25);
			pane1.setHgap(15);
			pane2.setHgap(10);
			pane2.setVgap(10);
			pane2.setPadding(new Insets(10, 0, 10, 0));
			pane3.setVgap(10);
						
			root.getChildren().addAll(pane0, pane1, pane2, pane3);
			root.setPadding(new Insets(0, 400, 0, 400));
			
			ScrollPane scrollPane = new ScrollPane();
			
			root.minWidth(scrollPane.getMinViewportWidth());
			root.setAlignment(Pos.CENTER);
			scrollPane.setContent(root);
		    
			Scene scene = new Scene(scrollPane,800,750);
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
