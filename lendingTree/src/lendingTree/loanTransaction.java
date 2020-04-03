package lendingtree;

public class loanTransaction 
{
	String name;
//	String userName;
	String typeOfLoan; 
	int amountDesired;
	int downPayment;
	double totalDollars;

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
