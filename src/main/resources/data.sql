INSERT INTO users
("nickname", "first_name", "last_name")
VALUES
('usuario1', 'Ivan', 'Tyrpak'),
('usuario2', 'Juan', 'Perez'),
('usuario3', 'Andres', 'Fuegos'),
('usuario4', 'Leandro', 'Gutierrez'),
('usuario5', 'Pedro', 'Gomez');

INSERT INTO events
("title", "description", "author_id", "created_at","last_updated")
VALUES
('Evento1', 'Descripcion evento 1', (SELECT users.id FROM users WHERE nickname='usuario1'), current_timestamp, current_timestamp),
('Evento2', 'Descripcion evento 2', (SELECT users.id FROM users WHERE nickname='usuario1'), current_timestamp, current_timestamp),
('Evento3', 'Descripcion evento 3', (SELECT users.id FROM users WHERE nickname='usuario1'), current_timestamp, current_timestamp),
('Evento4', 'Descripcion evento 4', (SELECT users.id FROM users WHERE nickname='usuario2'), current_timestamp, current_timestamp),
('Evento5', 'Descripcion evento 5', (SELECT users.id FROM users WHERE nickname='usuario3'), current_timestamp, current_timestamp);