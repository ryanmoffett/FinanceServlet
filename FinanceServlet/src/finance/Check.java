package finance;
import java.util.*;
public class Check extends Transaction{
	
	public Check(String transaction, String description, double spent, int month, int day, int year){
		super(transaction,description,spent,month,day,year);
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

