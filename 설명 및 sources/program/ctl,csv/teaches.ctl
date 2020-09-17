load data
infile '.\teaches.csv'
append
into table teaches
fields terminated by ','
(tch_ID,course_id,title,semester,grade,tch_name,remaining_seat)
