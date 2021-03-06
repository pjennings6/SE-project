package sss5;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class sss5 extends Application 
{
	public static TextArea textArea;
	public static TextArea textArea_1;
	public static TextArea textArea_2;
	public static TextArea textArea_3;
	TextArea clock;
	
	@Override
	public void start (Stage stage) throws FileNotFoundException
	{
		InetAddress ipAddress = null;
		try
		{
			ipAddress = InetAddress.getLocalHost();
		}
		catch (UnknownHostException el)
		{
			el.printStackTrace();
		}
		
		stage.setTitle("Simple Socket Server JAVA FX Rev 5.0   :   Rel Date April 23, 2020         " + 
	    "IP : " + ipAddress.getHostAddress() + "     Port# : 3333");
		stage.setWidth(1400);
		stage.setHeight(800);
		
		// text area for real time clock thread to display
		clock = new TextArea();
		clock.setEditable(false);
		clock.setPrefHeight(30);
		clock.setPrefWidth(900);
		clock.setFont(new Font(16));
		clock.setStyle("-fx-text-fill: green");
		
		textArea_1 = new TextArea();
		textArea_1.setEditable(false);
		textArea_1.setPrefHeight(80);
		textArea_1.setPrefWidth(300);
		textArea_1.setFont(new Font(24));
		textArea_1.setText("Client Connections\n");		
		textArea_1.setStyle("-fx-text-fill: green");
		
		textArea = new TextArea();
		textArea.setFont(Font.font("Verdana", 18));
		textArea.setEditable(false);
		textArea.setPrefHeight(80);
		textArea.setPrefWidth(300);
		textArea.setFont(new Font(24));
		textArea.setText("Transactions\n");
		textArea.setStyle("-fx-text-fill: green");			
				
		textArea_3 = new TextArea();
		textArea_3.setEditable(false);
		textArea_3.setPrefHeight(80);
		textArea_3.setPrefWidth(300);
		textArea_3.setFont(new Font(24));
		textArea_3.setText("Records:");
		textArea_3.setStyle("-fx-text-fill: green");	
		
		textArea_2 = new TextArea();
		textArea_2.setEditable(false);
		textArea_2.setPrefHeight(80);
		textArea_2.setPrefWidth(900);
		textArea_2.setFont(new Font(18));
		textArea_2.setText("Number of Loan Transactions Completed");
		textArea_2.setStyle("-fx-text-fill: green");	
		
		// define all BUTTONS
		Button exit = new Button("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
            	Alert a = new Alert(AlertType.CONFIRMATION); 
            	a.setTitle("Exit Confirmation Dialog");
            	a.setHeaderText("Are you sure you want to exit?");
                Optional<ButtonType> option = a.showAndWait();

                if (option.get() == ButtonType.OK) {
                	stage.close();
                }	
            }
        });
			
		Button delete = new Button("Delete");
		
		Button clients = new Button("Clients");
		
		Button summary = new Button("Summary");
		
		Button help = new Button("Help");
		help.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
            	Alert a = new Alert(AlertType.INFORMATION); 
            	a.setTitle("Help");
            	a.setContentText("Click exit to exit the socket server.");
                a.show();
            }
        });
		
		// all buttons go to horizontal view
		HBox hb = new HBox();
		hb.setPadding(new Insets(15, 12, 15, 12));
	    hb.setSpacing(120);
		hb.getChildren().addAll(exit, delete, clients, summary, help);
		
		
		// vertical has IP text area and buttons below
		VBox vb = new VBox();
		vb.getChildren().addAll(textArea_2, hb);
		
		// main BORDER PANE pane layout
		BorderPane bp = new BorderPane();
		bp.setTop(clock);
		bp.setLeft(textArea_1);
		bp.setCenter(textArea);
		bp.setRight(textArea_3);
		bp.setBottom(vb);
		
		// start all threads  for the GUI screen here
		startRealTimeClock();
	
		// lights, camera, action
		Scene scene = new Scene(bp);
		stage.setScene(scene);
		stage.show();
		
		startSockServer();
	}
	
	private void startSockServer()
	  {	
		 Thread refreshWeatherThread = new Thread()
		 {
		    public void run()
			  { 	
				  sockServer.runSockServer();
		      }
		 };

	    refreshWeatherThread.start();
	  }
  
  /*
   * Thread to update time    
   */     
  private void startRealTimeClock()
  {	
	   Thread refreshClock = new Thread()
	   {
		  public void run()
		  {   
			 clock.setFont(Font.font("Verdana", 14));
			 
			 while (true)
			 {	 			      
				   Date date = new Date();
				   String str = String.format("    %tc", date);
					 
				   clock.setText("");
				   clock.setText(str);
				   
			    	try
				    {
					   sleep(5000L);
				    }
				    catch (InterruptedException e)
				   {
					  e.printStackTrace();
				   }
             } // end while true 
	     }
	  };

    refreshClock.start();
   }
	
   // main function starts here
   public static void main(String[] args)
   {
		launch(args);
   }
}
