package lendingTree;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.input.MouseEvent;
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
	        
	        // Type of Loan
			String properties[] = {"Single Family Home", "Multi Family Home", "Townhome", "Condominium", "Mobile Home"};
			// Radio Buttons
			ToggleGroup loanGroup = new ToggleGroup();
			
			Label typeOfLoan = new Label("Type of Loan: ");
			
			RadioButton homeLoan = new RadioButton("Home");
			RadioButton carLoan = new RadioButton("Car");
			
			typeOfLoan.setPrefWidth(215);
			typeOfLoan.setMaxWidth(215);			
			homeLoan.setToggleGroup(loanGroup);
			carLoan.setToggleGroup(loanGroup);			
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
						
			Label accountTypeLabel = new Label("Account Type: ");
			String accountTypeList[] = {"Checkings", "Savings"};
			ComboBox<String> accountTypeSelect = new ComboBox<String>(FXCollections.observableArrayList(accountTypeList));
			
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
			
			String listOfStates[] = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DC", "DE", "FL", "GA", 
	          "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", 
	          "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", 
	          "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", 
	          "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
			
//			String listOfStates[] = {"Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", 
//			"Delaware", "District of Columbia", "Florida", "Georgia", "Guam", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", 
//			"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Minor Outlying Islands", "Mississippi",
//			"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", 
//			"Northern Mariana Islands", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", 
//			"Tennessee", "Texas", "U.S. Virgin Islands", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
			
			Label state = new Label("State: ");
			ComboBox<String> stateCombo = new ComboBox<String>(FXCollections.observableArrayList(listOfStates));
		
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
	            	
	            	// checking if any input was left empty 
	            	Alert a = new Alert(AlertType.NONE); 
	            	boolean isValid = true; 
	            	if (nameTF.getText().trim().isEmpty() || addressTF.getText().trim().isEmpty() || cityTF.getText().trim().isEmpty() || 
	            		stateCombo.getSelectionModel().isEmpty() || zipTF.getText().trim().isEmpty() || phoneTF.getText().trim().isEmpty() || 
	            		carLoan.getText().trim().isEmpty() || amountTF.getText().trim().isEmpty() || accountHolderTF.getText().trim().isEmpty() || 
	            		bankNameTF.getText().trim().isEmpty() || accountTypeSelect.getSelectionModel().isEmpty() || routingNumberTF.getText().trim().isEmpty() || 
	            		accountNumberTF.getText().trim().isEmpty() || downPaymentTF.getText().trim().isEmpty() || (homeLoan.isSelected() && PropertyTypeM.getSelectionModel().isEmpty())) 
	            	{
	            		isValid = false; 
	            		System.out.println("input");
	            		a.setAlertType(AlertType.WARNING); 
	            		a.setContentText("Please enter all input fields.");
           		 	 	a.show();
	            	}
	            	
	            	// validating phone number using regex
            		Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
            	    Matcher matcher = pattern.matcher(phoneTF.getText().trim());
	            	if (!matcher.matches()) 
	            	{ 
	            		isValid = false; 
	            		a.setAlertType(AlertType.WARNING); 
	            		a.setContentText("Phone Number must be in the form XXX-XXX-XXXX");
           		 	 	a.show();
	            	}
	            	
	            	// making sure some inputs are valid integers 
	            	try 
	            	{
	            		long temp = Long.parseLong(amountTF.getText().trim());
	            		temp = Long.parseLong(routingNumberTF.getText().trim());
	            		temp = Long.parseLong(accountNumberTF.getText().trim());
	            		temp = Long.parseLong(downPaymentTF.getText().trim());
	            	}
	            	catch (NumberFormatException x)
	            	{ 
	            		isValid = false;
	            		a.setAlertType(AlertType.WARNING); 
	            		a.setContentText("Input is invalid.");
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
		            	
		            	Platform.runLater(new Runnable() 
						{
						        public void run() 
						        {
						            socketUtils su = new socketUtils();
						            
						            if (su.socketConnect() == true)
						            {
						            	long amount = Long.parseLong(amountTF.getText());
						            	long accountNum = Long.parseLong(accountNumberTF.getText());
						            	long payment = Long.parseLong(downPaymentTF.getText());
						            	
//						            	System.out.print("connected to server\n");
						            	String msg = "Transaction>" + nameTF.getText() + "," + cityTF.getText() 
					            		+ "," + stateCombo.getValue() + "," +  zipTF.getText() + "," +  phoneTF.getText() + "," + typeOfLoan
					            		+ "," + PropertyTypeM.getValue() + "," +  amount + "," + bankNameTF.getText()
					            		+ "," + accountTypeSelect.getValue() + "," + accountNum + ","
					            		+ payment;
		            	                su.sendMessage(msg);
		            	                
//		            	                String rs = su.recvMessage();
		            	                su.closeSocket();
		            	                primaryStage.close();
		            	                Stage secondaryStage = new Stage();
		            	                VBox pane = new VBox();
		            	                Label label = new Label("Here are your loan options (Click to choose one): ");
		            	                TextArea option1 = new TextArea("LightStream | APR Range: 5.95-9.24% | Est. Payment: $165/mo. | Loan Term: 72 mos. | Min. Credit Score: 720+");
		            	                TextArea option2 = new TextArea("Payoff | APR Range: 5.99-16.49% | Est. Payment: $304/mo. | Loan Term: 36 mos. | Min Credit Score: 720+");
		            	                TextArea option3 = new TextArea("Freedom Plus | APR Range: 6.99-26.55% | Est. Payment: $448/mo. | Loan Term: 24 mos. | Min Credit Score: 720+");
		            	                option1.setEditable(false);
		            	                option2.setEditable(false);
		            	                option3.setEditable(false);
		            	                option1.setWrapText(true);
		            	                option2.setWrapText(true);
		            	                option3.setWrapText(true);
		            	                option1.setOnMouseClicked(new EventHandler<MouseEvent>() {
		            	                	@Override
		            	                	public void handle(MouseEvent event) {
		            	                		Alert a = new Alert(AlertType.INFORMATION); 
		            	    	            	a.setContentText("Loan Selection Complete. You can now exit.");
		            	    	                a.show(); 
		            	                	}
		            	                });
		            	                option2.setOnMouseClicked(new EventHandler<MouseEvent>() {
		            	                	@Override
		            	                	public void handle(MouseEvent event) {
		            	                		Alert a = new Alert(AlertType.INFORMATION); 
		            	    	            	a.setContentText("Loan Selection Complete. You can now exit.");
		            	    	                a.show(); 
		            	                	}
		            	                });
		            	                option3.setOnMouseClicked(new EventHandler<MouseEvent>() {
		            	                	@Override
		            	                	public void handle(MouseEvent event) {
		            	                		Alert a = new Alert(AlertType.INFORMATION); 
		            	    	            	a.setContentText("Loan Selection Complete. You can now exit.");
		            	    	                a.show(); 
		            	                	}
		            	                });
		            	                Button exit = new Button("Exit");
		            	                exit.setPrefWidth(80);
		            	                exit.setTranslateX(400);
		            	                exit.setOnAction(new EventHandler<ActionEvent>() {
		            	                	public void handle(ActionEvent event) {
		            	                		secondaryStage.close();
		            	                	}
		            	                });	            	                
		            	                pane.getChildren().add(label);
		            	                pane.getChildren().add(option1);
		            	                pane.getChildren().add(option2);
		            	                pane.getChildren().add(option3);
		            	                pane.getChildren().add(exit);
		            	                Scene scene = new Scene(pane, 500, 350);
		            	                secondaryStage.setScene(scene);
		            	                secondaryStage.show();
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
			pane1.add(stateCombo, 1, 3);
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
			pane1.add(accountTypeLabel, 0, 11);
			pane1.add(accountTypeSelect, 1, 11);
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
			scene.getStylesheets().add("/resources/style.css");
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
