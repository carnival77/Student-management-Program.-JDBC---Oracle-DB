load data
infile '.\time_slot.csv'
append
into table time_slot
fields terminated by ','
(time_slot_id,day,start_time,end_time)
