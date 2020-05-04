package lendingTree;

import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
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
			// TODO: CLOCK
			clock = new TextArea();
	        clock.setEditable(false);
	        clock.setPrefHeight(10);
	        clock.setStyle("text-area-background: white; -fx-border-color: white;");
	        
	        Image pic = new Image("/resources/lending_tree_logo.png");
	        ImageView imageOK = new ImageView(pic);
	        imageOK.setFitWidth(400);
	        imageOK.setPreserveRatio(true);

	        Button help = new Button("Help");
	        help.setTooltip(new Tooltip("Click to see help information."));
	        help.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	Alert a = new Alert(AlertType.INFORMATION); 
	            	a.setContentText("LendingTree is America's largest online lending marketplace. Please fill out the form to find the best loan for your next home or car.");
	                a.show(); 
	            }
	        });
	        help.setStyle("-fx-font: 18 Corbel; -fx-background-radius: 20px; -fx-background-color: #99e1d9;");
	        
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
			amountTF.setPromptText("Enter a whole number, ex. 10000");
			// bank account info
			Label accountHolder = new Label("Name of account holder: ");
			TextField accountHolderTF = new TextField();
			accountHolderTF.setPromptText("Ex. John Smith");
			Label bankName = new Label("Banking Institution: ");
			TextField bankNameTF = new TextField();
			bankNameTF.setPromptText("Ex. Capital One Bank");	
			
			Label accountTypeLabel = new Label("Account Type: ");
			String accountTypeList[] = {"Checkings", "Savings"};
			ComboBox<String> accountTypeSelect = new ComboBox<String>(FXCollections.observableArrayList(accountTypeList));
			accountTypeSelect.setTooltip(new Tooltip("Select Checkings or Savings."));
			Label routingNumber = new Label("Routing Number:");
			TextField routingNumberTF = new TextField();
			routingNumberTF.setPromptText("Enter a number, ex. 55555");
			Label accountNumber = new Label("Account Number:");
			TextField accountNumberTF = new TextField();
			accountNumberTF.setPromptText("Enter a number, ex. 55555");
					
			// credit score 
			Label creditScore = new Label("Credit Score: ");
			Slider creditScoreS = new Slider(300, 850, 580);
			Label CSValue = new Label(Integer.toString((int)creditScoreS.getValue()));
			creditScoreS.valueProperty().addListener(new ChangeListener<Number>() 
			{
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
				{
					CSValue.setText(String.format("%.0f", new_val));
		        }
		    });	
			creditScoreS.setTooltip(new Tooltip("Move the slider to enter your credit score."));
			Label downPayment = new Label("Down Payment: ");
			TextField downPaymentTF = new TextField();
			downPaymentTF.setPromptText("Enter a whole number, ex. 10000");
			
			// user info
			Label name = new Label("Name: ");
			TextField nameTF = new TextField();
			nameTF.setPromptText("Ex. John Smith");
			nameTF.setPrefWidth(300);
			nameTF.setMaxWidth(300);
			Label address = new Label("Address: ");
			TextField addressTF = new TextField();
			addressTF.setPromptText("Enter your address");
			addressTF.setPrefWidth(300);
			addressTF.setMaxWidth(300);
			Label city = new Label("City: ");
			TextField cityTF = new TextField();
			cityTF.setPromptText("Ex. New York");
			cityTF.setPrefWidth(300);
			cityTF.setMaxWidth(300);
			
			String listOfStates[] = {"AL", "AR", "AK", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", 
	          "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MD", "ME",  
	          "MA", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NV",  
	          "NH", "NJ", "NM", "NY", "OH", "OK", "OR", "PA", "RI", "SC", 
	          "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WI", "WV", "WY"};
					
			Label state = new Label("State: ");
			ComboBox<String> stateCombo = new ComboBox<String>(FXCollections.observableArrayList(listOfStates));
			stateCombo.setTooltip(new Tooltip("Select the state/territory of your home address."));
			Label zip = new Label("Postal Code:" );
			TextField zipTF = new TextField();
			zipTF.setPromptText("Enter your five-digit zip code");
			zipTF.setPrefWidth(300);
			zipTF.setMaxWidth(300);
			Label phone = new Label("Phone number:");
			TextField phoneTF = new TextField();
			phoneTF.setPromptText("XXX-XXX-XXXX");
			phoneTF.setPrefWidth(300);
			phoneTF.setMaxWidth(300);
			
			// loan options
			TextArea loanOptions; 
			loanOptions = new TextArea();
	        loanOptions.setEditable(false);
	        loanOptions.setVisible(false);
			
			Button submit = new Button("Submit");
			submit.setStyle("-fx-font: 18 Corbel; -fx-background-radius: 20px; -fx-background-color: #99e1d9;");
			submit.setTooltip(new Tooltip("Click to submit your information. Double check to make sure your information is correct!"));
			submit.setOnAction(new EventHandler<ActionEvent>() 
	        { 
				
	            @Override public void handle(ActionEvent e)
	            {
	            	// converting credit score to int 
	            	int creditScore = (int)Math.round(creditScoreS.getValue());
	            	
	            	// ERROR-CHECKING INPUT 
	            	
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
	            		a.setAlertType(AlertType.WARNING); 
	            		a.setContentText("Please enter all input fields.");
           		 	 	a.show();
	            	}
	            	
	            	// validating credit score to be at least 720
	            	if (creditScore < 720)
	            	{
	            		isValid = false;
	            		a.setAlertType(AlertType.WARNING); 
	            		a.setContentText("Credit score is too low.");
           		 	 	a.show(); 
	            	}
	            	
	            	// validating phone number using regex
            		Pattern pattern_phone = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
            	    Matcher matcher_phone = pattern_phone.matcher(phoneTF.getText().trim());
	            	if (!matcher_phone.matches()) 
	            	{ 
	            		isValid = false; 
	            		a.setAlertType(AlertType.WARNING); 
	            		a.setContentText("Phone Number must be in the form XXX-XXX-XXXX");
           		 	 	a.show();
	            	}
	            	
	            	// validating zip code using regex
            		Pattern pattern_zip = Pattern.compile("\\d{5}");
            	    Matcher matcher_zip = pattern_zip.matcher(zipTF.getText().trim());
	            	if (!matcher_zip.matches()) 
	            	{ 
	            		isValid = false; 
	            		a.setAlertType(AlertType.WARNING); 
	            		a.setContentText("Zip code must be a five digit number");
           		 	 	a.show();
	            	} 
	            	
	            	// making sure some inputs are valid integers 
	            	try 
	            	{
	            		long amount = Long.parseLong(amountTF.getText().trim());
	            		long routingNum = Long.parseLong(routingNumberTF.getText().trim());
	            		long accountNum = Long.parseLong(accountNumberTF.getText().trim());
	            		long payment = Long.parseLong(downPaymentTF.getText().trim());
	            	}
	            	catch (NumberFormatException x)
	            	{ 
	            		isValid = false;
	            		a.setAlertType(AlertType.WARNING); 
	            		a.setContentText("Input is invalid.");
           		 	 	a.show();
	            		
	            	}
 
	            	String typeOfLoan; 
	            	if (isValid == true)
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
						        	long amount = Long.parseLong(amountTF.getText().trim());
				            		long routingNum = Long.parseLong(routingNumberTF.getText().trim());
				            		long accountNum = Long.parseLong(accountNumberTF.getText().trim());
				            		long payment = Long.parseLong(downPaymentTF.getText().trim());
				            		
						            socketUtils su = new socketUtils();
						            
						            if (su.socketConnect() == true)
						            {
						            	            	
						            	String msg = "Transaction>" + nameTF.getText().trim() + "," + addressTF.getText().trim() + "," + cityTF.getText().trim() 
					            		+ "," + stateCombo.getValue() + "," +  zipTF.getText().trim() + "," +  phoneTF.getText().trim() + "," + typeOfLoan
					            		+ "," + PropertyTypeM.getValue() + "," + amount + "," + accountHolderTF.getText().trim() + "," + bankNameTF.getText().trim()
					            		+ "," + accountTypeSelect.getValue() + "," + routingNum + "," + accountNum + "," + creditScore + "," + payment;
		            	               
		            	                
		            	                //String rs = su.recvMessage();
		            	                
		            	                
		            	                // loan options	            	              
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
		            	    	                String msg1 = msg + ",1";
		            	    	                su.sendMessage(msg1);
		            	    	                su.closeSocket();
		            	                	}
		            	                });
		            	                option2.setOnMouseClicked(new EventHandler<MouseEvent>() {
		            	                	@Override
		            	                	public void handle(MouseEvent event) {
		            	                		Alert a = new Alert(AlertType.INFORMATION); 
		            	    	            	a.setContentText("Loan Selection Complete. You can now exit.");
		            	    	                a.show(); 
		            	    	                String msg1 = msg + ",2";
		            	    	                su.sendMessage(msg1);
		            	    	                su.closeSocket();
		            	                	}
		            	                });
		            	                option3.setOnMouseClicked(new EventHandler<MouseEvent>() {
		            	                	@Override
		            	                	public void handle(MouseEvent event) {
		            	                		Alert a = new Alert(AlertType.INFORMATION); 
		            	    	            	a.setContentText("Loan Selection Complete. You can now exit.");
		            	    	                a.show(); 
		            	    	                String msg1 = msg + ",3";
		            	    	                su.sendMessage(msg1);
		            	    	                su.closeSocket();
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
		            	                
		            	                // Clearing all text fields
		            	                nameTF.clear();
		            	                addressTF.clear();
		            	                cityTF.clear();
		            	                stateCombo.getSelectionModel().clearSelection();
		            	                zipTF.clear();
		            	                phoneTF.clear();
		            	                carLoan.setSelected(false);
		            	                homeLoan.setSelected(false);
		            	                PropertyTypeM.getSelectionModel().clearSelection();
		            	                PropertyType.setVisible(false);
		            	                PropertyType.setVisible(false);
		            	                amountTF.clear();
		            	                accountHolderTF.clear();
		            	                bankNameTF.clear();
		            	                accountTypeSelect.getSelectionModel().clearSelection();
		            	                routingNumberTF.clear();
		            	                accountNumberTF.clear();
		            	                downPaymentTF.clear();
		            	                
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
			exit.setTooltip(new Tooltip("Click to exit."));
	        exit.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	Alert a = new Alert(AlertType.CONFIRMATION); 
	            	a.setTitle("Exit Confirmation Dialog");
	            	a.setHeaderText("Are you sure you want to exit?");
	                Optional<ButtonType> option = a.showAndWait();

	                if (option.get() == ButtonType.OK) {
	                	primaryStage.close();
	                }	
	            	
	            }
	        });
	        exit.setStyle("-fx-font: 18 Corbel; -fx-background-radius: 20px; -fx-background-color: #99e1d9;");
	        
	        Button clear = new Button("Clear");
	        clear.setTooltip(new Tooltip("Click to exit."));
	        clear.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
	            	Alert a = new Alert(AlertType.CONFIRMATION); 
	            	a.setTitle("Clearing Input Confirmation Dialog");
	            	a.setHeaderText("Are you sure you want to clear your input data?");
	                Optional<ButtonType> option = a.showAndWait();

	                if (option.get() == ButtonType.OK) {
	                	// TODO : CLEAR EVERYTHING
	                	nameTF.clear();
	                	addressTF.clear(); 
	                	cityTF.clear();
	                	stateCombo.setValue(null);
	                	zipTF.clear();
	                	phoneTF.clear();
	                	homeLoan.setSelected(false);
	                	carLoan.setSelected(false);
	                	PropertyTypeM.getSelectionModel().clearSelection();
	                	PropertyTypeM.setVisible(false);
	                	PropertyType.setVisible(false);
	                	amountTF.clear();
	                	accountHolderTF.clear();
	                	bankNameTF.clear(); 
	                	accountTypeSelect.setValue(null);
	                	routingNumberTF.clear(); 
	                	accountNumberTF.clear();
	                	downPaymentTF.clear();           	
	                }		
	            }
	        });
	        clear.setStyle("-fx-font: 18 Corbel; -fx-background-radius: 20px;  -fx-background-color: #99e1d9;");
	        // fceade
	        
	        VBox root = new VBox();
			VBox pane001 = new VBox();
			VBox paneHelp = new VBox();
			VBox pane0 = new VBox(); 
			GridPane pane1 = new GridPane(); // user info
			GridPane pane2 = new GridPane(); // buttons
			pane2.setPrefWidth(80);
			
			pane001.getChildren().add(clock);
			paneHelp.getChildren().add(help);
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
			
			pane2.add(submit, 0, 1);
			pane2.add(exit, 1, 1);
			pane2.add(clear, 2, 1);
			
			pane1.setAlignment(Pos.CENTER);
			pane2.setAlignment(Pos.CENTER);
					
			pane0.setPadding(new Insets(10, 0, 50, 0));
			paneHelp.setPadding(new Insets(10, 0, 50, 20));
			pane1.setVgap(25);
			pane2.setPadding(new Insets(50, 0, 20, 0));
			pane2.setHgap(160);

			submit.setMinWidth(pane2.getPrefWidth());
			exit.setMinWidth(pane2.getPrefWidth());
			clear.setMinWidth(pane2.getPrefWidth());
				
			// Root
			root.getChildren().addAll(pane001, paneHelp, pane0, pane1, pane2);
			
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setFitToWidth(true);
			scrollPane.getStylesheets().add("style.css");
						
			root.minWidth(scrollPane.getMinViewportWidth());
			root.setAlignment(Pos.CENTER);
			
			scrollPane.setContent(root);
			root.setStyle("-fx-background-color: white");
			// #d8e2dc
			
			refreshClock();
		    
			Scene scene = new Scene(scrollPane, 800, 750);
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
