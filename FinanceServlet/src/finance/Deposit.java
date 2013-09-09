package finance;
import java.util.*;
public class Deposit extends Transaction{
	
	public Deposit(String transaction, String description, double amount, int month, int day, int year){
		super(transaction, description,amount,month,day,year);
	}
	
	public String getTransaction(){
		return super.getTransaction();
	}
	
	public int getYear(){
		return super.getYear();
	}
	
	public int getMonth(){
		return super.getMonth();
	}
	
	public int getDay(){
		return super.getDay();
	}
	
	public double getAmount(){
		return super.getAmount();
	}
	
	public String getDescription(){
		return super.getDescription();
	}

}

