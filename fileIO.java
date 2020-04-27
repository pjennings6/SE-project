package sss5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fileIO 
{
		public void wrTransactionData(String dataStr)
		{
	        FileWriter fwg1 = null;
	        FileWriter fwg2 = null; 
	        try 
	        {
	        	// open the file in append write mode
	        	fwg1 = new FileWriter("hashTableData.txt", true);
	        	fwg2 = new FileWriter("lendingTreeTransactionLog.txt", true);
	        }
	        catch (IOException e)
	        {
	        	e.printStackTrace();
	        }
	   	    
	        BufferedWriter bwg1 = new BufferedWriter(fwg1);
	        BufferedWriter bwg2 = new BufferedWriter(fwg2);
	        
	        PrintWriter outg1   = new PrintWriter(bwg1);
	        PrintWriter outg2   = new PrintWriter(bwg2);
			
	        String timeStamp = new SimpleDateFormat("MM-dd-yyyy HH.mm.ss").format(new Date());
	        
	        outg2.println(timeStamp + " : " + dataStr);
	        
	        if (dataStr.contains("Transaction>"))
	        {
	        	String tempStr = dataStr.replace("Transaction>", "");
	        	outg1.println(tempStr);
	        }
	        
	        outg1.close();
	        
	        outg2.close();
	        
	        
	        
		}

}