package sss5;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class sockServer implements Runnable
{
	Socket csocket;
	String ipString;
	char threadType;

	static Vector<String> vec = new Vector<String>(5);
	   
	public static Hashtable<String, loanTransaction> clients = new Hashtable<String, loanTransaction>();
	   
	static final String newline = "\n";
	static int first_time = 1;
	   
	static int port_num = 3333;
		   
	static int numOfConnections = 0;
	static int numOfMessages = 0;
	static int max_connections = 5;
	static int numOfTransactions = 0;
	static int numOfHomeLoans = 0;
	static int numOfAutoLoans = 0;
	
	sockServer(Socket csocket, String ip)
	{
		this.csocket  = csocket;
		this.ipString = ip;
	} 

	public static void runSockServer()   // throws Exception
	{
		boolean sessionDone = false;
	  
	    ServerSocket ssock = null;
	   
	    try
	    {
	    	ssock = new ServerSocket(port_num);
	    }
	    catch (BindException e)
	    {
	    	e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	 
	    // update the status text area to show progress of program
	    try 
	    {
	    	InetAddress ipAddress = InetAddress.getLocalHost();
		    sss5.textArea.appendText("IP Address : " + ipAddress.getHostAddress() + newline);
	    }
	    catch (UnknownHostException e1)
	    {
	    	e1.printStackTrace();
	    }
	 
	    sss5.textArea.appendText("Listening on port " + port_num + newline);
	 
	    // initialize the hash table to the following keys 
//	    try
//        {
//	    	File f = new File("hashTableData.txt");
//     	    if (f.exists())
//     	    {
//     	    	FileReader reader = new FileReader("hashTableData.txt");
//                BufferedReader br = new BufferedReader(reader);
//               
//                String line = br.readLine();
//                while (line != null)
//                {
//                	String args[]   = line.split("\\,");
//
//				    String name = args[0];
//					String cityName = args[1];
//					String stateName = args[2];
//					String zipCode = args[3];
//					String phoneNumber = args[4];
//					String typeOfLoan = args[5];
//					String propertyType = args[6]; 
//					long amountDesired = Long.parseLong(args[7]);
//					String nameOfBank = args[8];
//					String typeOfAccount = args[9]; 
//					long accountNumber = Long.parseLong(args[10]);
//					long downPayment = Long.parseLong(args[11]);
//               	
// 					clients.put(name, new loanTransaction(name,cityName,stateName,zipCode,phoneNumber,typeOfLoan,propertyType,amountDesired,nameOfBank,typeOfAccount,accountNumber,downPayment));
// 					line = br.readLine();
//                }
//                 
//                br.close();
//                
//                 //
//                 // add homemade key to HASHTABLE data structure
//                 //
//                 int    transCount=0, ticketCount=0;
//                 double dollarCount=0.0;
//                 
//                 List<String> v = new ArrayList<String>(clients.keySet());
//         	     for (String key : v)
//        	     {
//        	        transCount  = transCount  + clients.get(key).getTransactions();
//        	        ticketCount = ticketCount + clients.get(key).getTickets();
//        	        dollarCount = dollarCount + clients.get(key).getDollars();
//        	     }        
//         	     
//         	    clients.put("totalKiosk", new loanTransactions("totalKiosk",transCount,ticketCount,dollarCount));   
//     	      }
//     	 }
//	    
	     
	    sessionDone = false;
	    while (sessionDone == false)
	    {
		    Socket sock = null;
			try
			{
				// blocking system call
				sock = ssock.accept();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			// update the status text area to show progress of program
	        sss5.textArea.appendText("Client Connected : " + sock.getInetAddress() + newline);
	        new Thread(new sockServer(sock, sock.getInetAddress().toString())).start();
	    }
	    try 
	    {
	    	ssock.close();
	    }
	    catch (IOException e) 
	    {
	    	e.printStackTrace();
	    }
	}	  
	
	// get all transaction data from the hash table keys
//	public static String getAllTransactions()
//	{
//		String rs="";
//		
//		List<String> v = new ArrayList<String>(clients.keySet());
//	    Collections.sort(v);
//		
//	    for (String str : v) 
//	    	rs = rs + clients.get(str.toString()) + "\r\n";
//				
//		return rs;
//	}

	
	
	// this is the thread code that ALL clients will run()
	public void run()
	{
		try
		{
			boolean session_done = false; 
		    long threadId;
		    String clientString;
		    String keyString = "";
		    
		    threadId = Thread.currentThread().getId();
		    numOfConnections++;
	      
		    sss5.textArea.appendText("Num of Connections = " + numOfConnections + newline);
	      
		    keyString = ipString + ":" + threadId;
	      
		    if (vec.contains(keyString) == false)
		    {
		    	int counter = 0;
	        	vec.addElement(keyString);
	        	
//	        	sss5.textArea_1.setText("");
	        	Enumeration<String> en = vec.elements();
	        	while (en.hasMoreElements())
	        	{
        			sss5.textArea_1.appendText(en.nextElement() + "\n");
	        		
	        		if (++counter >= 6)
	        		{
	        			sss5.textArea_1.appendText("\r\n");
	        			counter = 0;
	        		}
	        	}
		    }
	       
		    PrintStream pstream = new PrintStream (csocket.getOutputStream());
		    BufferedReader rstream = new BufferedReader(new InputStreamReader(csocket.getInputStream()));
	       
		    while (session_done == false)
		    {
		    	if (rstream.ready())   // check for any data messages
		    	{
		    		clientString = rstream.readLine();

		    		// write to transaction log
		    		fileIO transLog = new fileIO();
		    		transLog.wrTransactionData(clientString);
	                         
		    		// update the status text area to show progress of program
	   	           	sss5.textArea.appendText("RECV : " + clientString + newline);
	     	       
	   	           	// update the status text area to show progress of program
	   	           	sss5.textArea.appendText("RLEN : " + clientString.length() + newline);
	              
	   	           	if (clientString.length() > 128)
	   	           	{
	   	           		session_done = true;
	   	           		continue;
	   	           	}

	   	           	if (clientString.contains("quit"))
	   	           	{
	   	           		session_done = true;
	   	           	}
	   	           	else if (clientString.contains("QUIT"))
	   	           	{
	   	           		session_done = true;
	   	           	}
	   	           	else if (clientString.contains("Quit"))
	   	           	{
	   	           		session_done = true;
	   	           	}
	   	           	else if (clientString.contains("Transaction>"))
	   	           	{ 
	   	           		numOfTransactions = numOfTransactions + 1;
	   	           		String tokens[] = clientString.split("\\>");
	   	           		String args[]   = tokens[1].split("\\,");
	   	           		String newClientString; 
	   	           		newClientString = args[0];
	   	           		
		   	           	long amount = Long.parseLong(args[7]);
		            	long accountNum = Long.parseLong(args[10]);
		            	long payment = Long.parseLong(args[11]);
		   	           		
	   	           		clients.put(newClientString, new loanTransaction(newClientString, args[1], args[2], args[3], args[4], args[5], args[6], amount, args[8], args[9], accountNum, payment));
	   	           		String loanType = String.valueOf(args[5]); 
	   	           		if (loanType.equals("Home"))
	   	           		{
	   	           			System.out.print("HOME\n");
	   	           			numOfHomeLoans = numOfHomeLoans + 1;
	   	           		}
	   	           		else if (loanType.equals("Car"))
	   	           		{
	   	           			System.out.print("CAR\n");
	   	           			numOfAutoLoans = numOfAutoLoans + 1; 
	   	           		}
	   	           		sss5.textArea_2.setText("");
	   	           		sss5.textArea_2.appendText("Num of Loan Transactions Completed: " + numOfTransactions);
	   	           		
	   	           		sss5.textArea_3.setText("");
		   	           	sss5.textArea_3.setText("RecordsL\n");
	   	           		sss5.textArea_3.appendText("Home Loans: " + numOfHomeLoans +"\n" + "Auto Loans: " + numOfAutoLoans +"\n");
   	           		
	   	           		
	   	           		pstream.println("ACK");
	   	           	}

	   	           	else if (clientString.contains("Date>"))
	   	           	{
	   	           		numOfMessages++;
	            	  
		            	// Create an instance of SimpleDateFormat used for formatting 
		            	// the string representation of date (month/day/year)
		            	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		            	// Get the date today using Calendar object.
		            	Date today = Calendar.getInstance().getTime();
	            	
		            	// Using DateFormat format method we can create a string 
		            	// representation of a date with the defined format.
		            	String reportDate = df.format(today);

		            	// Print what date is today!
		            	pstream.println("Num Of Messages : " + numOfMessages + "   Simple Date: " + reportDate);
	   	           	}
	   	           	else
	   	           	{
	   	           		pstream.println("NACK : ERROR : No such command!");
	   	           	}	
		    	}
		    	
		    	Thread.sleep(500);
		    } // end while loop
	
            keyString = ipString + ":" + threadId;
	      
	        if (vec.contains(keyString) == true)
	        {
	        	int counter = 0;
	        	vec.removeElement(keyString);
	        	
	        	sss5.textArea_1.setText("");
	        	Enumeration<String> en = vec.elements();
	        	while (en.hasMoreElements())
	        	{        		     		
	        		sss5.textArea_1.appendText(en.nextElement() + " || ");
	        		
	        		if (++counter >= 6)
	        		{
	        			sss5.textArea_1.appendText("\r\n");
	        			counter = 0;
	        		}
	        	}

  	            // sss5.textArea_1.repaint();
	        }
	      
	        numOfConnections--;

	        // close client socket
	        csocket.close();
	       
	        // update the status text area to show progress of program
	        sss5.textArea.appendText("Child Thread: " + threadId + " is exiting" + newline);
		} // end try  
		
		catch (SocketException e)
	    {
			// update the status text area to show progress of program
	    	sss5.textArea.appendText("ERROR : Socket Exception!" + newline);
	    }
	    catch (InterruptedException e)
	    {
	    	// update the status text area to show progress of program
	    	sss5.textArea.appendText("ERROR : Interrupted Exception!" + newline);
	    }
	    catch (UnknownHostException e)
	    {
	    	// update the status text area to show progress of program
	    	sss5.textArea.appendText("ERROR : Unknown Host Exception" + newline);
	    }
	    catch (IOException e) 
	    {
	    	// update the status text area to show progress of program
	    	sss5.textArea.appendText("ERROR : IO Exception!" + newline);
	    }     
	    catch (Exception e)
	    { 
	    	numOfConnections--;
	    	// update the status text area to show progress of program
	    	sss5.textArea.appendText("ERROR : Generic Exception!" + newline);
	    }
	}  // end run() thread method
}
