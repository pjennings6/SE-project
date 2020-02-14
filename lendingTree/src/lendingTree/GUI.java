package lendingTree;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;


public class GUI extends Application 
{

	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{		
			Label homeLoanL = new Label("Home");
			Label carLoanL = new Label("Car");
			RadioButton homeLoan = new RadioButton();
			RadioButton carLoan = new RadioButton();
			
			VBox root = new VBox();
			
			HBox pane1 = new HBox();
						
			pane1.getChildren().add(homeLoanL);
			pane1.getChildren().add(homeLoan);
			pane1.getChildren().add(carLoanL);
			pane1.getChildren().add(carLoan);
						
			root.getChildren().addAll(pane1);
			
			Scene scene = new Scene(root,500,500);
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
