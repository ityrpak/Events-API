INSERT INTO roles
("role_name")
VALUES
('ROLE_USER'),
('ROLE_MODERATOR'),
('ROLE_ADMIN');

INSERT INTO users
("nickname", "password", "email", "first_name", "last_name", "role_id", "enabled")
VALUES
('usuario1', '{bcrypt}$2a$05$/Q0aIC8sgwU0n.NLhdgBle.LAY7qw0N34KGEvwJ6cmLgOrF0oozRO',
 'usuario1@gmail.com', 'Ivan', 'Tyrpak', (SELECT roles.id FROM roles WHERE role_name='ROLE_ADMIN'), 1),
('usuario2', '{bcrypt}$2a$05$KINlpu/2HSCxMbHNLIsI7uHSLekU5NcM4G9/mW.hIQx4vzTQ8vvm6',
 'usuario2@gmail.com', 'Juan', 'Perez', (SELECT roles.id FROM roles WHERE role_name='ROLE_MODERATOR'), 1),
('usuario3', '{bcrypt}$2a$05$xtL9Nu81SlcMIYgJnTEZAOP/sTqID5XXoG2omSKe/NjfINzOJAbfG',
 'usuario3@gmail.com', 'Andres', 'Fuegos', (SELECT roles.id FROM roles WHERE role_name='ROLE_USER'), 1),
('usuario4', '{bcrypt}$2a$05$ca4mglDOpuRvzwKIUUO9JO70SEASbPQEtKkIhQWiNDg4JN6.GovBe',
 'usuario4@gmail.com', 'Leandro', 'Gutierrez', (SELECT roles.id FROM roles WHERE role_name='ROLE_USER'), 1),
('usuario5', '{bcrypt}$2a$05$DMfjuHeeSy1e/EQGs03UWuQ18a09qaS6vzZXrlX2B.4twZ5beivwu',
 'usuario5@gmail.com', 'Pedro', 'Gomez', (SELECT roles.id FROM roles WHERE role_name='ROLE_USER'), 1);

INSERT INTO events
("title", "description", "author_id", "created_at","last_updated")
VALUES
('Evento1', 'Descripcion evento 1', (SELECT users.id FROM users WHERE nickname='usuario1'), current_timestamp, current_timestamp),
('Evento2', 'Descripcion evento 2', (SELECT users.id FROM users WHERE nickname='usuario1'), current_timestamp, current_timestamp),
('Evento3', 'Descripcion evento 3', (SELECT users.id FROM users WHERE nickname='usuario1'), current_timestamp, current_timestamp),
('Evento4', 'Descripcion evento 4', (SELECT users.id FROM users WHERE nickname='usuario2'), current_timestamp, current_timestamp),
('Evento5', 'Descripcion evento 5', (SELECT users.id FROM users WHERE nickname='usuario3'), current_timestamp, current_timestamp);