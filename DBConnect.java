package sources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	public static Connection dbConn;
    
    public static Connection getConnection()
    {
        Connection conn = null;
        try {
            String user = "c##evan"; 
            String pw = "0325";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            
            Class.forName("oracle.jdbc.driver.OracleDriver");        
            conn = DriverManager.getConnection(url, user, pw);
            
//            System.out.println("Database�� ����Ǿ����ϴ�.\n");
            
            conn.setAutoCommit(false);
            
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB ����̹� �ε� ���� :"+cnfe.toString());
        } catch (SQLException sqle) {
            System.out.println("DB ���ӽ��� : "+sqle.toString());
        } catch (Exception e) {
            System.out.println("Unkonwn error");
            e.printStackTrace();
        }
        
        
        return conn;     
    }
}