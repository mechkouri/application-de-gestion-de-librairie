package ConnectionDB;

import java.sql.*;


public class Conix {
private static Connection con;

static{
	
		try {
			   Class.forName("oracle.jdbc.OracleDriver");
			 con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:orcl","system","Fonx8888");
				System.out.println("Connecté e");

		} catch (ClassNotFoundException e) {
			System.out.println("probleme au niveau du pilotage");
		}catch (SQLException e) {
			System.out.println("probleme au niveau de base ou accrs au donnees");
		}

		
	
	
}

public static Connection getCon() {
	return con;
}


      
}
