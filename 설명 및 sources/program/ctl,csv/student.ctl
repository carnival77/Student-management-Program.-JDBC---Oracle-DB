load data
infile '.\student.csv'
append
into table student
fields terminated by ','
(std_ID,std_name,dept_name)
