--------------------
--User generation --
--------------------
INSERT INTO app_authorization (id, permission) VALUES (1, 'VIEWER');
INSERT INTO app_authorization (id, permission) VALUES (2, 'EDITOR');
INSERT INTO app_authorization (id, permission) VALUES (3, 'USER_MANAGER');

INSERT INTO app_role (id, name) VALUES (1, 'ADMIN');
INSERT INTO app_role_authorization (role_id, authorization_id) VALUES (1, 1);
INSERT INTO app_role_authorization (role_id, authorization_id) VALUES (1, 2);
INSERT INTO app_role_authorization (role_id, authorization_id) VALUES (1, 3);

INSERT INTO app_role (id, name) VALUES (2, 'USER');
INSERT INTO app_role_authorization (role_id, authorization_id) VALUES (2, 1);

------------------------
--UserControllerTest
----------------------
INSERT INTO app_user (id, username, password, role_id) VALUES (1, 'admin', '$2a$10$dSHph1FnsWAIfmxC5jGDjOm.TKdl.NWbMGdpaTLxB/FmZgZQ6oWYe',1);	--admin,admin :this is used for test authentication
INSERT INTO app_user (id, username, password, role_id) VALUES (2, 'Gino', 'Balboa',1);
INSERT INTO app_user (id, username, password, role_id) VALUES (3, 'Mike', 'Bongiorno',1);

------------------------
--CustomerControllerTest
------------------------
insert into customer (age, email, name, id)  VALUES (18, 'young@mail.com', 'young signorino', 1);
insert into customer (age, email, name, id)  VALUES (45, 'alessandro.dottavio@gmail,com', 'alessandro dottavio', 2);
insert into customer (age, email, name, id)  VALUES (52, 'gino.pino@gmail.com', 'gino pino', 3);



