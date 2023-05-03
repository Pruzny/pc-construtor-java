DROP TABLE IF EXISTS usuario;

CREATE TABLE usuario (
  id 		INT 		NOT NULL	AUTO_INCREMENT,
  nome 		VARCHAR(32) NOT NULL,
  email 	VARCHAR(32) NOT NULL,
  senha 	VARCHAR(32) NOT NULL,
  versao	INT			DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE (email)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

INSERT INTO usuario(nome, email, senha)
VALUES('max', 'max@email.com', '22194');

INSERT INTO usuario(nome, email, senha)
VALUES('uff', 'uff@email.com', 'iduff');
