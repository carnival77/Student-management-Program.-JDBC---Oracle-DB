load data
infile '.\takes.csv'
append
into table takes
fields terminated by ','
(std_ID,course_id,title,semester,grade,remaining_seat,tch_name)
