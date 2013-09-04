package finance;
import java.util.*;
public class Deposit extends Transaction{
	
	public Deposit(String from, double amount, GregorianCalendar d){
		super(from,amount,d);
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
	public String getWhere(){
		return super.getWhere();
	}
	
	public double getAmount(){
		return super.getAmount();
	}

}

