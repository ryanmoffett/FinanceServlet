
/**
 * August 6, 2013
 * @author Ryan Moffett
 */

package finance;
import java.util.*;
import java.sql.*;
import java.math.BigDecimal;
public class Account {
	
	/**
	 * Creates an account that keeps track of funds and transaction history
	 * @method writeCheck writes a check from the account and updates transaction history
	 * @method makeDeposit makes a deposit to the account and updates transaction history
	 * @method getHistory returns transactions from between specific years
	 */
	
	String user;
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	int flag;
	
	public Account(String accountholder){
		this.user = accountholder.replace(" ", "");
		this.flag = 0;
		this.connection = null;
		this.statement = null;
		this.resultSet = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM accounts;");
			
			while(resultSet.next()){
				String name = resultSet.getString("name");
				if(name.equals(this.user)){
					this.flag = 1;
				}
			}
			if(flag==0){
				statement.execute("INSERT INTO accounts (name, funds) VALUES ('"+this.user+"',0)");
				statement.execute("CREATE TABLE "+this.user+"_history (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
						+ "transaction VARCHAR(100), description VARCHAR(200), amount DECIMAL(10,2), month INT, day INT, y INT);");
			}
			
			
		}
		catch(Exception error){
			System.out.println("Error: "+error.getMessage());
		}
		finally{
			if (this.connection != null) try{connection.close();} catch(SQLException ignore){}
			if (this.statement != null) try{statement.close();} catch(SQLException ignore){}
			if (this.resultSet != null) try{resultSet.close();} catch(SQLException ignore){}
		}
		
	}
	
	public void setFunds(double amount){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			statement.execute("UPDATE accounts SET funds = "+amount+" WHERE name = '"+this.user+"';");
		}
		catch(Exception error){
			System.out.println("Error: "+error.getMessage());
		}
		finally{
			if (this.connection != null) try{connection.close();} catch(SQLException ignore){}
			if (this.statement != null) try{statement.close();} catch(SQLException ignore){}
		}
	}
	
	public double getFunds(){
		double funds = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT funds FROM accounts WHERE name = '"+this.user+"';");
			resultSet.next();
			funds = resultSet.getDouble("funds");
		}
		catch(Exception error){
			System.out.println("Error: "+error.getMessage());
		}
		finally{
			if (this.connection != null) try{connection.close();} catch(SQLException ignore){}
			if (this.statement != null) try{statement.close();} catch(SQLException ignore){}
		}
		return funds;
	}
	
	// this writes a check from the account and updates the account's transaction histories
	public void writeCheck(String description, double amountdouble){
		BigDecimal funds = new BigDecimal(0);
		BigDecimal amount = new BigDecimal(amountdouble);
		GregorianCalendar d = new GregorianCalendar();
		int month = d.get(Calendar.MONTH)+1;
		int day = d.get(Calendar.DAY_OF_MONTH);
		int year = d.get(Calendar.YEAR);
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT funds FROM accounts WHERE name = '"+this.user+"';");
			resultSet.next();
 			funds = resultSet.getBigDecimal("funds").subtract(amount);
			statement.execute("UPDATE accounts SET funds = "+funds+" WHERE name = '"+this.user+"';");
			statement.execute("INSERT INTO "+this.user+"_history (transaction, description, amount, month, day, y) VALUES ('Withdraw', '"
					+description+"', "+amount+", "+month+", "+day+", "+year+");");
		}
		catch(Exception error){
			System.out.println("ERROR: "+error.getMessage());
		}
		finally{
			if (connection != null) try{connection.close();} catch(SQLException ignore){}
			if (statement != null) try{statement.close();} catch(SQLException ignore){}
			if (resultSet != null) try{resultSet.close();} catch(SQLException ignore){}
		}	
	}
	
	// this makes a deposit to the account and updates the account's transaction histories
	public void makeDeposit(String description, double amountdouble){
		BigDecimal funds = new BigDecimal(0);
		BigDecimal amount = new BigDecimal(amountdouble);
		GregorianCalendar d = new GregorianCalendar();
		int month = d.get(Calendar.MONTH)+1;
		int day = d.get(Calendar.DAY_OF_MONTH);
		int year = d.get(Calendar.YEAR);
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT funds FROM accounts WHERE name = '"+this.user+"'");
			resultSet.next();
 			funds = resultSet.getBigDecimal("funds").add(amount);
 			statement.execute("UPDATE accounts SET funds = "+funds+" WHERE name = '"+this.user+"'");
  			statement.execute("INSERT INTO "+this.user+"_history (transaction, description, amount, month, day, y) VALUES ('Deposit', '"
  					+description+"', "+amount+", "+month+", "+day+", "+year+");");
		}
		catch(Exception error){
			System.out.println("ERROR: "+error.getMessage());
		}
		finally{
			if (connection != null) try{connection.close();} catch(SQLException ignore){}
			if (statement != null) try{statement.close();} catch(SQLException ignore){}
			if (resultSet != null) try{resultSet.close();} catch(SQLException ignore){}
		}		
	}
	
	// this returns a list of transactions from between specific years
	public ArrayList<Transaction> getHistory(int fromYear,int toYear){
		ArrayList<Transaction> subhistory = new ArrayList<Transaction>();
		int i = 0;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+this.user+"_history;");
			while(resultSet.next()){
				if((resultSet.getInt("y")<=toYear && fromYear<=resultSet.getInt("y"))
					|| (resultSet.getInt("y")>=toYear && fromYear>=resultSet.getInt("y"))){
					Transaction transaction = new Transaction(resultSet.getString("transaction"),resultSet.getString("description"),
							resultSet.getDouble("amount"),resultSet.getInt("month")+1,resultSet.getInt("day"),resultSet.getInt("y"));
					subhistory.add(i,transaction);
					i++;
				}
			}
		}
		catch(Exception error){
			System.out.println("ERROR: "+error.getMessage());
		}
		finally{
			if (connection != null) try{connection.close();} catch(SQLException ignore){}
			if (statement != null) try{statement.close();} catch(SQLException ignore){}
			if (resultSet != null) try{statement.close();} catch(SQLException ignore){}
		}
		
		return subhistory;
		
	}
	
	public ArrayList<Transaction> getDepositHistory(int fromYear,int toYear){
		ArrayList<Transaction> subhistory = new ArrayList<Transaction>();
		int i = 0;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+this.user+"_history WHERE transaction = 'Deposit';");
			while(resultSet.next()){
				if((resultSet.getInt("y")<=toYear && fromYear<=resultSet.getInt("y"))
					|| (resultSet.getInt("y")>=toYear && fromYear>=resultSet.getInt("y"))){
					Transaction transaction = new Transaction(resultSet.getString("transaction"),resultSet.getString("description"),
							resultSet.getDouble("amount"),resultSet.getInt("month")+1,resultSet.getInt("day"),resultSet.getInt("y"));
					subhistory.add(i,transaction);
					i++;
				}
			}
		}
		catch(Exception error){
			System.out.println("ERROR: "+error.getMessage());
		}
		finally{
			if (connection != null) try{connection.close();} catch(SQLException ignore){}
			if (statement != null) try{statement.close();} catch(SQLException ignore){}
			if (resultSet != null) try{resultSet.close();} catch(SQLException ignore){}
		}
		
		return subhistory;
		
	}
	
	public ArrayList<Transaction> getCheckHistory(int fromYear,int toYear){
		ArrayList<Transaction> subhistory = new ArrayList<Transaction>();
		int i = 0;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+this.user+"_history WHERE transaction = 'Withdraw';");
			while(resultSet.next()){
				if((resultSet.getInt("y")<=toYear && fromYear<=resultSet.getInt("y"))
					|| (resultSet.getInt("y")>=toYear && fromYear>=resultSet.getInt("y"))){
					Transaction transaction = new Transaction(resultSet.getString("transaction"),resultSet.getString("description"),
							resultSet.getDouble("amount"),resultSet.getInt("month")+1,resultSet.getInt("day"),resultSet.getInt("y"));
					subhistory.add(i,transaction);
					i++;
				}
			}
		}
		catch(Exception error){
			System.out.println("ERROR: "+error.getMessage());
		}
		finally{
			if (connection != null) try{connection.close();} catch(SQLException ignore){}
			if (statement != null) try{statement.close();} catch(SQLException ignore){}
			if (resultSet != null) try{resultSet.close();} catch(SQLException ignore){}
		}
		
		return subhistory;
	}
	
	
	public ArrayList<Transaction> getHistory(){
		ArrayList<Transaction> subhistory = new ArrayList<Transaction>();
		int i = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+this.user+"_history;");
			while(resultSet.next()){
				Transaction transaction = new Transaction(resultSet.getString("transaction"),resultSet.getString("description"),
						resultSet.getDouble("amount"),resultSet.getInt("month")+1,resultSet.getInt("day"),resultSet.getInt("y"));
				subhistory.add(i,transaction);
				i++;
			}
		}
		catch(Exception error){
			System.out.println("ERROR: "+error.getMessage());
		}
		finally{
			if (connection != null) try{connection.close();} catch(SQLException ignore){}
			if (statement != null) try{statement.close();} catch(SQLException ignore){}
			if (resultSet != null) try{resultSet.close();} catch(SQLException ignore){}
		}
		
		return subhistory;
	}
	
	public ArrayList<Transaction> getDepositHistory(){
		ArrayList<Transaction> subhistory = new ArrayList<Transaction>();
		int i = 0;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+this.user+"_history WHERE transaction = 'Deposit';");
			while(resultSet.next()){
				Transaction transaction = new Transaction(resultSet.getString("transaction"),resultSet.getString("description"),
						resultSet.getDouble("amount"),resultSet.getInt("month")+1,resultSet.getInt("day"),resultSet.getInt("y"));
				subhistory.add(i,transaction);
				i++;
			}
		}
		catch(Exception error){
			System.out.println("ERROR: "+error.getMessage());
		}
		finally{
			if (connection != null) try{connection.close();} catch(SQLException ignore){}
			if (statement != null) try{statement.close();} catch(SQLException ignore){}
			if (resultSet != null) try{resultSet.close();} catch(SQLException ignore){}
		}
		
		return subhistory;

	}
	
	public ArrayList<Transaction> getCheckHistory(){
		ArrayList<Transaction> subhistory = new ArrayList<Transaction>();
		int i = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/finance","root","root");
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM "+this.user+"_history WHERE transaction = 'Withdraw';");
			while(resultSet.next()){
				Transaction transaction = new Transaction(resultSet.getString("transaction"),resultSet.getString("description"),
						resultSet.getDouble("amount"),resultSet.getInt("month")+1,resultSet.getInt("day"),resultSet.getInt("y"));
				subhistory.add(i,transaction);
				i++;
			}
		}
		catch(Exception error){
			System.out.println("ERROR: "+error.getMessage());
		}
		finally{
			if (connection != null) try{connection.close();} catch(SQLException ignore){}
			if (statement != null) try{statement.close();} catch(SQLException ignore){}
			if (resultSet != null) try{resultSet.close();} catch(SQLException ignore){}
		}
		
		return subhistory;
	}
	
	public static void main(String[] args){
		
		// a sample
		Account testing = new Account("testing");
		testing.makeDeposit("testing123", 500.00);
		testing.writeCheck("testing456", 25.00);
		
		for (int i=0;i<testing.getHistory().size();i++){
			System.out.println(testing.getHistory().get(i).getTransaction()+" "+testing.getHistory().get(i).getAmount());
		}
		
		System.out.println("-----");
		for (int i=0;i<testing.getDepositHistory().size();i++){
			System.out.println(testing.getDepositHistory().get(i).getTransaction()+" "+testing.getDepositHistory().get(i).getAmount());
		}
		
		System.out.println("-----");
		for (int i=0;i<testing.getCheckHistory().size();i++){
			System.out.println(testing.getCheckHistory().get(i).getTransaction()+" "+testing.getCheckHistory().get(i).getAmount());
		}
		
		System.out.println("-----");
		for (int i=0;i<testing.getHistory(2009,2014).size();i++){
			System.out.println(testing.getHistory(2009,2014).get(i).getTransaction()+" "+testing.getHistory(2009,2014).get(i).getAmount());
		}
		
		System.out.println("-----");
		for (int i=0;i<testing.getDepositHistory(2009,2012).size();i++){
			System.out.println(testing.getDepositHistory(2009,2012).get(i).getTransaction()+" "+testing.getDepositHistory(2009,2012).get(i).getAmount());
		}
		
		System.out.println("-----");
		for (int i=0;i<testing.getCheckHistory(2009,2014).size();i++){
			System.out.println(testing.getCheckHistory(2009,2014).get(i).getTransaction()+" "+testing.getCheckHistory(2009,2014).get(i).getAmount());
		}
		
	}

}
