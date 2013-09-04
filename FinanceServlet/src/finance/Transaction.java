package finance;
import java.util.*;
public class Transaction {
	
	public String where;
	public double amount;
	public GregorianCalendar date;
	
	public Transaction(String where, double amount, GregorianCalendar d){
		this.where = where;
		this.amount = amount;
		this.date = d;
	}
	
	public int getYear(){
		return date.get(Calendar.YEAR);
	}
	
	public int getMonth(){
		return date.get(Calendar.MONTH);
	}
	
	public int getDay(){
		return date.get(Calendar.DAY_OF_MONTH);
	}
	
	public String getWhere(){
		return this.where;
	}
	
	public double getAmount(){
		return this.amount;
	}

}

