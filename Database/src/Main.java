import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {	
	public static void main(String[] args) {
		//Open Connection
		//Connection and Statement are interfaces I guess implemented by the sql package somewhere...
		//Statement is the object that holds the query and returning its results and a ResulSet Object
		//ResultSet is an interface with a shitload of methods that holds objects as tables
		String user;
		String pass;
		String database;
		final String prompt = "mysql -> ";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print(prompt + "username: ");
		user = sc.next();
		System.out.print(prompt + "senha: ");
		pass = sc.next();
		System.out.print(prompt + "banco de dados: ");
		database = sc.next();
		
		System.out.println("Tentando estabelecer conexão com o banco de dados " + database);
		
		Database db = new Database(user,pass,database);
		String url = "jdbc:mysql://localhost/"+database;
		String query;
		Statement stmt;
		//ResultSet rs;
		
		try (Connection conn = DriverManager.getConnection(url, user, pass);)
		{
			System.out.println("Conexão bem sucedida.");
			while(true)
			{
				
				System.out.println("Qual consulta gostaria de fazer?");
				query = sc.nextLine();
				stmt = conn.createStatement();
				try(ResultSet rs = stmt.executeQuery(query);)
				{
					while(rs.next())
					{
						System.out.print("ID: " + rs.getInt("id"));
						System.out.print(", Age: " + rs.getInt("age"));
						System.out.print(", First name: " + rs.getString("first"));
						System.out.println(", Last Name: " + rs.getString("last"));
					}
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					
				}
			System.out.println("Gostaria de fazer uma nova consulta? (s/n)");
			
			if (sc.next() == "s")
				{
					continue;
				}
			else break;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		
	}
}
