package lendingTree;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
	        clock.setPrefWidth(900);
			
			Image pic = new Image("/resources/lending_tree_logo.png");
	        ImageView imageOK = new ImageView(pic);
	        imageOK.setFitWidth(400);
	        imageOK.setPreserveRatio(true);
	        
	        Button save = new Button("Save");
	        
			String properties[] = {"Single Family Home", "Multi Family Home", "Townhome", "Condominium", "Mobile Home"};
			
			// Type of Loan Radio Buttons
			ToggleGroup group = new ToggleGroup();
			
			Label typeOfLoan = new Label("Type of Loan: ");
			
			RadioButton homeLoan = new RadioButton("Home");
			RadioButton carLoan = new RadioButton("Car");
			
			typeOfLoan.setPrefWidth(215);
			typeOfLoan.setMaxWidth(215);			
			homeLoan.setPrefWidth(80);
			homeLoan.setMaxWidth(80);
			carLoan.setPrefWidth(80);
			carLoan.setMaxWidth(80);
			homeLoan.setToggleGroup(group);
			carLoan.setToggleGroup(group);			
			carLoan.setTranslateX(100);
			
			// Type of property (if auto loan) 
			Label PropertyType = new Label("Type of property: ");
			PropertyType.setVisible(false);
			ComboBox<String> PropertyTypeM = new ComboBox<String>(FXCollections.observableArrayList(properties));
			PropertyTypeM.setVisible(false);
			
		
			
			// loan amount desired 
			Label amount = new Label("Amount Desired: ");
			TextField amountTF = new TextField();
			
			// bank account info
			Label accountHolder = new Label("Name of account holder: ");
			Label bankName = new Label("Banking Institution: ");
			Label accountType = new Label("Account Type (checking/savings): ");
			Label routingNumber = new Label("Routing Number:");
			Label accountNumber = new Label("Account Number:");
			
			TextField accountHolderTF = new TextField();
			TextField bankNameTF = new TextField();
			TextField accountTypeTF = new TextField();
			TextField routingNumberTF = new TextField();
			TextField accountNumberTF = new TextField();
			
					
			// credit score 
			Label creditScore = new Label("Credit Score: ");
			Slider creditScoreS = new Slider(300, 850, 550);
			Label CSValue = new Label(Double.toString(creditScoreS.getValue()));
			
			
			Label downPayment = new Label("Down Payment: ");
			TextField downPaymentTF = new TextField();
			
			// user info
			Label name = new Label("Name: ");
			Label address = new Label("Address: ");
			Label city = new Label("City: ");
			Label state = new Label("State: ");
			Label zip = new Label("Postal Code:" );
			Label phone = new Label("Phone number:");
			
			TextField nameTF = new TextField();
			TextField addressTF = new TextField();
			TextField cityTF = new TextField();
			TextField stateTF = new TextField();
			TextField zipTF = new TextField();
			TextField phoneTF = new TextField();
			
			nameTF.setPrefWidth(300);
			nameTF.setMaxWidth(300);						
			addressTF.setPrefWidth(300);
			addressTF.setMaxWidth(300);						
			cityTF.setPrefWidth(300);
			cityTF.setMaxWidth(300);						
			stateTF.setPrefWidth(300);
			stateTF.setMaxWidth(300);						
			zipTF.setPrefWidth(300);
			zipTF.setMaxWidth(300);						
			phoneTF.setPrefWidth(300);
			phoneTF.setMaxWidth(300);
			
			VBox root = new VBox();
			VBox pane001 = new VBox();
			VBox pane0 = new VBox(10); 
			GridPane pane1 = new GridPane(); // user info
			
			pane001.getChildren().add(clock);
			
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
			pane1.add(save, 2, 16);
			pane1.setAlignment(Pos.CENTER);
					
			pane0.setPadding(new Insets(10, 0, 50, 0));
			pane1.setVgap(25);
			pane1.setHgap(15);
			
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
			
	        save.setOnAction(new EventHandler<ActionEvent>()
	        {
	            @Override public void handle(ActionEvent e)
	            {
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
	            }
	        });
	        
	        creditScoreS.valueProperty().addListener(new ChangeListener<Number>() 
			{
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
				{
					CSValue.setText(String.format("%.0f", new_val));
		        }
		    });			
						
			root.getChildren().addAll(pane001, pane0, pane1);
						
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setFitToWidth(true);
						
			root.minWidth(scrollPane.getMinViewportWidth());
			root.setAlignment(Pos.CENTER);
			scrollPane.setContent(root);
			
			refreshClock();
			
			BackgroundImage myBI= new BackgroundImage(new Image("resources/background.jpg",800,1250,false,true),
			        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
			          BackgroundSize.DEFAULT);
			root.setBackground(new Background(myBI));
					
			Scene scene = new Scene(scrollPane,800,750);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}

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
					   sleep(1000L);
				    }
				    catch (InterruptedException e) 
				    {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
				    }
				  
	            }  // end while ( true )
				 
		    } // end run thread
		 };

	     refreshClock.start();
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
