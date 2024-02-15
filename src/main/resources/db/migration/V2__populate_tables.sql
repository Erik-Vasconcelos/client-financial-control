INSERT INTO permission(description) VALUES
    ('ADMIN'),
    ('MANAGER'),
    ('COMMON_USER');

INSERT INTO public.client (user_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled, birth_date, first_name, gender, last_name, status, telephone) 
VALUES 
    ('john', '19bbf735b27066f2f145e602624e1b24a3fbc54cd5dfd3143fc5feea6bdee9e139ca7332d4806b9f', TRUE, TRUE, TRUE, TRUE, '1990-01-01', 'John', 1, 'Doe', 1, '1234567890'),
    ('alice', '75ec349c1b0ef4ee7b249d0b83ae4861853f3aa77bce8c4b15f28cd43c6424ab4f29df431831bb0d', TRUE, TRUE, TRUE, TRUE, '1985-03-15', 'Alice', 0, 'Smith', 2, '9876543210'),
    ('michael', '75ec349c1b0ef4ee7b249d0b83ae4861853f3aa77bce8c4b15f28cd43c6424ab4f29df431831bb0d', TRUE, TRUE, TRUE, TRUE, '1978-07-20', 'Michael', 1, 'Johnson', 0, '5556667777'),
    ('emma', '75ec349c1b0ef4ee7b249d0b83ae4861853f3aa77bce8c4b15f28cd43c6424ab4f29df431831bb0d', TRUE, TRUE, TRUE, TRUE, '1995-09-10', 'Emma', 0, 'Brown', 3, '3332221111'),
    ('david', '75ec349c1b0ef4ee7b249d0b83ae4861853f3aa77bce8c4b15f28cd43c6424ab4f29df431831bb0d', TRUE, TRUE, TRUE, TRUE, '1982-04-25', 'David', 1, 'Wilson', 4, '9998887777'),
    ('sophia', '75ec349c1b0ef4ee7b249d0b83ae4861853f3aa77bce8c4b15f28cd43c6424ab4f29df431831bb0d', TRUE, TRUE, TRUE, TRUE,'1998-12-05', 'Sophia', 0, 'Martinez', 1, '1112223333'),
    ('jessica', '75ec349c1b0ef4ee7b249d0b83ae4861853f3aa77bce8c4b15f28cd43c6424ab4f29df431831bb0d', TRUE, TRUE, TRUE, TRUE,'1970-08-30', 'Jessica', 0, 'Lee', 2, '4445556666'),
    ('matthew', '75ec349c1b0ef4ee7b249d0b83ae4861853f3aa77bce8c4b15f28cd43c6424ab4f29df431831bb0d', TRUE, TRUE, TRUE, TRUE,'1991-06-18', 'Matthew', 1, 'Taylor', 0, '7778889999'),
    ('emily', '75ec349c1b0ef4ee7b249d0b83ae4861853f3aa77bce8c4b15f28cd43c6424ab4f29df431831bb0d', TRUE, TRUE, TRUE, TRUE, '1987-02-14', 'Emily', 0, 'Anderson', 3, '6667778888'),
    ('daniel', '75ec349c1b0ef4ee7b249d0b83ae4861853f3aa77bce8c4b15f28cd43c6424ab4f29df431831bb0d', TRUE, TRUE, TRUE, TRUE,'1975-10-12', 'Daniel', 1, 'Thomas', 1, '2223334444');

INSERT INTO client_permission (id_client, id_permission) VALUES
	(1, 1),
	(1, 2),
	(2, 1),
	(3, 3),
	(4, 3),
	(5, 3),
	(6, 3),
	(7, 3),
	(8, 3),
	(9, 3),
	(10, 3);

INSERT INTO public.address (city, complement, neighborhood, number, postal_code, state, street, client_id)
VALUES 
    ('New York', 'Apt 101', 'Manhattan', 123, '10001', 10, 'Broadway St', 1),
    ('Los Angeles', 'Suite 202', 'Hollywood', 456, '90001', 5, 'Sunset Blvd', 2),
    ('Chicago', NULL, 'Downtown', 789, '60601', 13, 'Michigan Ave', 3),
    ('San Francisco', NULL, 'Mission District', 246, '94101', 5, 'Market St', 4),
    ('Houston', NULL, 'Midtown', 135, '77001', 15, 'Main St', 3),
    ('Miami', 'Unit 303', 'South Beach', 789, '33101', 2, 'Ocean Dr', 4),
    ('Seattle', NULL, 'Capitol Hill', 753, '98101', 23, 'Pike St', 7),
    ('Dallas', NULL, 'Uptown', 852, '75201', 18, 'Elm St', 2),
    ('Atlanta', NULL, 'Buckhead', 369, '30301', 11, 'Peachtree St', 9),
    ('Boston', NULL, 'Back Bay', 147, '02101', 21, 'Boylston St', 1);
