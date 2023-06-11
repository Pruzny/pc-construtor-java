DROP TABLE IF EXISTS trab02.Usuario;

CREATE TABLE trab02.Usuario (
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

INSERT INTO trab02.Usuario(nome, email, senha)
VALUES('max', 'max@email.com', '22194');

INSERT INTO trab02.Usuario(nome, email, senha)
VALUES('uff', 'uff@email.com', 'iduff');
