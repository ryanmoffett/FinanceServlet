package finance;
import java.util.*;
public class Check extends Transaction{
	
	public Check(String store, double spent, GregorianCalendar d){
		super(store,spent,d);
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

