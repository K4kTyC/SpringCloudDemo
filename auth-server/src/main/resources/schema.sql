CREATE TABLE IF NOT EXISTS `users` (
    `id`        BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    `username`  VARCHAR(50) NOT NULL,
    `password`  VARCHAR(500) NOT NULL
);
CREATE SEQUENCE IF NOT EXISTS users_sequence START 10 INCREMENT 1;

CREATE TABLE IF NOT EXISTS `roles` (
    `id`    BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    `name`  VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS `privileges` (
    `id`   BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS `users_roles` (
    `user_id`   BIGINT UNSIGNED NOT NULL,
    `role_id`   BIGINT UNSIGNED NOT NULL,

    CONSTRAINT `fk_users_roles_user` FOREIGN KEY (`user_id`) REFERENCES users (`id`),
    CONSTRAINT `fk_users_roles_role` FOREIGN KEY (`role_id`) REFERENCES roles (`id`)
);

CREATE TABLE IF NOT EXISTS `roles_privileges` (
    `role_id`       BIGINT UNSIGNED NOT NULL,
    `privilege_id`  BIGINT UNSIGNED NOT NULL,

    CONSTRAINT `fk_roles_privileges_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
    CONSTRAINT `fk_roles_privileges_privilege` FOREIGN KEY (`privilege_id`) REFERENCES `privileges` (`id`)
);

INSERT IGNORE INTO roles
VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT IGNORE INTO privileges
VALUES (1, 'roles.read'), (2, 'roles.write');

INSERT IGNORE INTO users
VALUES (1, 'admin', '{bcrypt}$2a$10$VeZuNeOVnSYbbORx./9S.eCly8178h5chkdFAEH6FYo6i3NHiczZK');

INSERT IGNORE INTO users_roles
VALUES (1, 2);

INSERT IGNORE INTO roles_privileges
VALUES (1, 1), (2, 1), (2, 2);