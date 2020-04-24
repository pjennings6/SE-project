package lendingtree;

public class loanTransaction 
{
	String name;
//	String userName;
	String typeOfLoan; 
	int amountDesired;
	int downPayment;
	dpackage sss5;

public class loanTransaction 
{
	String name;
	String cityName;
	String stateName;
	String zipCode; 
	String phoneNumber;
	String typeOfLoan; 
	String propertyType; 
	long amountDesired;
	String nameOfBank;
	String typeOfAccount;
	long accountNumber;
	long downPayment;
	double totalDollars;

	public loanTransaction (String n, String city, String state, String zip, String phoneNum, String loanType, String property, long amount, String bankName, String accountType, long accountNum, long payment)
	{
		name = n;
		cityName = city;
		stateName = state;
		zipCode = zip;
		phoneNumber = phoneNum;
		typeOfLoan = loanType;
		propertyType = property; 
		amountDesired = amount;
		nameOfBank = bankName;
		typeOfAccount = accountType; 
		accountNumber = accountNum;
		downPayment = payment;
	}

	public String toString()
	{
		return name + " : " + "Type of Loan = " + typeOfLoan + " Amount Desired = " + amountDesired + " Down Payment = " + downPayment;
	}
	
}
ouble totalDollars;

	public loanTransaction (String n, String loanType, String amount, String payment)
	{
		name = n;
//		userName = name;
		typeOfLoan = loanType;
		amountDesired     = Integer.parseInt(amount);
		downPayment     = Integer.parseInt(payment);
	}

	public String toString()
	{
		return name + " : " + "Type of Loan = " + typeOfLoan + " Amount Desired = " + amountDesired + " Down Payment = " + downPayment;
	}
	
}
