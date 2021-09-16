INSERT INTO roles
("role_name")
VALUES
('USER_ROLE'),
('MODERATOR_ROLE'),
('ADMIN_ROLE');

INSERT INTO users
("nickname", "password", "email", "first_name", "last_name", "role_id")
VALUES
('usuario1', 'usuario1', 'usuario1@gmail.com', 'Ivan', 'Tyrpak', (SELECT roles.id FROM roles WHERE role_name='ADMIN_ROLE')),
('usuario2', 'usuario2', 'usuario2@gmail.com', 'Juan', 'Perez', (SELECT roles.id FROM roles WHERE role_name='MODERATOR_ROLE')),
('usuario3', 'usuario3', 'usuario3@gmail.com', 'Andres', 'Fuegos', (SELECT roles.id FROM roles WHERE role_name='USER_ROLE')),
('usuario4', 'usuario4', 'usuario4@gmail.com', 'Leandro', 'Gutierrez', (SELECT roles.id FROM roles WHERE role_name='USER_ROLE')),
('usuario5', 'usuario5', 'usuario5@gmail.com', 'Pedro', 'Gomez', (SELECT roles.id FROM roles WHERE role_name='USER_ROLE'));

INSERT INTO events
("title", "description", "author_id", "created_at","last_updated")
VALUES
('Evento1', 'Descripcion evento 1', (SELECT users.id FROM users WHERE nickname='usuario1'), current_timestamp, current_timestamp),
('Evento2', 'Descripcion evento 2', (SELECT users.id FROM users WHERE nickname='usuario1'), current_timestamp, current_timestamp),
('Evento3', 'Descripcion evento 3', (SELECT users.id FROM users WHERE nickname='usuario1'), current_timestamp, current_timestamp),
('Evento4', 'Descripcion evento 4', (SELECT users.id FROM users WHERE nickname='usuario2'), current_timestamp, current_timestamp),
('Evento5', 'Descripcion evento 5', (SELECT users.id FROM users WHERE nickname='usuario3'), current_timestamp, current_timestamp);