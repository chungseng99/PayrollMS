package database.connection;

import java.sql.Connection;

/**
 * This class test the connection to the database
**/
public class TestConnectionDemo {

	/**
	 * Main entry point to the program
	 * @param args
	 */
	public static void main(String[] args) {
		
		DBController dbCtrl = new DBController();
		
		try {
			
			Connection connection = dbCtrl.getConnection();
			System.out.println("Connection to RBDMS is successful!");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
