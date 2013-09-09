package finance;
import java.util.*;
public class Transaction {
	
	public String transaction;
	public String description;
	public double amount;
	public GregorianCalendar date;
	public int month;
	public int day;
	public int year;
	
	public Transaction(String transaction, String description, double amount, int month, int day, int year){
		this.description = description;
		this.amount = amount;
		this.month = month;
		this.day = day;
		this.year = year;
		this.transaction = transaction;
	}
	
	public String getTransaction(){
		return this.transaction;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public int getMonth(){
		return this.month;
	}
	
	public int getDay(){
		return this.day;
	}
	
	
	public double getAmount(){
		return this.amount;
	}
	
	public String getDescription(){
		return this.description;
	}

}

