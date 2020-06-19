package sources;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class Data_creation {
	
	public void takes_DB(String[] student_ID,String[] course_id,String[] title,
			String[] semester,String[] grade,String[] remaining_seat, String[] tch_name) {
		
		String filepath = ".\\takes.csv";
		BufferedWriter fw=null;
		
		try {
			
			fw = Files.newBufferedWriter(Paths.get(filepath),Charset.forName("UTF-8"));
			
			for (int i=0; i<10000; i++) {
				fw.write(student_ID[i]); // student_ID
				fw.write(",");
	        	fw.write(course_id[i]);// course_id
	        	fw.write(",");
	        	fw.write(title[i]);// title
	        	fw.write(",");
	        	fw.write(semester[i]);// semester
	        	fw.write(",");
	        	fw.write(grade[i]);// grade
	        	fw.write(",");
	        	fw.write(remaining_seat[i]);// remaining_seat
	        	fw.write(",");
	        	fw.write(tch_name[i]);// tch_name
	        	fw.write(",");
	
	        	fw.newLine();
			}
			
			fw.flush();
            
            fw.close();
		
        }catch(IOException e){
            e.printStackTrace();
        }
	}
	
	public void student_DB(String[] student_ID,String[] name,String[] dept_name) {
		
		String filepath = ".\\student.csv";
		BufferedWriter fw=null;
		
		try {
			
			fw = Files.newBufferedWriter(Paths.get(filepath),Charset.forName("UTF-8"));
		
			for (int i = 0 ; i<10000;i++) {
            	fw.write(student_ID[i]); // student_ID
            	fw.write(",");
            	fw.write(name[i]); // name
            	fw.write(",");
            	fw.write(dept_name[i]); // dept_name

            	fw.newLine();
			}
			
			fw.flush();
            
            fw.close();
		
        }catch(IOException e){
            e.printStackTrace();
        }
	}
	
	public void course_DB(String[] course_id,String[] title,String[] semester,String[] grade,
			String[] classroom,String[] time_slot_id,String[] dept_name,String[] credit
			,String[] tch_name,String[] remaining_seat) {
		
		String filepath = ".\\course.csv";
		BufferedWriter fw=null;
		
		try {
			
			fw = Files.newBufferedWriter(Paths.get(filepath),Charset.forName("UTF-8"));
			
			for (int j = 0 ; j<10000;j++) {
            	fw.write(course_id[j]);// course_id
            	fw.write(",");
            	fw.write(title[j]);// title
            	fw.write(",");
            	fw.write(semester[j]);// semester
            	fw.write(",");
            	fw.write(grade[j]);// grade
            	fw.write(",");
            	fw.write(classroom[j]);// classroom
            	fw.write(",");
            	fw.write(time_slot_id[j]);// time_slot_id
            	fw.write(",");
            	fw.write(dept_name[j]);// dept_name
            	fw.write(",");
            	fw.write(credit[j]);// credit
            	fw.write(",");
            	fw.write(tch_name[j]);// tch_name
            	fw.write(",");
            	fw.write(remaining_seat[j]);// remaining_seat
            	
            	fw.newLine();
			}
			
			fw.flush();
            
            fw.close();
		
        }catch(IOException e){
            e.printStackTrace();
        }
	}

	public void teaches_DB(String[] tch_ID,String[] course_id,String[] title,
			String[] semester,String[] grade,String[] tch_name, String[] remaining_seat) {
		
		String filepath = ".\\teaches.csv";
		BufferedWriter fw=null;
		
		try {
			
			fw = Files.newBufferedWriter(Paths.get(filepath),Charset.forName("UTF-8"));

			
			for (int j = 0 ; j<10000;j++) {
            	fw.write(tch_ID[j]); // teaches_ID
            	fw.write(",");
            	fw.write(course_id[j]);// course_id
            	fw.write(",");
            	fw.write(title[j]);// title
            	fw.write(",");
            	fw.write(semester[j]);// semester
            	fw.write(",");
            	fw.write(grade[j]);// grade
            	fw.write(",");
            	fw.write(tch_name[j]);// tch_name
            	fw.write(",");
            	fw.write(remaining_seat[j]);// remaining_seat
            	fw.write(",");

            	fw.newLine();
			}
			
			fw.flush();
            
            fw.close();
		
        }catch(IOException e){
            e.printStackTrace();
        }
	}

	public void time_slot_DB(String[] time_slot_id,String[] day,String[] start_time,
			String[] end_time) {
			
			String filepath = ".\\time_slot.csv";
			BufferedWriter fw=null;
			
			try {
				
				fw = Files.newBufferedWriter(Paths.get(filepath),Charset.forName("UTF-8"));
			
				for (int i = 0 ; i<10000;i++) {
	            	fw.write(time_slot_id[i]); // time_slot_id
	            	fw.write(",");
	            	fw.write(day[i]); // day
	            	fw.write(",");
	            	fw.write(start_time[i]); // start_time
	            	fw.write(",");
	            	fw.write(end_time[i]); // end_time
	            	fw.write(",");
	
	            	fw.newLine();
				}
				
				fw.flush();
	            
	            fw.close();
			
	        }catch(IOException e){
	            e.printStackTrace();
	        }
	}
	
	public void insertion_DB(){
		
		Random random = new Random();
		
		char A = 65;
		char a = 97;
		
		String[] days = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
		String[] start_times= {"9","10","11","12","13","14","15","16","17","18"};
		String[] end_times= {"11","12","13","14","15","16","17","18","19","20"};
		
		String[] std_ID = new String[10000];
		String[] std_name = new String[10000];
		String[] dept_name = new String[10000];
		
		String[] course_id = new String[10000];
		String[] title = new String[10000];
		String[] tch_name = new String[10000];
		String[] semester = new String[10000];
		String[] grade = new String[10000];
		String[] remaining_seat = new String[10000];
		
		String[] classroom = new String[10000];
		String[] time_slot_id = new String[10000];
		String[] credit = new String[10000];
//		String[] like = new String[10000];
		
		String[] tch_ID = new String[10000];
		String[] day = new String[10000];
		String[] start_time = new String[10000];
		String[] end_time = new String[10000];
		
		Data_creation db_creation = new Data_creation();
		
		for (int i =0 ; i<10000;i++) {
			std_ID[i] = Integer.toString(2010000+i);
			std_name[i] = String.valueOf((char)(a+random.nextInt(25))) + 
					String.valueOf((char)(a+random.nextInt(25))) + 
					String.valueOf((char)(a+random.nextInt(25)));
			dept_name[i] = String.valueOf((char)(A+random.nextInt(25)))
					+ String.valueOf((char)(A+random.nextInt(25))) 
					+ String.valueOf((char)(A+random.nextInt(25)));
			
			course_id[i] = Integer.toString((int)(Math.random()*89999999)+10000000);
			title[i] = String.valueOf((char)(a+random.nextInt(25))) + 
					String.valueOf((char)(a+random.nextInt(25))) + 
					String.valueOf((char)(a+random.nextInt(25)));
			tch_name[i] = String.valueOf((char)(a+random.nextInt(25))) + 
					String.valueOf((char)(a+random.nextInt(25))) + 
					String.valueOf((char)(a+random.nextInt(25)));
			semester[i] = Integer.toString((int)(Math.random()*2)+1);
			grade[i] = Integer.toString((int)(Math.random()*4)+1);
			classroom[i] = Integer.toString(i);
			time_slot_id[i] = Integer.toString(10000+i);
			remaining_seat[i] = "1";
			credit[i] = Integer.toString((int)(Math.random()*3)+1);
//			like[i] = Integer.toString((int)((Math.random()*1)+0));
			
			tch_ID[i] = Integer.toString(10000+i);
			day[i] = days[(int)(Math.random()*6)+0];
			start_time[i] = start_times[i%10];
			end_time[i] = end_times[i%10];
			
		}
		
		db_creation.takes_DB(std_ID, course_id,title, semester, grade,remaining_seat,tch_name);
		db_creation.student_DB(std_ID, std_name, dept_name);
		db_creation.teaches_DB(tch_ID, course_id, title, semester, grade, tch_name, remaining_seat);
		db_creation.course_DB(course_id, title, semester, grade, classroom, time_slot_id,
				dept_name,credit,tch_name,remaining_seat);
		db_creation.time_slot_DB(time_slot_id, day, start_time,end_time);
		
		System.out.println("DB »ðÀÔ ¼º°ø");
	}
}

		

