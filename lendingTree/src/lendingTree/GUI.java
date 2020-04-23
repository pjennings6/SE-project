package lendingtree;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
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
	TextArea clock;
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{	
			clock = new TextArea();
	        clock.setEditable(false);
	        clock.setPrefHeight(30);   
	        // clock.setPrefWidth(900);
	        
	        Image pic = new Image("/resources/lending_tree_logo.png");
	        ImageView imageOK = new ImageView(pic);
	        imageOK.setFitWidth(400);
	        imageOK.setPreserveRatio(true);

	        Button help = new Button("Help");
	        help.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	Alert a = new Alert(AlertType.INFORMATION); 
	            	a.setContentText("LendingTree is America's largest online lending marketplace. Please fill out the form to find the best loan for your next home or car.");
	                a.show(); 
	            }
	        });
	        
			String properties[] = {"Single Family Home", "Multi Family Home", "Townhome", "Condominium", "Mobile Home"};
			
			// Type of Loan Radio Buttons
			ToggleGroup group = new ToggleGroup();
			
			Label typeOfLoan = new Label("Type of Loan: ");
			
			RadioButton homeLoan = new RadioButton("Home");
			RadioButton carLoan = new RadioButton("Car");
			
			typeOfLoan.setPrefWidth(215);
			typeOfLoan.setMaxWidth(215);			
			homeLoan.setToggleGroup(group);
			carLoan.setToggleGroup(group);			
			carLoan.setTranslateX(100);
			
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
			Slider creditScoreS = new Slider(300, 850, 580);
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
			
			// loan options
			TextArea loanOptions; 
			loanOptions = new TextArea();
	        loanOptions.setEditable(false);
	        loanOptions.setVisible(false);
			
			Button submit = new Button("Submit");
			submit.setOnAction(new EventHandler<ActionEvent>() 
	        { 
				
	            @Override public void handle(ActionEvent e)
	            {
	            	// Error-Checking Input 
	            	Alert a = new Alert(AlertType.NONE); 
	            	boolean isValid = true; 
	            	if (nameTF.getText().trim().isEmpty() || addressTF.getText().trim().isEmpty() || cityTF.getText().trim().isEmpty() || 
	            		stateTF.getText().trim().isEmpty() || zipTF.getText().trim().isEmpty() || phoneTF.getText().trim().isEmpty() || 
	            		carLoan.getText().trim().isEmpty() || amountTF.getText().trim().isEmpty() || accountHolderTF.getText().trim().isEmpty() || 
	            		bankNameTF.getText().trim().isEmpty() || accountTypeTF.getText().trim().isEmpty() || routingNumberTF.getText().trim().isEmpty() || 
	            		accountNumberTF.getText().trim().isEmpty() || downPaymentTF.getText().trim().isEmpty() || (homeLoan.isSelected() && PropertyTypeM.getSelectionModel().isEmpty())) 
	            	{
	            		System.out.println("input");
	            		isValid = false; 
	            		a.setAlertType(AlertType.WARNING); 
	            		a.setContentText("Please enter all input fields.");
           		 	 	a.show();
	            	}
	            	
	            	String typeOfLoan; 
	            	
	            	if (isValid)
	            	{
	            		if (carLoan.isSelected())
		            	{
		            		typeOfLoan = "Car";
		            	}
		            	else 
		            	{
		            		typeOfLoan = "Home";
		            	}
		            	String transaction;
		            	if(carLoan.isSelected())
		            	{
		            		fileIO dataEntry = new fileIO();
		            		transaction = nameTF.getText() + " | " + addressTF.getText() + " | " + cityTF.getText() 
		            		+ " | " + stateTF.getText() + " | " +  zipTF.getText() + " | " +  phoneTF.getText() + " | " + carLoan.getText()
		            		+ " | " + amountTF.getText() + " | " + accountHolderTF.getText() + " | " + bankNameTF.getText()
		            		+ " | " + accountTypeTF.getText() + " | " + routingNumberTF.getText() + " | " + accountNumberTF.getText() + " | "
		            		+ downPaymentTF.getText();
		            		dataEntry.wrTransactionData(transaction);
		            	}
		            	else
		            	{
		            		fileIO dataEntry = new fileIO();
		            		transaction = nameTF.getText() + " | " + addressTF.getText() + " | " + cityTF.getText() 
		            		+ " | " + stateTF.getText() + " | " +  zipTF.getText() + " | " +  phoneTF.getText() + " | " + homeLoan.getText()
		            		+ " | " + PropertyTypeM.getValue() + " | " +  amountTF.getText() + " | " + accountHolderTF.getText() + " | " + bankNameTF.getText()
		            		+ " | " + accountTypeTF.getText() + " | " + routingNumberTF.getText() + " | " + accountNumberTF.getText() + " | "
		            		+ downPaymentTF.getText();
		            		dataEntry.wrTransactionData(transaction);
		            	}
		            	
		            	loanOptions.setVisible(true);
		            	
		            	Platform.runLater(new Runnable() 
						{
						        public void run() 
						        {
						            socketUtils su = new socketUtils();
						            
						            if (su.socketConnect() == true)
						            {
						            	System.out.print("connected to server");
						            	String msg = "Transaction> Name: " + nameTF.getText() + ", Address: " + addressTF.getText() + ", City: " + cityTF.getText() 
					            		+ ", State: " + stateTF.getText() + ", Zip: " +  zipTF.getText() + ", Phone Number: " +  phoneTF.getText() + ", Loan Type: " + homeLoan.getText()
					            		+ ", Property Type: " + PropertyTypeM.getValue() + ", Loan Size: " +  amountTF.getText() + ", Bank Account Holder: " + accountHolderTF.getText() + ", Bank: " + bankNameTF.getText()
					            		+ ", Account Type: " + accountTypeTF.getText() + ", Routing Number: " + routingNumberTF.getText() + ", Account Number: " + accountNumberTF.getText() + ", Down Payment: "
					            		+ downPaymentTF.getText();
		            	                
		            	              //  String rs = su.recvMessage();
		            	                su.closeSocket();
		            	                
		            	                loanOptions.setVisible(true);
		            	                loanOptions.setText("");
		            	            //    loanOptions.setText("RECV : " + rs);
						            }
						            else
						            {
						            	Alert alert = new Alert(Alert.AlertType.ERROR);
								        alert.setTitle("--- Network Communications Error ---");
								        alert.setHeaderText("Unable to talk to Socket Server!");
								          
								        alert.showAndWait();
						            }
						        }
		            	});
	            	}
	            	
	            	
	            }
	        });
	        
			Button exit = new Button("Exit");
	        exit.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	primaryStage.close();
	            }
	        });
	        
	        VBox root = new VBox();
			VBox pane001 = new VBox();
			VBox pane0 = new VBox(); 
			GridPane pane1 = new GridPane(); // user info
			
			pane001.getChildren().add(clock);
			pane001.getChildren().add(help);
			pane001.setPadding(new Insets(10, 0, 10, 20));
			
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
			pane1.add(typeOfLoan, 0, 6);							
			pane1.add(homeLoan, 1, 6);
			pane1.add(carLoan, 1, 6);
			pane1.add(PropertyType, 0, 7);
			pane1.add(PropertyTypeM, 1, 7);
			pane1.add(amount, 0, 8);
			pane1.add(amountTF, 1, 8);
			pane1.add(accountHolder, 0, 9);
			pane1.add(accountHolderTF, 1, 9);
			pane1.add(bankName, 0, 10);
			pane1.add(bankNameTF, 1, 10);
			pane1.add(accountType, 0, 11);
			pane1.add(accountTypeTF, 1, 11);
			pane1.add(routingNumber, 0, 12);
			pane1.add(routingNumberTF, 1, 12);
			pane1.add(accountNumber, 0, 13);
			pane1.add(accountNumberTF, 1, 13);
			pane1.add(creditScore, 0, 14);
			pane1.add(creditScoreS, 1, 14);
			pane1.add(CSValue, 2, 14);
			pane1.add(downPayment, 0, 15);
			pane1.add(downPaymentTF, 1, 15);
			pane1.add(submit, 0, 16);
			pane1.add(exit, 1, 16);
//			pane1.add(loanOptions, 0, 17);
			pane1.setAlignment(Pos.CENTER);
					
			pane0.setPadding(new Insets(10, 0, 50, 0));
			pane1.setVgap(25);
			pane1.setHgap(15);
				
			// Root
			root.getChildren().addAll(pane001, pane0, pane1);
			
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setFitToWidth(true);
						
			root.minWidth(scrollPane.getMinViewportWidth());
			root.setAlignment(Pos.CENTER);
			scrollPane.setContent(root);
			
			refreshClock();
		    
			Scene scene = new Scene(scrollPane,800,750);
			scene.getStylesheets().add("style.css");
			primaryStage.setTitle("Lending Tree");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
    // Clock - thread code
    private void refreshClock()
    {
    	Thread refreshClock = new Thread()
		   {  
			  public void run()
			  {	 
				while (true)
				{
					Date dte = new Date();
		
					String topMenuStr = "       " + dte.toString();					      
				    clock.setText(topMenuStr); 
			               
				    try
				    {
					   sleep(3000L);
				    }
				    catch (InterruptedException e) 
				    {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
				    } 
	            }	 
		    }
		 };

	     refreshClock.start();
    }

	public static void main(String[] args) {
		launch(args);
	}
}
