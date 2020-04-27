package sss5;

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
		return (name + " = " + "Location: " + cityName + ", " + stateName + " " + zipCode + ", Phone Number: " + phoneNumber + 
				", Type of Loan: " + typeOfLoan + ", Property Type: " + propertyType + ", Amount Desired: " + amountDesired + 
				", Bank: " + nameOfBank + ", Account Type: " + accountNumber + ", Down Payment: " + downPayment);
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getCity()
	{
		return cityName;
	}
	
	public String getState()
	{
		return stateName;
	}
	
	public String getZip()
	{
		return zipCode;
	}
	
	public String getPhoneNum()
	{
		return phoneNumber;
	}
	
	public String getLoanType()
	{
		return typeOfLoan;
	}
	
	public String getProperty()
	{
		return propertyType;
	}
	
	public long getAmount()
	{
		return amountDesired;
	}
	
	public String getBankName()
	{
		return nameOfBank;
	}
	
	public String getAccountType()
	{
		return typeOfAccount;
	}
	
	public long getAccountNum()
	{
		return accountNumber;
	}
	
	public long getPayment()
	{
		return downPayment;
	}

	
}
