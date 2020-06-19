package sources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TABLE_CREATION
{
	private static final String CREATE_TABLE_STUDENT ="CREATE TABLE student("
			+ "std_id			varchar(20)		not null,"
			+ "std_name			varchar(20)		not null,"
			+ "dept_name		varchar(20)		not null,"
			+ "constraint student_pk primary key(std_id))";
	
	private static final String CREATE_TABLE_TIME_SLOT ="CREATE TABLE time_slot("
			+ "time_slot_id		varchar(20)		not null,"
			+ "day				varchar(20)		not null,"
			+ "start_time		varchar(20)		not null,"
			+ "end_time			varchar(20)		not null,"
			+ "constraint time_slot_pk primary key(time_slot_id))";
	
	private static final String CREATE_TABLE_COURSE="CREATE TABLE course ("
            + "course_id		varchar(20)		not null,"
            + "title			varchar(20)		not null,"
            + "semester			varchar(20)		not null,"
            + "grade			varchar(20)		not null,"
            + "classroom		varchar(20)		not null,"
            + "time_slot_id		varchar(20)		not null,"
            + "dept_name		varchar(20)		not null,"
            + "credit			varchar(20)		not null,"
            + "tch_name			varchar(20)		not null,"
            + "remaining_seat	varchar(20)		not null,"
            + "constraint course_pk	primary key(course_id,title,semester,"
            + "grade,remaining_seat,tch_name),"
            + "constraint fk_course_time foreign key(time_slot_id)"
            + "references time_slot(time_slot_id)"
            + "on delete cascade)";
	
	private static final String CREATE_TABLE_TAKES ="CREATE TABLE takes("
			+ "std_id			varchar(20)		not null,"
			+ "course_id		varchar(20)		not null,"
			+ "title			varchar(20)		not null,"
			+ "semester			varchar(20)		not null,"
			+ "grade			varchar(20)		not null,"
			+ "remaining_seat	varchar(20)		not null,"
			+ "tch_name			varchar(20)		not null,"
			+ "constraint takes_pk primary key(std_id,course_id,title,"
			+ "semester,grade,remaining_seat,tch_name),"
			+ "constraint fk_takes_std foreign key(std_id)"
			+ "references student(std_id)"
			+ "on delete cascade,"
			+ "constraint fk_takes_course foreign key(course_id,title,semester,"
			+ "grade,remaining_seat,tch_name)"
			+ "references course(course_id,title,semester,grade,remaining_seat,tch_name)"
			+ "on delete cascade)";
	
	private static final String CREATE_TABLE_TEACHES ="CREATE TABLE teaches("
			+ "course_id		varchar(20)		not null,"
			+ "title			varchar(20)		not null,"
			+ "semester			varchar(20)		not null,"
			+ "grade			varchar(20)		not null,"
			+ "tch_name			varchar(20)		not null,"
			+ "tch_id			varchar(20)		not null,"
			+ "remaining_seat	varchar(20)		not null,"
			+ "constraint teaches_pk primary key(tch_id,course_id,title,"
			+ "semester,grade,tch_name,remaining_seat),"
			+ "constraint fk_teaches_course foreign key(course_id,title,"
			+ "semester,grade,tch_name,remaining_seat)"
			+ "references course(course_id,title,semester,grade,tch_name,remaining_seat)"
			+ "on delete cascade)";
	
	private static final String DROP_STUDENT_TABLE = "DROP TABLE STUDENT";
	private static final String DROP_TIME_SLOT_TABLE = "DROP TABLE TIME_SLOT";
	private static final String DROP_COURSE_TABLE = "DROP TABLE COURSE";
	private static final String DROP_TAKES_TABLE = "DROP TABLE TAKES";
	private static final String DROP_TEACHES_TABLE = "DROP TABLE TEACHES";
	
	public void create_table(Connection conn, Statement stmt) {
		
		try {
			
			// DROP ALL TABLE
			stmt.executeUpdate(DROP_TAKES_TABLE);
			stmt.executeUpdate(DROP_STUDENT_TABLE);
			stmt.executeUpdate(DROP_TEACHES_TABLE);
			stmt.executeUpdate(DROP_COURSE_TABLE);
			stmt.executeUpdate(DROP_TIME_SLOT_TABLE);
			
			// CREATE TABLE
			
			stmt.executeUpdate(CREATE_TABLE_STUDENT);
			stmt.executeUpdate(CREATE_TABLE_TIME_SLOT);
			stmt.executeUpdate(CREATE_TABLE_COURSE);
			stmt.executeUpdate(CREATE_TABLE_TAKES);
			stmt.executeUpdate(CREATE_TABLE_TEACHES);
			
			System.out.println("table 생성 성공");
			
				
		}catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Make_ctl(String[] columns, String file_name) {
		
		try {
			String filepath = ".\\"+file_name+".ctl";
			BufferedWriter fw=null;
			
			fw = Files.newBufferedWriter(Paths.get(filepath),Charset.forName("UTF-8"));

        	fw.write("load data"); 
        	fw.newLine();
        	fw.write("infile '.\\"+file_name+".csv'");
        	fw.newLine();
        	fw.write("append");
        	fw.newLine();
        	fw.write("into table "+file_name);
        	fw.newLine();
        	fw.write("fields terminated by ','");
        	fw.newLine();
        	fw.write("(");
        	for (int i= 0;i<columns.length;i++) {
        		fw.write(columns[i]);
        		if (i!=columns.length-1)
        			fw.write(",");
        	}
        	fw.write(")");
        	fw.newLine();
			
			fw.flush();
            
            fw.close();
		
        }catch(IOException e){
            e.printStackTrace();
        }
	}
	
	public void Data_load(Connection conn, Statement st) {
		
		String[] load_db = new String[5];
		
		load_db[0] = "sqlldr c##evan/0325 control=time_slot.ctl";
		load_db[1] = "sqlldr c##evan/0325 control=student.ctl";
		load_db[2] = "sqlldr c##evan/0325 control=course.ctl";
		load_db[3] = "sqlldr c##evan/0325 control=teaches.ctl";
		load_db[4] = "sqlldr c##evan/0325 control=takes.ctl";
		
		String[] student_cols = {"std_ID","std_name","dept_name"};
		String[] takes_cols = {"std_ID","course_id","title","semester",
				"grade","remaining_seat","tch_name"};
		String[] course_cols = {"course_id","title","semester","grade","classroom",
				"time_slot_id","dept_name","credit","tch_name","remaining_seat"};
		String[] teaches_cols = {"tch_ID","course_id","title","semester","grade",
				"tch_name","remaining_seat"};
		String[] time_slot_cols = {"time_slot_id","day","start_time","end_time"};
		
		Make_ctl(takes_cols,"takes");
		Make_ctl(student_cols,"student");
		Make_ctl(teaches_cols,"teaches");
		Make_ctl(course_cols,"course");
		Make_ctl(time_slot_cols,"time_slot");
		
		System.out.println("ctl 파일 완성");
		

			
		try {
			for (int i=0;i<5;i++) {
				Process p = Runtime.getRuntime().exec(load_db[i]);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				
				String line = null;
				
				while ((line = br.readLine()) != null) {
					System.out.println(line);
	
				}
				
				p.waitFor();	
			}
		}catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("db load 완료");
		
	}
}