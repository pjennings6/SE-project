package sss5;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
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
		
		stage.setTitle("Lending Tree Socket Server JAVA FX Rev 5.0   :   Rel Date May 4, 2020         " + 
	    "IP : " + ipAddress.getHostAddress() + "     Port# : 3333");
		stage.setWidth(1400);
		stage.setHeight(800);
		
		// text area for real time clock thread to display
		clock = new TextArea();
		clock.setEditable(false);
		clock.setPrefHeight(30);
		clock.setPrefWidth(900);
		clock.setFont(Font.font("Gill Sans MT", 16));
		clock.setStyle("-text-area-background: transparent;-fx-border-color: #99e1d9;");
		
		textArea_1 = new TextArea();
		textArea_1.setEditable(false);
		textArea_1.setPrefHeight(80);
		textArea_1.setPrefWidth(300);
		textArea_1.setFont(new Font(20));
		textArea_1.setText("Client Connections\n");		
		textArea_1.setStyle("-fx-text-fill: black");
		
		textArea = new TextArea();
		textArea.setFont(Font.font("Gill Sans MT", 18));
		textArea.setEditable(false);
		textArea.setPrefHeight(80);
		textArea.setPrefWidth(300);
		textArea.setFont(new Font(20));
		textArea.setText("Transactions\n");
		textArea.setStyle("-fx-text-fill: black");			
				
		textArea_3 = new TextArea();
		textArea_3.setEditable(false);
		textArea_3.setPrefHeight(80);
		textArea_3.setPrefWidth(300);
		textArea_3.setFont(new Font(20));
		textArea_3.setText("Tracker\nRecords: 0\nHome Loans: 0\nAuto Loans: 0\n$Loan Amount: $0\nMost Freq. State: ");
		textArea_3.setStyle("-fx-text-fill: black");	
		
		textArea_2 = new TextArea();
		textArea_2.setEditable(false);
		textArea_2.setPrefHeight(80);
		textArea_2.setPrefWidth(900);
		textArea_2.setFont(new Font(18));
		textArea_2.setText("Number of Loan Transactions Completed: 0");
		textArea_2.setStyle("-fx-text-fill: black");	
		
		// define all BUTTONS
		Button exit = new Button("Exit");
		exit.setStyle("-fx-background-radius: 10px; -fx-background-color: #99e1d9;");
		exit.setTooltip(new Tooltip("Click to exit out of the socket server."));
		exit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
            	Alert a = new Alert(AlertType.CONFIRMATION); 
            	a.setTitle("Exit Confirmation Dialog");
            	a.setHeaderText("Are you sure you want to exit?");
                Optional<ButtonType> option = a.showAndWait();

                if (option.get() == ButtonType.OK) 
                {
                	stage.close();
                }	
            }
        });
		
		Button clients = new Button("Clients");
		clients.setStyle("-fx-background-radius: 10px;  -fx-background-color: #99e1d9;");
		clients.setTooltip(new Tooltip("Click to view the list of client connections."));
		clients.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
            	Platform.runLater(new Runnable() 
            	{
            		public void run() 
            		{	
		            	Alert a = new Alert(AlertType.INFORMATION); 
		            	a.setTitle("List of Clients");
		            	a.setHeaderText("Client Connections");
		            	
		            	TextArea area = new TextArea(sockServer.getAllClients());
				        area.setWrapText(true);
				        area.setEditable(false);
	
				        a.getDialogPane().setContent(area);
				        a.setResizable(true);
	
				        a.showAndWait();
            		}
            	});
                	
            }
        });
			
		Button transactions = new Button("Transactions");
		transactions.setStyle("-fx-background-radius: 10px;  -fx-background-color: #99e1d9;");
		transactions.setTooltip(new Tooltip("Click to view a complete list of transactions."));
		transactions.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				Platform.runLater(new Runnable() 
				{
				        public void run() 
				        {				          
				          Alert alert = new Alert(Alert.AlertType.INFORMATION);
				          alert.setTitle("Loan Transactions");
				          alert.setHeaderText("Total Number of Transactions");
		          
				          TextArea area = new TextArea(sockServer.getAllTransactions());
				          area.setWrapText(true);
				          area.setEditable(false);

				          alert.getDialogPane().setContent(area);
				          alert.setResizable(true);

				          alert.showAndWait();
				        }
				 });	
			}
		});
		
		Button transactionsByDate = new Button("Transactions by Date");
		transactionsByDate.setStyle("-fx-background-radius: 10px;  -fx-background-color: #99e1d9;");
		transactionsByDate.setTooltip(new Tooltip("Click to view a complete list of transactions ordered by date."));
		transactionsByDate.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				Platform.runLater(new Runnable() 
				 {
				        public void run() 
				        {				          
				          Alert alert = new Alert(Alert.AlertType.INFORMATION);
				          alert.setTitle("Loan Transactions By Date");
				          alert.setHeaderText("Transactions Ordered By Date");
		          
				          TextArea area = new TextArea(sockServer.getAllTransactionsByDate());
				          area.setWrapText(true);
				          area.setEditable(false);

				          alert.getDialogPane().setContent(area);
				          alert.setResizable(true);

				          alert.showAndWait();
				        }
				    });	
			}
		});
			
		Button autoTransactions = new Button("Auto");
		autoTransactions.setStyle("-fx-background-radius: 10px;  -fx-background-color: #99e1d9;");
		autoTransactions.setTooltip(new Tooltip("Click to view a complete list of auto loan transactions."));
		autoTransactions.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				Platform.runLater(new Runnable() 
				 {
				        public void run() 
				        {				          
				          Alert alert = new Alert(Alert.AlertType.INFORMATION);
				          alert.setTitle("List of Auto Loan Transactions");
				          alert.setHeaderText("Auto Loan Transactions");
		          
				          TextArea area = new TextArea(sockServer.getAllAutoLoanTransactions());
				          area.setWrapText(true);
				          area.setEditable(false);

				          alert.getDialogPane().setContent(area);
				          alert.setResizable(true);

				          alert.showAndWait();
				        }
				    });	
			}
		});
		
		Button homeTransactions = new Button("Home");
		homeTransactions.setStyle("-fx-background-radius: 10px;  -fx-background-color: #99e1d9;");
		homeTransactions.setTooltip(new Tooltip("Click to view a complete list of home loan transactions."));
		homeTransactions.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		 	public void handle(ActionEvent e)
		 	{
				Platform.runLater(new Runnable() 
				 {
				        public void run() 
				        {				          
				          Alert alert = new Alert(Alert.AlertType.INFORMATION);
				          alert.setTitle("List of Home Loan Transactions");
				          alert.setHeaderText("Home Loan Transactions");
		          
				          TextArea area = new TextArea(sockServer.getAllHomeLoanTransactions());
				          area.setWrapText(true);
				          area.setEditable(false);

				          alert.getDialogPane().setContent(area);
				          alert.setResizable(true);

				          alert.showAndWait();
				        }
				    });	
			}
		});
		
		Button help = new Button("Help");
		help.setStyle("-fx-background-radius: 10px;  -fx-background-color: #99e1d9;");
		help.setTooltip(new Tooltip("Click to see help information."));
		help.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override public void handle(ActionEvent e)
            {
            	Alert a = new Alert(AlertType.INFORMATION); 
            	a.setTitle("Help Message");
            	a.setHeaderText("Help");
            	a.setContentText("Welcome to the server!\nClick Exit to exit the socket server.\nClick Clients to view a list of client connections.\nClick Transactions to view a list of transactions.\nClick Transactions by Date to view the list of transactions ordered by date.\nClick Home to view the list of home loan transactions.\nClick Auto to view the list of auto loans.");
                a.show();
            }
        });
		
		// all buttons go to horizontal view
		HBox hb = new HBox();
		hb.setPadding(new Insets(15, 12, 15, 12));
	    hb.setSpacing(120);
		hb.getChildren().addAll(exit, clients, transactions, transactionsByDate, homeTransactions, autoTransactions, help);
		
		
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
  
	// Thread to update time     
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
