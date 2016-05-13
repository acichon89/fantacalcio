INSERT INTO clubs (id, created_date_time, updated_date_time, version, active, name) 
	values  ('aaa-bbb', CURDATE(), CURDATE(), 1, true, 'Juventus');
	
INSERT INTO players (id, created_date_time, updated_date_time, version, date_of_birth, full_name, position, club_id ) 
	values  ('aaa-bbb', CURDATE(), CURDATE(), 1, CURDATE(), 'Claudio Marchisio', 'MIDFIELDER', 'aaa-bbb');
