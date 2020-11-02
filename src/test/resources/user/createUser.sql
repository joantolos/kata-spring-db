DROP TABLE IF EXISTS users;

CREATE TABLE users (
	username	VARCHAR(30) NOT NULL,
	password	VARCHAR(30) NOT NULL
);

INSERT INTO users (username, password) VALUES('luke', '123');
INSERT INTO users (username, password) VALUES('vader', '456');
INSERT INTO users (username, password) VALUES('yoda', '789');