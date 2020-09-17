package sources;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//// Transaction T1
//	public String[] T1(int inx1, String item1, int inx2, String item2, int inx3,
//			String item3) {
//		
//		String sql=null;
//		// SQL S1-1
//		String sql1="select * from course";
//		String[] result = new String[6];
//		
//		PreparedStatement ps=null;
//		PreparedStatement ps1=null;
//		ResultSet rs;
//		
//		int temp1 = item1.length();
//		int temp2 = item2.length();
//		int temp3 = item3.length();
//		
//		
//		try {
//			conn = DBConnect.getConnection();
//			conn.setAutoCommit(false);
//			
//			ps=conn.prepareStatement(sql1);
//			rs=ps.executeQuery();
//			
//			if (temp1 !=0 && temp2 ==0 && temp3 ==0) {
//				// SQL S2-1
//				sql = "select * from course where "
//						+ lecture_conditions[inx1] + "=?" ;
//				
//				result[0]="1";
//				result[1]="1";
//				result[3] = item1;	
//			}
//			else if (temp1 !=0 && temp2 !=0 && temp3 ==0) {
//				// SQL S2-2
//				sql = "select * from course where "
//						+ lecture_conditions[inx1] + "=?" 
//						+ " and " + 
//						lecture_conditions[inx2] + "=?";
//				
//				result[0]="1";
//				result[1]="2";
//				result[3] = item1;
//				result[4] = item2;
//			}
//			else if(temp1 !=0 && temp2 !=0 && temp3 !=0) {
//				// SQL S2-3
//				sql = "select * from course where "
//						+ lecture_conditions[inx1] + "=?" 
//						+ " and " + 
//						lecture_conditions[inx2] + "=?" 
//						+ " and " +
//						lecture_conditions[inx3] + "=?";
//				
//				result[0]="1";
//				result[1]="3";
//				result[3] = item1;
//				result[4] = item2;
//				result[5] = item3;
//				
//			}
//			else {
//				// SQL S2-4
//				sql =  "select * from course order by course_id";
//				result[0]="0";
//			}
//		
//			result[2] = sql;
//			
//			conn.commit();
//					
//			}catch (Exception e) {
//	            e.printStackTrace();
//	        } finally {
//	        	if (ps != null) { 
//	        		try { 
//	        			ps.close(); 
//	        			} catch (SQLException e) { 
//	        				e.printStackTrace(); 
//	        				} 
//	        		} 
//	        	if (ps1 != null) { 
//	        		try { 
//	        			ps.close(); 
//	        			} catch (SQLException e) { 
//	        				e.printStackTrace(); 
//	        				} 
//	        		} 
//	        	if (conn != null) {
//	    			try {
//	    				conn.close(); 
//	    			} catch (SQLException e) {
//	    				e.printStackTrace(); 
//	    			} 
//	        	}
//	        }
//		return result;
//	}