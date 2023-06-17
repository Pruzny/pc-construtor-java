DROP TABLE IF EXISTS peca;
DROP TABLE IF EXISTS composicao;
DROP TABLE IF EXISTS montagem;
DROP TABLE IF EXISTS usuario;

CREATE TABLE peca (
  id 		INT 		NOT NULL	AUTO_INCREMENT,
  nome 		VARCHAR(32) NOT NULL,
  preco 	DECIMAL		NOT NULL,
  tipo 		VARCHAR(32) NOT NULL,
  descricao	VARCHAR(32)	NOT	NULL,
  versao	INT			DEFAULT 0,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

CREATE TABLE composicao (
  id			INT			NOT NULL	AUTO_INCREMENT,
  quantidade	VARCHAR(32) NOT NULL,
  versao		INT			DEFAULT 0,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

CREATE TABLE montagem (
  id    			INT	NOT NULL	AUTO_INCREMENT,
  gabinete			INT	NOT NULL,
  fonte				INT NOT NULL,
  placaMae 			INT	NOT NULL,
  processador		INT NOT NULL,
  placaDeVideo		INT	NOT	NULL,
  armazenamento 	INT NOT NULL,
  memoriasRam 		INT NOT NULL,
  coolerProcessador INT,
  coolers 			INT,
  versao			INT	DEFAULT 0,
  PRIMARY KEY (id),
  FOREIGN KEY (gabinete) REFERENCES peca(id),
  FOREIGN KEY (fonte) REFERENCES peca(id),
  FOREIGN KEY (placaMae) REFERENCES peca(id),
  FOREIGN KEY (processador) REFERENCES peca(id),
  FOREIGN KEY (placaDeVideo) REFERENCES peca(id),
  FOREIGN KEY (armazenamento) REFERENCES composicao(id),
  FOREIGN KEY (memoriasRam) REFERENCES composicao(id),
  FOREIGN KEY (coolerProcessador) REFERENCES peca(id),
  FOREIGN KEY (coolers) REFERENCES composicao(id)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

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

CREATE TABLE composicao_peca (
  id 			INT	NOT NULL	AUTO_INCREMENT,
  composicao_id	INT NOT NULL,
  peca_id 		INT NOT NULL,
  versao		INT	DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE (email)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

INSERT INTO peca(nome, preco, tipo, descricao)
VALUES('Intel i5 12400F', 979.90, 'processador', '6-core 12-thread LGA 1700');

INSERT INTO peca(nome, preco, tipo, descricao)
VALUES('AMD Ryzen 5 5600', 779.90, 'processador', '6-core 12-thread AM4');

INSERT INTO usuario(nome, email, senha)
VALUES('max', 'max@email.com', '22194');

INSERT INTO usuario(nome, email, senha)
VALUES('uff', 'uff@email.com', 'iduff');
