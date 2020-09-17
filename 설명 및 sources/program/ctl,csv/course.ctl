load data
infile '.\course.csv'
append
into table course
fields terminated by ','
(course_id,title,semester,grade,classroom,time_slot_id,dept_name,credit,tch_name,remaining_seat)
