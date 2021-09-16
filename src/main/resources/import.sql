INSERT INTO usuarios (username, password) VALUES ('admin','$2a$10$HvpP9YHalfk/hnU0qSh/iek1zAu5iS8gIWvvVzou2XCJQxYm4Wjm2');

INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES ('ROLE_EMPLEADO');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 2);

