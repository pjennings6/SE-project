package sss5;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
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

	static Vector<String> vec = new Vector<String>(5); // holds clients
	static Vector<Vector<String>> vDateTransactionData = new Vector<Vector<String>>(); // vector of vectors 
	static Vector<Vector<String>> autoLoanTransactions = new Vector<Vector<String>>(); // only holds auto loan transactions
	static Vector<Vector<String>> homeLoanTransactions = new Vector<Vector<String>>(); // only holds home loan transactions 
	
	public static Hashtable<String, loanTransaction> clients = new Hashtable<String, loanTransaction>(); // holds all transactions
	public static Hashtable<String, String> hashDateTransactionData = new Hashtable<String, String>(); // organized by date
	public static Hashtable<String, Long> mostFreqState = new Hashtable<String, Long>(); // holds the state occurrence frequency 
	
	static final String newline = "\n";
	
	static int port_num = 3333;
		   
	static int numOfConnections = 0;
	static int numOfMessages = 0;
	static int max_connections = 5;
	static int numOfRecords = 0;
	static int numOfTransactions = 0;
	static int numOfHomeLoans = 0;
	static int numOfAutoLoans = 0;
	static long totalLoanAmount = 0;
	static String maxState = "";
	static int numOfOption1 = 0;
	static int numOfOption2 = 0;
	static int numOfOption3 = 0;
	static String maxOption = "";
	
	
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
	  
	    try
        {
	    	File f = new File("hashTableData.txt");
     	    if (f.exists())
     	    {
     	    	System.out.print("FOUND!\n");
     	    	FileReader reader = new FileReader("hashTableData.txt");
                BufferedReader br = new BufferedReader(reader);      		
        		
                String line = br.readLine();
                while (line != null)
                {
                	System.out.print("READING!\n");
                	String args[]   = line.split("\\,");
            		String keyNum = args[0];
				    String name = args[1];
				    String address = args[2];
					String cityName = args[3];
					String stateName = args[4];
					String zipCode = args[5];
					String phoneNumber = args[6];
					String typeOfLoan = args[7];
					String propertyType = args[8];
					long amountDesired = Long.parseLong(args[9]);
					String accountHolder = args[10];
					String nameOfBank = args[11];
					String typeOfAccount = args[12];
					long routingNumber = Long.parseLong(args[13]);
					long accountNumber = Long.parseLong(args[14]);
					String creditScore = args[15];
					long downPayment = Long.parseLong(args[16]);
					int loanOption = Integer.parseInt(args[17]);
					String date = args[18];
					
					// adding to clients hashtable 
 					clients.put(keyNum, new loanTransaction(keyNum,name,address,cityName,stateName,zipCode,phoneNumber,typeOfLoan,propertyType,
 							amountDesired,accountHolder,nameOfBank,typeOfAccount,routingNumber,accountNumber,creditScore,downPayment,loanOption));
 					
 					// updating important tracker info
 					numOfRecords++;
 					totalLoanAmount = totalLoanAmount + amountDesired; 
 		 
 					// updating vectors specific to each loan type
   	           		if (typeOfLoan.equals("Home"))
   	           		{
   	           			numOfHomeLoans++;
   	           			homeLoanTransactions.add(clients.get(keyNum).transactionData);
   	           		}
   	           		else if (typeOfLoan.equals("Car"))
   	           		{
   	           			numOfAutoLoans++; 
   	           			autoLoanTransactions.add(clients.get(keyNum).transactionData);
   	           		}
   	           		
   	           		// reading into hashDateTransactionData
   	           		if (hashDateTransactionData.get(date) != null)
   	           		{
   	           			vDateTransactionData.add(clients.get(keyNum).transactionData);
   	           			hashDateTransactionData.put(date, vDateTransactionData.toString());   	
   	           		}
   	           		else if (hashDateTransactionData.get(date) == null)
   	           		{
   	           			vDateTransactionData.removeAllElements();
   	           			vDateTransactionData.add(clients.get(keyNum).transactionData);
	           			hashDateTransactionData.put(date, vDateTransactionData.toString());	           			
   	           		}
   	           		
   	           		// updating the most frequent state
   	           		if (mostFreqState.get(stateName) != null)
   	           		{
   	           			mostFreqState.put(stateName, mostFreqState.get(stateName)+1);
   	           		}
   	           		else if (mostFreqState.get(stateName) == null)
   	           		{
   	           			mostFreqState.put(stateName, (long)1);
   	           		}
   	           		
   	           		// max loan option
   	           		maxOption = getMaxOption(); 
   	           		
   	           		line = br.readLine();
                }
                
                // getting the most frequent state
                getMostFreqState();
                
                System.out.print("READ\n");
                
                // updating the server text
                sss5.textArea_3.setText("");
   	           	sss5.textArea_3.setText("Tracker\nRecords: " + numOfRecords + "\n");
   	           	sss5.textArea_3.appendText("Home Loans: " + numOfHomeLoans +"\n" + "Auto Loans: " + numOfAutoLoans + "\nMost Freq. State: " +
   	           	maxState + "\nHottest Option: " + maxOption + "\n$ Loan Amount: $" + totalLoanAmount);   	           	
               
   	           	br.close();
                
     	    } 

        }
	    catch(Exception e2)
        {   
    	    e2.printStackTrace(); 
        }    
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
	        // start a NEW THREAD process
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
	public static String getAllTransactions()
	{
		String rs="";
		
		Vector<Long> v = new Vector<Long>();
		
		for (String key : clients.keySet())
		{
			v.add(Long.parseLong(key));
		}
		
	    Collections.sort(v);
		
	    for (Long str : v) 
	        rs = rs + clients.get(str.toString()) + "\r\n";
				
		return rs;
	}
	
	// organizes the transactions by date
	public static String getAllTransactionsByDate()
	{
		String rs="";
		
		List<String> v = new ArrayList<String>(hashDateTransactionData.keySet());
	    Collections.sort(v);
	    
	    for (String date : v)
	    {
	    	rs = rs + date + newline; 
	    	String temp = hashDateTransactionData.get(date).toString();
	    	String tempStr = temp.replace("[", "").replace("],", newline).replace("]", newline);
	    	rs = rs + tempStr;
	    }
			
		return rs;
	}
	
	// organizes the transaction by loan type: auto
	public static String getAllAutoLoanTransactions()
	{
		String rs="";
		rs = autoLoanTransactions.toString(); 
		String tempStr = rs.replace("[", "").replace("],", newline).replace("]", newline);
		return tempStr;
	}
	
	// organizes the transaction by loan type: home
	public static String getAllHomeLoanTransactions()
	{
		String rs="";
		rs = homeLoanTransactions.toString(); 
		String tempStr = rs.replace("[", "").replace("],", newline).replace("]", newline);
		return tempStr;
	}
	
	// getting all clients
	public static String getAllClients()
	{
		String rs = "";
		Enumeration<String> en = vec.elements();
		while (en.hasMoreElements())
    	{
			rs = rs + en.nextElement() + "\n";
    	}
		return rs;
	}
	
	// getting the most frequently occurring state
	public static void getMostFreqState()
	{
		Long max = Long.MIN_VALUE; 
        for (String key: mostFreqState.keySet())
        {
        	Long temp = mostFreqState.get(key);
        	if (temp > max)
        	{
        		max = temp; 
        		maxState = key; 
        	}
        }
	}
	
	// getting the most chosen loan option
	public static String getMaxOption()
	{
		if (numOfOption1 > numOfOption2 && numOfOption1 > numOfOption3) 
      	{
  			maxOption = "LightStream";
  		}
  		else if (numOfOption2 > numOfOption1 && numOfOption2 > numOfOption3) 
  		{
  			maxOption = "Payoff";
  		}
  		else
  		{
  			maxOption = "Freedom Plus";
  		}
		
		return maxOption;
	}
	
	public static int getNumOfTransactions()
	{
		return numOfRecords; 
	}
	
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
	        	
	        	sss5.textArea_1.setText("");
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
	   	           		 
	   	           		String tokens[] = clientString.split("\\>");
	   	           		String args[]   = tokens[1].split("\\,");
	   	           		String newClientString; // key
	   	           		newClientString = String.valueOf(numOfRecords);
	   	           		
		   	           	numOfTransactions++;
	   	           		numOfRecords++;
	   	           		
	   	           		String stateName = args[3];
		   	           	long amount = Long.parseLong(args[8]);
		   	           	long routingNum = Long.parseLong(args[12]);
		            	long accountNum = Long.parseLong(args[13]);
		            	long payment = Long.parseLong(args[15]);
		            	String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
		            	int option = Integer.parseInt(args[16]);
		            	
	   	           		clients.put(newClientString, new loanTransaction(newClientString,args[0],args[1],args[2],args[3],args[4],args[5],args[6], 
	   	           			args[7],amount,args[9],args[10],args[11],routingNum,accountNum,args[14],payment,option));
	   	           		
	   	           		totalLoanAmount = totalLoanAmount + amount;
	   	           		
		   	           	if (mostFreqState.get(stateName) != null)
	   	           		{
	   	           			mostFreqState.put(stateName, mostFreqState.get(stateName)+1);
	   	           		}
	   	           		else if (mostFreqState.get(stateName) == null)
	   	           		{
	   	           			mostFreqState.put(stateName, (long)1);
	   	           		}
   	           		
	   	           		getMostFreqState();
	   	           		maxOption = getMaxOption(); 
	   	           		
	   	           		String loanType = String.valueOf(args[6]); 
	   	           		if (loanType.equals("Home"))
	   	           		{
	   	           			numOfHomeLoans++;
	   	           			homeLoanTransactions.add(clients.get(newClientString).transactionData);
	   	           		}
	   	           		else if (loanType.equals("Car"))
	   	           		{
	   	           			numOfAutoLoans++; 
	   	           			autoLoanTransactions.add(clients.get(newClientString).transactionData);
	   	           		}
	   	           		
	   	           		if (hashDateTransactionData.get(date) != null)
	   	           		{
	   	           			String temp = hashDateTransactionData.get(date).toString(); 
	   	           			temp = temp + clients.get(newClientString).transactionData.toString(); 
	   	           			hashDateTransactionData.put(date, temp);
	   	           		}
	   	           		else if (hashDateTransactionData.get(date) == null)
	   	           		{
	   	           			hashDateTransactionData.put(date, clients.get(newClientString).transactionData.toString()); 
	   	           		}
	   	           		
	   	           		// finding the most frequently chosen option
		   	           	if (option == 1) {
	   	           			numOfOption1++;
	   	           		}
	   	           		else if (option == 2) {
	   	           			numOfOption2++;
	   	           		}
	   	           		else if (option == 3) {
	   	           			numOfOption3++;
	   	           		}

			   	        if (numOfOption1 > numOfOption2 && numOfOption1 > numOfOption3) 
			   	      	{
			   	  			maxOption = "LightStream";
			   	  		}
			   	  		else if (numOfOption2 > numOfOption1 && numOfOption2 > numOfOption3) 
			   	  		{
			   	  			maxOption = "Payoff";
			   	  		}
			   	  		else
			   	  		{
			   	  			maxOption = "Freedom Plus";
			   	  		} 
		   	           	
	   	           		// Updating server view
	   	           		sss5.textArea_2.setText("");
	   	           		sss5.textArea_2.appendText("Number of Loan Transactions Completed: " + numOfTransactions);
	   	           		
	   	           		sss5.textArea_3.setText("");
	   	           		sss5.textArea_3.setText("Tracker\nRecords: " + numOfRecords + "\n");
	   	           		sss5.textArea_3.appendText("Home Loans: " + numOfHomeLoans + "\n" + "Auto Loans: " + numOfAutoLoans + "\nMost Freq. State: " +
	   	           				maxState + "\nHottest Option: " + maxOption + "\n$ Loan Amount: $" + totalLoanAmount);
	   	           	
	   	           		pstream.println("ACK");
	   	           	}

	   	           	else if (clientString.contains("Date>"))
	   	           	{
	   	           		numOfMessages++;
	            	  
		            	// Create an instance of SimpleDateFormat used for formatting 
		            	// the string representation of date (month/day/year)
		            	DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

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

//  	             sss5.textArea_1.repaint();
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
