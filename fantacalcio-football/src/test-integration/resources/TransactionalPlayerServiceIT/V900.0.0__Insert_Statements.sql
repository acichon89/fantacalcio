INSERT INTO clubs (id, created_date_time, updated_date_time, version, active, name) 
	values  ('aaa-bbb', CURDATE(), CURDATE(), 1, true, 'Juventus');
INSERT INTO clubs (id, created_date_time, updated_date_time, version, active, name) 
	values  ('aaa-ccc', CURDATE(), CURDATE(), 1, true, 'AC Milan');

INSERT INTO players (id, created_date_time, updated_date_time, version, full_name, position, date_of_birth, club_id)
	values ('123-999', CURDATE(), CURDATE(), 1, 'Gianluigi Buffon', 'GOALKEEPER', CURDATE(), 'aaa-bbb');