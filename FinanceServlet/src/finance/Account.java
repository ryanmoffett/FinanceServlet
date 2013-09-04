
/**
 * FDM Group Assessment Day
 * August 6, 2013
 * @author Ryan Moffett
 */

package finance;
import java.util.*;
public class Account {
	
	/**
	 * Creates an account that keeps track of funds and transaction history
	 * @method writeCheck writes a check from the account and updates transaction history
	 * @method makeDeposit makes a deposit to the account and updates transaction history
	 * @method getHistory returns transactions from between specific years
	 */
	
	double funds;
	String user;
	ArrayList<Check> checkHistory;
	ArrayList<Deposit> depositHistory;
	public ArrayList<Transaction> history;
	
	public Account(String user){
		this.funds = 0;
		this.user = user;
		this.checkHistory = new ArrayList<Check>();
		this.depositHistory = new ArrayList<Deposit>();
		this.history = new ArrayList<Transaction>();
	}
	
	public void setFunds(double amount){
		this.funds = amount;
	}
	
	public double getFunds(){
		return funds;
	}
	
	// this writes a check from the account and updates the account's transaction histories
	public void writeCheck(String store, double amount, int year, int month, int day){
		this.funds = funds - amount;
		GregorianCalendar d = new GregorianCalendar(year, month, day);
		Check check = new Check(store,-amount,d);
		
		// insert check into check history in reverse chronological order
		search:{		
			if (checkHistory.size()==0){checkHistory.add(check);}else{
				int checksize = checkHistory.size();	
				for (int i=0;i<checkHistory.size();i++){
					int x = checkHistory.get(i).date.compareTo(d);
					if (x <= 0){
						checkHistory.add(i,check);
						break search;
					} else {continue;}	
				}
				if(checkHistory.size()==checksize){checkHistory.add(check);}
			}		
		}
		
		// insert check into transaction history in reverse chronological order
		search:{		
			if (history.size()==0){history.add(check);}else{
				int checksize = history.size();	
				for (int i=0;i<history.size();i++){
					int x = history.get(i).date.compareTo(d);
					if (x <= 0){
						history.add(i,check);
						break search;
					} else {continue;}	
				}
				if(history.size()==checksize){history.add(check);}
			}		
		}
		
	}
	
	// this makes a deposit to the account and updates the account's transaction histories
	public void makeDeposit(String from, double amount, int year, int month, int day){
		this.funds = funds + amount;
		GregorianCalendar d = new GregorianCalendar(year, month, day);
		Deposit deposit = new Deposit(from,amount,d);
		
		// insert deposit into deposit history in reverse chronological order
		search:{		
			if (depositHistory.size()==0){depositHistory.add(deposit);}else{
				int checksize = depositHistory.size();	
				for (int i=0;i<depositHistory.size();i++){
					int x = depositHistory.get(i).date.compareTo(d);
					if (x <= 0){
						depositHistory.add(i,deposit);
						break search;
					} else {continue;}	
				}
				if(depositHistory.size()==checksize){depositHistory.add(deposit);}
			}		
		}
		
		// insert deposit into transaction history in reverse chronological order
		search:{		
			if (history.size()==0){history.add(deposit);}else{
				int checksize = history.size();	
				for (int i=0;i<history.size();i++){
					int x = history.get(i).date.compareTo(d);
					if (x <= 0){
						history.add(i,deposit);
						break search;
					} else {continue;}	
				}
				if(history.size()==checksize){history.add(deposit);}
			}		
		}
		
	}
	
	// this returns a list of transactions from between specific years
	public ArrayList<Transaction> getHistory(int fromYear,int toYear){
		ArrayList<Transaction> subhistory = new ArrayList<Transaction>();
		if (history.size()==0){return subhistory;}else{
			for(int i=0;i<history.size();i++){
				if((history.get(i).getYear()<=toYear && fromYear<=history.get(i).getYear())
						|| (history.get(i).getYear()>=toYear && fromYear>=history.get(i).getYear())){
					subhistory.add(history.get(i));
				}
			}
			return subhistory;
		}
	}
	
