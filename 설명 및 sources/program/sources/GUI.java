package sources;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame{
	
	JScrollPane scroll;
	
	JTable table;
	Vector data, col;
	DefaultTableModel default_table;
	
	BorderLayout bdlayout = new BorderLayout();
	GridLayout gdlayout = new GridLayout(7,1);
	FlowLayout fllayout = new FlowLayout();
	GridLayout gdlayout1 = new GridLayout(1,7);
	
	Panel pn_tools = new Panel();
	Panel pn_total = new Panel();
	Panel[] pn_input = new Panel[2];
	
	Button[] bt = new Button[7];
	TextField[] tf = new TextField[8];
	
	String[] lecture_conditions = {"course_id","title","semester","grade","classroom",
			"time_slot_id","dept_name","credit","tch_name","remaining_seat"};
	
	String[] std_conditions = {"std_ID","std_name","dept_name"};
	
	JComboBox[] cb = new JComboBox[6];
	
	String item1=null;
	String item2=null;
	String item3=null;
	String item4=null;
	String item5=null;
	
	int inx0=0;
	int inx1=0;
	int inx2=0;
	int inx3=0;
	int inx4=0;
	int inx5=0;
	
	String[] sql;
	
	Transaction transaction = new Transaction();
	
	public GUI() {
		super("학사 생활 도우미");
		setSize(1000,700);
		setLayout(bdlayout);
	}
	
	public void show_GUI(Connection conn, java.sql.Statement st) {
		
		pn_total.setLayout(new GridLayout(3,1));
		
		pn_input[0] = new Panel();
		pn_input[1] = new Panel();
		pn_input[0].setLayout(gdlayout1);
		pn_input[1].setLayout(gdlayout1);
		pn_tools.setLayout(gdlayout1);
		
		bt[0] = new Button("강의 검색");
		bt[1] = new Button("학생 검색");
		bt[2] = new Button("수강 신청");
		bt[3] = new Button("강의 변경");
		bt[4] = new Button("학생 변경");
		bt[5] = new Button("삭제");
		bt[6] = new Button("학생 등록");
		
		for (int i=0;i<7;i++) {
			bt[i].setSize(50,50);
			pn_tools.add(bt[i]);
		}
		
		// 강의 input
		for (int i=0;i<3;i++) {
			tf[i]=new TextField(10);
			cb[i] = new JComboBox(lecture_conditions);
			pn_input[0].add(cb[i]);
			pn_input[0].add(tf[i]);
		}
		tf[3]=new TextField(10);
		pn_input[0].add(tf[3]);
		
		pn_total.add(pn_input[0]);

		// 학생 input
		for (int i=4;i<7;i++) {
			tf[i]=new TextField(10);
			cb[i-1] = new JComboBox(std_conditions);
			pn_input[1].add(cb[i-1]);
			pn_input[1].add(tf[i]);
		}
		tf[7]=new TextField(10);
		pn_input[1].add(tf[7]);
		
		pn_total.add(pn_input[1]);
		
		pn_total.add(pn_tools);
		
		add(pn_total,BorderLayout.NORTH);
		
		String[] basic_sql = new String[3];
		
		String show_all_sql = "select * from course order by course_id";
		
		basic_sql[0] = "0";
		
		basic_sql[2] = show_all_sql;
		
		default_table = Get_default_table_model(basic_sql,0);

		default_table = Get_Table_model(default_table);
		
		setVisible(true);
		
		// 버튼 이벤트
		
		// T1. 강의 검색
		
		cb[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent cb_e) {
				// TODO Auto-generated method stub
				inx0 = cb[0].getSelectedIndex();
			}
		});
		
		cb[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent cb_e) {
				// TODO Auto-generated method stub
				inx1 = cb[1].getSelectedIndex();
			}
		});
		
		cb[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent cb_e) {
				// TODO Auto-generated method stub
				inx2 = cb[2].getSelectedIndex();
			}
		});
		
		bt[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				item1 = tf[0].getText();
				item2 = tf[1].getText();
				item3 = tf[2].getText();
				sql = transaction.T1(inx0,item1,inx1,item2,inx2,item3);
				
				DMT_refresh(sql,0);
			}
		});
		
		// T2. 학생 검색
		
		cb[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent cb_e) {
				// TODO Auto-generated method stub
				inx3 = cb[3].getSelectedIndex();
			}
		});
		
		cb[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent cb_e) {
				// TODO Auto-generated method stub
				inx4 = cb[4].getSelectedIndex();
			}
		});
		
		cb[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent cb_e) {
				// TODO Auto-generated method stub
				inx5 = cb[5].getSelectedIndex();
			}
		});
		
		bt[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				item1 = tf[4].getText();
				item2 = tf[5].getText();
				item3 = tf[6].getText();
				sql = transaction.T2(inx3,item1,inx4,item2,inx5,item3);
				
				DMT_refresh(sql,1);
			}
		});
		
		// T3, T4. 삭제
		
		bt[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				item1 = tf[0].getText();
				item2 = tf[4].getText();
				if (!item1.equals("")) {
					sql = transaction.T3(inx1,item1);
					
					DMT_refresh(sql,0);
				}
				else if(!item2.equals("")){
					sql = transaction.T4(inx3,item2);
					
					DMT_refresh(sql,1);
				}
			}
		});
		
		// T5,T9   수강 신청, 수강 신청 취소
		
		bt[2].addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				item5 = tf[7].getText();
				if(!item5.equals("")) {
//					sql = transaction.T5(item5);
				
					DMT_refresh(sql,0);
				}
				else  {
					item1 = tf[0].getText();
					item2 = tf[1].getText();
					item3 = tf[2].getText();
//					sql = transaction.T9(inx0,item1,inx1,item2,inx2,item3);
					
					DMT_refresh(sql,0);
				}
			}
		});
		
		// T6. 학생 등록
		
		bt[6].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				item1 = tf[4].getText();
				item2 = tf[5].getText();
				item3 = tf[6].getText();
				sql = transaction.T6(inx3,item1,inx4,item2,inx5,item3);
				
				DMT_refresh(sql,1);
			}
		});
		
		// T7. 강의 변경
		
		bt[3].addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				item1 = tf[0].getText();
				item2 = tf[1].getText();
				item3 = tf[2].getText();
				item4 = tf[3].getText();
				sql = transaction.T7(inx0,item1,inx1,item2,inx2,item3,item4);
				
				DMT_refresh(sql,0);
			}
		});
		
		// T8. BE 혼합. 학생 변경
		
		bt[4].addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				item1 = tf[4].getText();
				item2 = tf[5].getText();
				item3 = tf[6].getText();
				item4 = tf[7].getText();
				sql = transaction.T8(inx3,item1,inx4,item2,inx5,
						item3,item4);
				
				DMT_refresh(sql,1);
			}
		});
		
		
	}
	
	public DefaultTableModel Get_Table_model(DefaultTableModel dt) {
	
		Connection conn=null;
		conn = DBConnect.getConnection();
		
		table = new JTable(dt);
		scroll = new JScrollPane(table);
		
		scroll.setBounds(415,10,770,250);
		
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	    final JButton next = new JButton("next");
	    final JButton prev = new JButton("prev");
	    
	    ActionListener al = new ActionListener(){
	        public void actionPerformed(ActionEvent e) {
	            Rectangle rect = scroll.getVisibleRect();
	            JScrollBar  bar = scroll.getVerticalScrollBar();
	            int blockIncr = scroll.getViewport().getViewRect().height;
	            if (e.getSource() == next) {
	                bar.setValue(bar.getValue() + blockIncr);
	            } else if (e.getSource() == prev) {
	                bar.setValue(bar.getValue() - blockIncr);
	            }
	            scroll.scrollRectToVisible(rect);
	        }
	    };

	    next.addActionListener(al);
	    prev.addActionListener(al);

	    JPanel panel = new JPanel(new BorderLayout());
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.add(prev);
	    buttonPanel.add(next);
	    panel.add(buttonPanel, BorderLayout.NORTH);
	    panel.add(scroll, BorderLayout.CENTER);
	    add(panel);
		
		return dt;
	}
	
	public Vector Get_row_list(String[] sql,int dec) {
		
		Connection conn = DBConnect.getConnection();
		Vector data = new Vector();
		ResultSet rs = null;
		PreparedStatement ps=null;
		
		
//		int item_num = Integer.parseInt(sql[1]);
//		String get_sql = sql[2];

		try {
			int sql_dec = Integer.parseInt(sql[0]);
			
			if (dec == 0) {
				if(sql_dec == 0) {
					String get_sql = sql[2];
					System.out.println(get_sql);
					ps =conn.prepareStatement(get_sql);
					rs = ps.executeQuery();
					
					while(rs.next()) {
						String course_id = rs.getString(1);
						String title = rs.getString(2);
						String semester = rs.getString(3);
						String grade = rs.getString(4);
						String classroom = rs.getString(5);
						String time_slot_id = rs.getString(6);
						String dept_name = rs.getString(7);
						String credit = rs.getString(8);
						String tch_name = rs.getString(9);
						String remaining_seat = rs.getString(10);
	//					String like = rs.getString(10);
						
						Vector row = new Vector();
						row.add(course_id);
						row.add(title);
						row.add(semester);
						row.add(grade);
						row.add(classroom);
						row.add(time_slot_id);
						row.add(dept_name);
						row.add(credit);
						row.add(tch_name);
						row.add(remaining_seat);
	//					row.add(like);
						
						data.add(row);
					}
				}
				else {
					int item_num = Integer.parseInt(sql[1]);
					String get_sql = sql[2];
					
	//				if (dec == 0) {
						ps =conn.prepareStatement(get_sql);
	//					System.out.println(get_sql);
						
						for(int i =0;i<item_num;i++ ) {
	//						System.out.println(sql[3+i]);
		//					ps =conn.prepareStatement(get_sql);
							
							ps.setString(i+1,sql[3+i]);
							
	//						System.out.println(sql[3+i]);
		//				rs = ps.executeQuery();
						}
						rs = ps.executeQuery();
						
						while(rs.next()) {
							String course_id = rs.getString(1);
							String title = rs.getString(2);
							String semester = rs.getString(3);
							String grade = rs.getString(4);
							String classroom = rs.getString(5);
							String time_slot_id = rs.getString(6);
							String dept_name = rs.getString(7);
							String credit = rs.getString(8);
							String tch_name = rs.getString(9);
							String remaining_seat = rs.getString(10);
		//					String like = rs.getString(10);
							
							Vector row = new Vector();
							row.add(course_id);
							row.add(title);
							row.add(semester);
							row.add(grade);
							row.add(classroom);
							row.add(time_slot_id);
							row.add(dept_name);
							row.add(credit);
							row.add(tch_name);
							row.add(remaining_seat);
		//					row.add(like);
							
							data.add(row);
						}
	//				}
				}
			}
			else {
				if(sql_dec == 0) {
					String get_sql = sql[2];
					System.out.println(get_sql);
					ps =conn.prepareStatement(get_sql);
					rs = ps.executeQuery();
					
					while(rs.next()) {
						String std_ID = rs.getString(1);
						String std_name = rs.getString(2);
						String dept_name = rs.getString(3);
						
						Vector row = new Vector();
						row.add(std_ID);
						row.add(std_name);
						row.add(dept_name);
						
						data.add(row);
					}
				}
				else {
					int item_num = Integer.parseInt(sql[1]);
					String get_sql = sql[2];
					
	//				if (dec == 0) {
						ps =conn.prepareStatement(get_sql);
	//					System.out.println(get_sql);
						
						for(int i =0;i<item_num;i++ ) {
	//						System.out.println(sql[3+i]);
		//					ps =conn.prepareStatement(get_sql);
							
							ps.setString(i+1,sql[3+i]);
							
	//						System.out.println(sql[3+i]);
		//				rs = ps.executeQuery();
						}
						rs = ps.executeQuery();
						
						while(rs.next()) {
							String std_ID = rs.getString(1);
							String std_name = rs.getString(2);
							String dept_name = rs.getString(3);
							
							Vector row = new Vector();
							row.add(std_ID);
							row.add(std_name);
							row.add(dept_name);
							
							data.add(row);
						}
	//				}
				}
				
			}
//			else {
//				ps =conn.prepareStatement(sql) ;
//				rs = ps.executeQuery();
//				
//				while(rs.next()) {
//					String std_ID = rs.getString(1);
//					String std_name = rs.getString(2);
//					String dept_name = rs.getString(3);
//					
//					Vector row = new Vector();
//					row.add(std_ID);
//					row.add(std_name);
//					row.add(dept_name);
//					
//					data.add(row);
//				}
//			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public Vector Get_col_list(int dec) {
		
		col = new Vector();
		
		if(dec == 0) {
			col.add("course_id");
			col.add("title");
			col.add("semester");
			col.add("grade");
			col.add("classroom");
			col.add("time_slot_id");
			col.add("dept_name");
			col.add("credit");
			col.add("tch_name");
			col.add("remaining_seat");
		}
		else {
			col.add("std_ID");
			col.add("std_name");
			col.add("dept_name");
		}
		
		return col;
	}
	
	public DefaultTableModel Get_default_table_model(String[] sql,int dec) {
		
		Vector col = Get_col_list(dec);
		
		Vector row = Get_row_list(sql,dec);

		DefaultTableModel dtm = new DefaultTableModel(row,col);
		return dtm;
	}
	
	public void DMT_refresh(String[] sql, int dec) {
		DefaultTableModel dt = Get_default_table_model((String[])sql,(int)dec);
//		table = new JTable();
		table.setModel((DefaultTableModel)dt);
		default_table.fireTableDataChanged();
	}
}
