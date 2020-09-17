package sources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

public class Main {
	
	public static Connection conn;
	public static Statement st;
	public static ResultSet rs;
	public static PreparedStatement ps;
	
	public static void contents() {
	
//		DBConnect db_conn = new DBConnect();
		conn = DBConnect.getConnection();
		
		TABLE_CREATION table_creation = new TABLE_CREATION();
		Data_creation db_creation = new Data_creation();
		GUI gui = new GUI();
		
		try {
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			table_creation.create_table(conn, st);
			
			db_creation.insertion_DB();
			
			table_creation.Data_load(conn, st);
			
			gui.show_GUI(conn,st);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		contents();
	}
}


