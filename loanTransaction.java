package sss5;

import java.util.Vector;

public class loanTransaction 
{
	String keyNum; 
	String name; 
	String address; 
	String cityName;
	String stateName;
	String zipCode; 
	String phoneNumber;
	String typeOfLoan; 
	String propertyType; 
	long amountDesired;
	String nameOfAccountHolder;
	String nameOfBank;
	String typeOfAccount;
	long routingNumber; 
	long accountNumber;
	String creditScore; 
	long downPayment;
	double totalDollars;
	int loanOpt;
	String loanOption;
	
	Vector<String> transactionData = new Vector<String>(); 

	public loanTransaction (String key, String n, String a, String city, String state, String zip, String phoneNum, String loanType, 
			String property, long amount, String accountHolder, String bankName, String accountType, long routingNum, long accountNum, 
			String credit, long payment, int option)
	{
		keyNum = key;			// 0
		name = n;				// 1
		address = a;			// 2
		cityName = city;		// 3
		stateName = state;		// 4
		zipCode = zip;			// 5
		phoneNumber = phoneNum;	// 6
		typeOfLoan = loanType;	// 7
		propertyType = property; // 8
		amountDesired = amount; 	// 9
		nameOfAccountHolder = accountHolder; // 10 
		nameOfBank = bankName;	// 11
		typeOfAccount = accountType;	// 12
		routingNumber = routingNum; 	// 13
		accountNumber = accountNum;	// 14
		creditScore = credit;	// 15
		downPayment = payment;	// 16
		loanOpt = option; // 17
		
		if (loanOpt == 1)
		{
			loanOption = "LightStream";
		}
		else if (loanOpt == 2)
		{
			loanOption = "Payoff";
		}
		else if (loanOpt == 3)
		{
			loanOption = "Freedom Plus";
		}
		
		transactionData.add(toString()); 		
	}

	public String toString()
	{
		return (keyNum + " = " + "Name: " + name + ", " + "Location: " + address + " " + cityName + ", " + stateName + " " + zipCode + 
				", Phone Number: " + phoneNumber + ", Type of Loan: " + typeOfLoan + ", Property Type: " + propertyType + ", Amount Desired: " 
				+ amountDesired + ", Name of Account Holder: " + nameOfAccountHolder + ", Bank: " + nameOfBank + ", Account Type: " + typeOfAccount + ", Routing Number: " + routingNumber + 
				", Account Number: " + accountNumber + ", Credit Score: " + creditScore + ", Down Payment: " + downPayment + ", Loan Option: " + loanOption);
	}
}
	