	public ArrayList<Deposit> getDepositHistory(int fromYear,int toYear){
		ArrayList<Deposit> subhistory = new ArrayList<Deposit>();
		if (depositHistory.size()==0){return subhistory;}else{
			for(int i=0;i<depositHistory.size();i++){
				if((depositHistory.get(i).getYear()<=toYear && fromYear<=depositHistory.get(i).getYear())
						|| (depositHistory.get(i).getYear()>=toYear && fromYear>=depositHistory.get(i).getYear())){
					subhistory.add(depositHistory.get(i));
				}
			}
			return subhistory;
		}
	}
	
	public ArrayList<Check> getCheckHistory(int fromYear,int toYear){
		ArrayList<Check> subhistory = new ArrayList<Check>();
		if (checkHistory.size()==0){return subhistory;}else{
			for(int i=0;i<checkHistory.size();i++){
				if((checkHistory.get(i).getYear()<=toYear && fromYear<=checkHistory.get(i).getYear())
						|| (checkHistory.get(i).getYear()>=toYear && fromYear>=checkHistory.get(i).getYear())){
					subhistory.add(checkHistory.get(i));
				}
			}
			return subhistory;
		}
	}
	
	
	public ArrayList<Transaction> getHistory(){
		ArrayList<Transaction> subhistory = new ArrayList<Transaction>();
		if(history.size()==0){return subhistory;}else{
			for(int i=0;i<history.size();i++){
				subhistory.add(history.get(i));
			}
		}
		return subhistory;
	}
	
	public ArrayList<Deposit> getDepositHistory(){
		ArrayList<Deposit> subhistory = new ArrayList<Deposit>();
		if(depositHistory.size()==0){return subhistory;}else{
			for(int i=0;i<depositHistory.size();i++){
				subhistory.add(depositHistory.get(i));
			}
		}
		return subhistory;
	}
	
	public ArrayList<Check> getCheckHistory(){
		ArrayList<Check> subhistory = new ArrayList<Check>();
		if(checkHistory.size()==0){return subhistory;}else{
			for(int i=0;i<checkHistory.size();i++){
				subhistory.add(checkHistory.get(i));
			}
		}
		return subhistory;
	}
	
	public static void main(String[] args){
		
		// a sample
		Account a = new Account("Ryan");
		a.setFunds(1000.00);
		a.writeCheck("CVS1", 10.00,2010,11,05);
		a.writeCheck("CVS2", 10.00,2009,11,05);
		a.writeCheck("CVS3", 10.00,2008,11,05);
		a.writeCheck("CVS4", 10.00,2007,11,05);
		a.writeCheck("CVS5", 10.00,2005,11,05);
		a.writeCheck("CVS6", 10.00,2005,11,05);
		a.makeDeposit("Here", 100, 2005, 2, 25);
		a.makeDeposit("Here2", 20, 2007, 2, 14);
		
		System.out.println(a.checkHistory.size());
		System.out.println(a.funds);
		
		// print out check history
		for (int i=0;i<a.checkHistory.size();i++){
			System.out.println(a.checkHistory.get(i).where+" "+a.checkHistory.get(i).amount);
		}
		
		// print out deposit history
		System.out.println("-----");
		for (int i=0;i<a.depositHistory.size();i++){
			System.out.println(a.depositHistory.get(i).where+" "+a.depositHistory.get(i).amount);
		}
		
		// print out transaction history
		System.out.println("-----");
		for (int i=0;i<a.history.size();i++){
			System.out.println(a.history.get(i).where+" "+a.history.get(i).amount);
		}
		
		// print out transaction history between years 2008 and 2010
		System.out.println("-----");
		for (int i=0;i<a.getHistory(2008, 2010).size();i++){
			System.out.println(a.getHistory(2008, 2010).get(i).where+" "+a.getHistory(2008, 2010).get(i).amount
					+" "+a.getHistory(2008, 2010).get(i).getMonth()+"/"+a.getHistory(2008, 2010).get(i).getDay()+"/"
					+a.getHistory(2008, 2010).get(i).getYear());
		}
		
		// print out transaction history between years 2010 and 2008
		System.out.println("-----");
		for (int i=0;i<a.getHistory(2010, 2008).size();i++){
			System.out.println(a.getHistory(2010, 2008).get(i).where+" "+a.getHistory(2010, 2008).get(i).amount
					+" "+a.getHistory(2010, 2008).get(i).getMonth()+"/"+a.getHistory(2010, 2008).get(i).getDay()+"/"
					+a.getHistory(2010, 2008).get(i).getYear());
		}
	}

}
