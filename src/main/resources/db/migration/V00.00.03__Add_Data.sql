INSERT INTO public.roles(name, decs)
	VALUES ('ROLE_ADMIN', 'ADMIN'),
('ROLE_MANAGER', 'MANAGER'),
('ROLE_USER', 'USER')
;

INSERT INTO public.persons(id, personal_number, first_name, last_name, birth_date, birth_place, blocked, created_at)
 VALUES(1, '01234567890', 'Test', 'Example', '1986-06-01', 'Sokhumi', false, now());

INSERT INTO public.users(id, email, person_id,  password, created_at, active)
 VALUES (1, 'test@example.com', 1, '$2y$12$YfDet3bMbaNvCFDq4d/F2u90NL/aYQu83NQPDNu6nWWqIWxnVcNz6', now(), true);


INSERT INTO public.user_roles(user_id, role_id)
	VALUES (1, 1);
