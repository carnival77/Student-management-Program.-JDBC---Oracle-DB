package sources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class Transaction {
	
	public static Connection conn;
	public static Statement st;
	public static ResultSet rs;
	public static PreparedStatement ps;
	public static String sql1="select * from course";
	
	String[] lecture_conditions = {"course_id","title","semester","grade","classroom",
			"time_slot_id","dept_name","credit","tch_name"};
	
	String[] std_conditions = {"std_ID","std_name","dept_name"};
	
	DefaultTableModel dtm;
	
	// Transaction T1
	public String[] T1(int inx1, String item1, int inx2, String item2, int inx3,
			String item3) {
		
		String sql=null;
		// SQL S1-1
		String sql1="select * from course";
		String[] result = new String[6];
		
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		ResultSet rs;
		
		int temp1 = item1.length();
		int temp2 = item2.length();
		int temp3 = item3.length();
		
		
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			
			ps=conn.prepareStatement(sql1);
			rs=ps.executeQuery();
			
			if (temp1 !=0 && temp2 ==0 && temp3 ==0) {
				// SQL S2-1
				sql = "select * from course where "
						+ lecture_conditions[inx1] + "=?" ;
				
				result[0]="1";
				result[1]="1";
				result[3] = item1;	
			}
			else if (temp1 !=0 && temp2 !=0 && temp3 ==0) {
				// SQL S2-2
				sql = "select * from course where "
						+ lecture_conditions[inx1] + "=?" 
						+ " and " + 
						lecture_conditions[inx2] + "=?";
				
				result[0]="1";
				result[1]="2";
				result[3] = item1;
				result[4] = item2;
			}
			else if(temp1 !=0 && temp2 !=0 && temp3 !=0) {
				// SQL S2-3
				sql = "select * from course where "
						+ lecture_conditions[inx1] + "=?" 
						+ " and " + 
						lecture_conditions[inx2] + "=?" 
						+ " and " +
						lecture_conditions[inx3] + "=?";
				
				result[0]="1";
				result[1]="3";
				result[3] = item1;
				result[4] = item2;
				result[5] = item3;
				
			}
			else {
				// SQL S2-4
				sql =  "select * from course order by course_id";
				result[0]="0";
			}
		
			result[2] = sql;
			
			ps1=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			conn.commit();
					
			}catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	if (ps != null) { 
	        		try { 
	        			ps.close(); 
	        			} catch (SQLException e) { 
	        				e.printStackTrace(); 
	        				} 
	        		} 
	        	if (ps1 != null) { 
	        		try { 
	        			ps.close(); 
	        			} catch (SQLException e) { 
	        				e.printStackTrace(); 
	        				} 
	        		} 
	        	if (conn != null) {
	    			try {
	    				conn.close(); 
	    			} catch (SQLException e) {
	    				e.printStackTrace(); 
	    			} 
	        	}
	        }
		return result;
	}
	
	// Transaction T2 
	public String[] T2(int inx1, String item1, int inx2, String item2, int inx3,
			String item3) {
		
		String sql=null;
		// SQL S1-1
		String sql1="select * from student";
		String[] result = new String[6];
		
		int temp1 = item1.length();
		int temp2 = item2.length();
		int temp3 = item3.length();
		
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		ResultSet rs;
		
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			
			ps=conn.prepareStatement(sql1);
			rs=ps.executeQuery();
			
			if (temp1 !=0 && temp2 ==0 && temp3 ==0) {
				// SQL S2-1
				sql = "select * from student where "
						+ std_conditions[inx1] + "=?";
				
				result[0]="1";
				result[1]="1";
				result[3] = item1;	
			}
			else if (temp1 !=0 && temp2 !=0 && temp3 ==0) {
				// SQL S2-2
				sql = "select * from student where "
						+ std_conditions[inx1] + "=?" 
						+ " and " + 
						std_conditions[inx2] + "=?";
				
				result[0]="1";
				result[1]="2";
				result[3] = item1;
				result[4] = item2;
			}
			else if(temp1 !=0 && temp2 !=0 && temp3 !=0)  {
				// SQL S2-3
				sql = "select * from student where "
						+ std_conditions[inx1] + "=?" 
						+ " and " + 
						std_conditions[inx2] + "=?" 
						+ " and " +
						std_conditions[inx3] + "=?";
				
				result[0]="1";
				result[1]="3";
				result[3] = item1;
				result[4] = item2;
				result[5] = item3;
			}
			else {
				// SQL S2-4
				sql =  "select * from student order by std_ID";
				result[0]="0";
			}
		
			result[2] = sql;
			
			ps1=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			conn.commit();
					
		}catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if (ps != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (ps1 != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (conn != null) {
    			try {
    				conn.close(); 
    			} catch (SQLException e) {
    				e.printStackTrace(); 
    			} 
    	}
        }
		return result;
	}
	
	// Transaction T3
	public String[] T3(int inx1, String item1) {
	
		String sql=null;
		String sql1=null;
		String sql2=null;
		String sql3 = null;
		String time_slot_id = null;
		String course_id = item1;
		String[] result = new String[3];
		
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		ResultSet rs;
		
		// SQL S3-1
		sql = "select time_slot_id from course where "
				+ lecture_conditions[inx1] + "=" 
				+ "'"+course_id+"'";
		
		// SQL S3-2
		sql1 = "delete from course where "
				+ lecture_conditions[inx1] + "=?"; 
		
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				time_slot_id = rs.getString(1);
			}
			
			ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, course_id);
			int rs1 = ps1.executeUpdate();
			
			// SQL S3-3
			sql2 = "delete from time_slot where "
					+ "time_slot_id" + "=?";
			ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, time_slot_id);
			int rs2 = ps2.executeUpdate();
			
			conn.commit();
			// SQL S3-4
			sql3 =  "select * from course order by course_id";
			
			result[0]="0";
			result[2]=sql3;
					
		}catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if (ps != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (ps1 != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (ps2 != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (conn != null) {
        			try {
        				conn.close(); 
        			} catch (SQLException e) {
        				e.printStackTrace(); 
        			} 
        	}
        
        }
		return result;
	}
	
	// Transaction T4
	public String[] T4(int inx1, String item1) {
		
		String sql=null;
		String sql1=null;
		String sql2=null;
		String sql3 = null;
		String std_id = item1;
		String[] result = new String[3];
		
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		ResultSet rs;
		
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			
			// SQL S4-1 
			sql2 = "select * from student where std_id =?";
			ps = conn.prepareStatement(sql2);
			ps.setString(1, item1);
			rs=ps.executeQuery();
			
			if(rs.next()) {
				// SQL S4-2
				sql = "delete from takes where "
						+ std_conditions[inx1] + "=?"; 
				// SQL S4-3
				sql1 = "delete from student where "
						+ std_conditions[inx1] + "=?"; 

				ps = conn.prepareStatement(sql);
				ps.setString(1, std_id);
				rs = ps.executeQuery();
				
				ps1 = conn.prepareStatement(sql1);
				ps1.setString(1, std_id);
				rs = ps1.executeQuery();
			}
			
			conn.commit();
			// SQL S4-3
			sql3 =  "select * from student order by std_id";
			
			result[0]="0";
			result[2] = sql3;
					
		}catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if (ps != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (ps1 != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (conn != null) {
        			try {
        				conn.close(); 
        			} catch (SQLException e) {
        				e.printStackTrace(); 
        			} 
        	}
        
        }
		return result;
	}
	
	// Transaction T5
	public String T5(String item5) {
		String t=null;
		return t;
	}
	
	// Transaction T7
	public String[] T7(int inx1, String item1, int inx2, String item2, 
			int inx3, String item3, String item4) {
		
		String[] sql = new String[10];
		String sql2= null;
		String[] result = new String[3];
		
		String get_1 = item1;
		String get_2 = item2;
		String get_3 = item3;
		String course_id = item4;
		boolean foreign_dec=true;
		
		String[] not_foreign = new String[] {
				"classroom","time_slot_id","dept_name","credit"
		};
		
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		Statement st=null;
		ResultSet rs;
		
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			
			if(!item1.equals("") && item2.equals("") && item3.equals("")) {
				
				for (int i=0;i<not_foreign.length;i++) {
					if(lecture_conditions[inx1].equals(not_foreign[i])){
						foreign_dec=false;
						break;
					}
				}
				
				if(foreign_dec) {
				
					// SQL S7-1-1
					sql[1] = "create or replace trigger update_trigger1 "
							+ "after update on course "
							+ "for each row "
							+ "begin "
							+ "update takes "
							+ "set "+ lecture_conditions[inx1]
							+ "="+ get_1 
							+ " where course_id=" +course_id+";"+" end;" ;
					
					// SQL S7-1-2
					sql[2] = "create or replace trigger update_trigger2 "
							+ "after update on course "
							+ "for each row "
							+ "begin "
							+ "update teaches "
							+ "set "+ lecture_conditions[inx1]
							+ "="+ get_1 
							+ " where course_id=" +course_id+";"+" end;" ;
					
					st=conn.createStatement();
					st.execute(sql[1]);
					st=conn.createStatement();
					st.execute(sql[2]);
				}
				
				// SQL S7-1-3
				sql[9] = "update course set "+ lecture_conditions[inx1] +"=?" 
						+ " where course_id=" +course_id; 
				
				ps1 = conn.prepareStatement(sql[9]);
				ps1.setString(1, get_1);
			}
			else if(!item1.equals("") && !item2.equals("") && item3.equals("")) 
			{
				// SQL S7-2-1
				sql[1] = "create or replace trigger update_trigger3 "
						+ "after update on course "
						+ "for each row "
						+ "begin "
						+ "update takes "
						+ "set "+ lecture_conditions[inx1]
						+ "="+ get_1 +","
						+ lecture_conditions[inx2]
						+ "="+get_2
						+ " where course_id=" +course_id+";"+" end;" ;
				
				// SQL S7-2-2
				sql[2] = "create or replace trigger update_trigger4 "
						+ "after update on course "
						+ "for each row "
						+ "begin "
						+ "update teaches "
						+ "set "+ lecture_conditions[inx1]
						+ "="+ get_1 +","
						+ lecture_conditions[inx2]
						+ "="+get_2
						+ " where course_id=" +course_id+";"+" end;" ;
				
				// SQL S7-2-3
				sql[9] = "update course set "+ lecture_conditions[inx1] +"=?," 
						+ lecture_conditions[inx2] +"=?"
						+ " where course_id=" +course_id; 
				
				st=conn.createStatement();
				st.execute(sql[1]);
				st=conn.createStatement();
				st.execute(sql[2]);
				
				ps1 = conn.prepareStatement(sql[9]);
				ps1.setString(1, get_1);
				ps1.setString(2, get_2);
			}
//			else if(!item1.equals("") && !item2.equals("") && !item3.equals("")) 
//			{
//				// SQL S7-3-1
//				sql[1] = "create or replace trigger update_trigger3 "
//						+ "after update on course "
//						+ "for each row "
//						+ "begin "
//						+ "update takes "
//						+ "set "+ lecture_conditions[inx1]
//						+ "="+ get_1 +","
//						+ lecture_conditions[inx2]
//						+ "="+get_2
//						+ " where course_id=" +course_id+";"+" end;" ;
//				
//				// SQL S7-3-2
//				sql1 = "update course set "+ lecture_conditions[inx1] +"=?," 
//						+ lecture_conditions[inx2] +"=?,"
//						+ lecture_conditions[inx3] +"=?"
//						+ " where course_id=" +course_id;
//				
//				ps = conn.prepareStatement(sql2);
//				rs= ps.executeQuery();
//				
//				ps1 = conn.prepareStatement(sql1);
//				ps1.setString(1, get_1);
//				ps1.setString(2, get_2);
//				ps1.setString(3, get_3);
//			}

			sql[0] = "select * from course where "
					+ "course_id" + "=" 
					+ get_1;
			
			result[0] = "0";
			result[1]="1";
			result[2] = sql[0];
			
			rs= ps1.executeQuery();
			
			conn.commit();
					
		}catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if (ps != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (ps1 != null) { 
        		try { 
        			ps1.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (conn != null) {
        			try {
        				conn.close(); 
        			} catch (SQLException e) {
        				e.printStackTrace(); 
        			} 
        	}
        
        }
		return result;
	}
	
	// Transation T8
	public String[] T8(int inx1, String item1, int inx2, String item2, 
			int inx3, String item3, String item4) {
		
		String[] sql = new String[10];
		
		String get_1 = item1;
		String get_2 = item2;
		String get_3 = item3;
		String std_id = item4;
		String[] result = new String[3];
		result[0]="0";
		
		
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		Statement st=null;
		ResultSet rs;
		
		// SQL S8-1
		sql[8] = "select * from student where "
				+ "std_id" + "=" 
				+ get_1;
		
		result[2]=sql[8];
		
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			
			// SQL S8-1
			sql[0] = "select * from student where "
					+ "std_id" + "=?" ;
			
			if(!item1.equals("") && item2.equals("") && item3.equals("")) {
				
				// SQL S8-2-1
				sql[1] = "create or replace trigger update_trigger1 "
						+ "after update on student "
						+ "for each row "
						+ "begin "
						+ "update takes "
						+ "set "+ std_conditions[inx1]
						+ "="+ get_1 
						+ " where std_id=" +std_id+";"+" end;" ;
				
				st=conn.createStatement();
				st.execute(sql[1]);
				
				ps = conn.prepareStatement(sql[0]);
				ps.setString(1, get_1);
				rs=ps.executeQuery();
				int target_index = rs.findColumn("std_id");
				
				// SQL S8-2-2
				sql[9] = "update student set "+ std_conditions[target_index-1] +"=?" 
						+ " where std_id=" +std_id; 
				
				ps1 = conn.prepareStatement(sql[9]);
				ps1.setString(1, get_1);
			}
			else if(!item1.equals("") && !item2.equals("") && item3.equals("")) 
			{
				// SQL S8-3-1
				sql[1] = "create or replace trigger update_trigger1 "
						+ "after update on student "
						+ "for each row "
						+ "begin "
						+ "update takes "
						+ "set "+ std_conditions[inx1]
						+ "="+ get_1 
						+ " where std_id=" +std_id+";"+" end;" ;
				
				st=conn.createStatement();
				st.execute(sql[1]);
				
				ps = conn.prepareStatement(sql[0]);
				ps.setString(1, get_1);
				rs=ps.executeQuery();
				int target_index = rs.findColumn("std_id");
				
				// SQL S8-3-2
				sql[9] = "update student set "+ std_conditions[target_index-1] +"=?," 
						+ std_conditions[inx2] +"=?"
						+ " where std_id=" +std_id; 
				
				ps1 = conn.prepareStatement(sql[9]);
				ps1.setString(1, get_1);
				ps1.setString(2, get_2);
			}
			else if(!item1.equals("") && !item2.equals("") && !item3.equals("")) 
			{
				// SQL S8-4-1
				sql[1] = "create or replace trigger update_trigger1 "
						+ "after update on student "
						+ "for each row "
						+ "begin "
						+ "update takes "
						+ "set "+ std_conditions[inx1]
						+ "="+ get_1 
						+ " where std_id=" +std_id+";"+" end;" ;
				
				st=conn.createStatement();
				st.execute(sql[1]);
				
				ps = conn.prepareStatement(sql[0]);
				ps.setString(1, get_1);
				rs=ps.executeQuery();
				int target_index = rs.findColumn("std_id");
				
				// SQL S8-4-2
				sql[9] = "update student set "+ std_conditions[target_index-1] +"=?," 
						+ std_conditions[inx2] +"=?,"
						+ std_conditions[inx3] +"=?"
						+ " where std_id=" +std_id;
				
				ps1 = conn.prepareStatement(sql[9]);
				ps1.setString(1, get_1);
				ps1.setString(2, get_2);
				ps1.setString(3, get_3);
			}
			
			rs= ps1.executeQuery();
			
			conn.commit();
					
		}catch (Exception e) {
            e.printStackTrace();
        }finally {
        	if (ps != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (ps1 != null) { 
        		try { 
        			ps1.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (conn != null) {
        			try {
        				conn.close(); 
        			} catch (SQLException e) {
        				e.printStackTrace(); 
        			} 
        	}
        
        }
		return result;
	}

	// Transation T6
	public String[] T6(int inx1, String item1, int inx2, String item2, int inx3,
			String item3) {
		
		// SQL S8-1
		String sql="select * from student where std_id = "+item1;
		String sql1=null;
		String sql2=null;
		String[] result = new String[3];
		result[0] = "0";
		result[2] = sql;
		
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		ResultSet rs = null;
		
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			
			// SQL S8-2
			sql1 = "insert into student(std_id,std_name,dept_name) values "
					+ "(?,?,?)";
			
			// SQL S8-3
			sql2 = "insert into takes(std_id,course_id,title,semester,"
					+ "grade,remaining_seat,tch_name) values "
					+ "(?,?,?,?,?,?,?)";
			
			ps=conn.prepareStatement(sql1);
			ps.setString(1,item1);
			ps.setString(2,item2);
			ps.setString(3,item3);
			ps.executeUpdate();
			
			ps1=conn.prepareStatement(sql2);
			ps1.setString(1,item1);
			ps1.setString(2,"10000000");
			ps1.setString(3,"aaa");
			ps1.setString(4,"2");
			ps1.setString(5,"2");
			ps1.setString(6,"2");
			ps1.setString(7,"bbb");
			ps1.executeUpdate();
			
			conn.commit();
					
		}catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if (ps != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (ps1 != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (conn != null) {
    			try {
    				conn.close(); 
    			} catch (SQLException e) {
    				e.printStackTrace(); 
    			} 
    	}
        }
		return result;
	}

	// Transation T9
	public String T9(int inx1, String item1, int inx2, String item2, int inx3,
			String item3) {
		
		String sql=null;
		String sql1="select * from course";
		String sql2 = null;
		String sql3=null;
		String[] sql_ = new String[10];
		
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps3=null;
		
		ResultSet rs;
		ResultSet rs1;
		ResultSet rs2;
		String remaining=null;
		
		int temp1 = item1.length();
		int temp2 = item2.length();
		int temp3 = item3.length();
		
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			
			ps=conn.prepareStatement(sql1);
			rs=ps.executeQuery();
			
			if (temp1 !=0 && temp2 ==0 && temp3 ==0) {
				
				sql_[1] = "create or replace trigger update_trigger3 "
						+ "after update on course "
						+ "for each row "
						+ "begin "
						+ "update takes "
						+ "set remaining_seat"
						+ "=0"
						+ " where " +lecture_conditions[inx1]
						+ "=" + item1 + " and remaining_seat = 1; end;";
				
				sql_[2] = "create or replace trigger update_trigger3 "
						+ "after update on course "
						+ "for each row "
						+ "begin "
						+ "update teaches "
						+ "set remaining_seat"
						+ "=0"
						+ " where " +lecture_conditions[inx1]
						+ "=" + item1 + " and remaining_seat = 1; end;";
				
				st=conn.createStatement();
				st.execute(sql_[1]);
				st=conn.createStatement();
				st.execute(sql_[2]);
				
				sql = "select * from course where "
						+ lecture_conditions[inx1] + "=?" ;
				
				ps1=conn.prepareStatement(sql);
				ps1.setString(1, item1);
				
				rs=ps1.executeQuery();
				while(rs.next()) {
					remaining = rs.getString(10);
				}
				sql2 = "select * from course where "
						+ lecture_conditions[inx1] + "=? and "
						+ "remaining_seat =?";
				
				ps2=conn.prepareStatement(sql2);
				ps2.setString(1, item1);
				ps2.setString(2, remaining);
				
				rs1=ps2.executeQuery();

				sql3 = "update course set remaining_seat=? where "
						+ lecture_conditions[inx1] + "=? and "
						+ "remaining_seat =?";
				
				ps3=conn.prepareStatement(sql3);
				ps3.setString(1, item1);
				ps3.setString(2, remaining);
				
			}
			else if (temp1 !=0 && temp2 !=0 && temp3 ==0) {
				sql = "select * from course where "
						+ lecture_conditions[inx1] + "=?" + " and " + 
						lecture_conditions[inx2] + "=?";
				
				ps=conn.prepareStatement(sql);
				ps.setString(1, item1);
				ps.setString(2, item2);
				
				
				
			}
			else if(temp1 !=0 && temp2 !=0 && temp3 !=0) {
				sql = "select * from course where "
						+ lecture_conditions[inx1] + "=" 
						+ "'"+item1+"'" + " and " + 
						lecture_conditions[inx2] + "=" 
						+ "'"+item2+"'" + " and " +
						lecture_conditions[inx3] + "=" 
						+ "'"+item3+"'";
				
				ps=conn.prepareStatement(sql);
				ps.setString(1, item1);
				ps.setString(2, item2);
				ps.setString(3, item3);
			}
			else {
				sql =  "select * from course where remaining_seat=0";
			}
			
			rs=ps1.executeQuery();
			
			System.out.println(sql);
			
			conn.commit();
					
		}catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if (ps != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (ps1 != null) { 
        		try { 
        			ps.close(); 
        			} catch (SQLException e) { 
        				e.printStackTrace(); 
        				} 
        		} 
        	if (conn != null) {
    			try {
    				conn.close(); 
    			} catch (SQLException e) {
    				e.printStackTrace(); 
    			} 
        	}
        }
		return sql;
	}
}
