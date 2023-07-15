DROP TABLE IF EXISTS trab05.montagem;
DROP TABLE IF EXISTS trab05.usuario;
DROP TABLE IF EXISTS trab05.peca;

CREATE TABLE trab05.usuario (
  id 		INT 		NOT NULL	AUTO_INCREMENT,
  nome 		VARCHAR(32) NOT NULL,
  email 	VARCHAR(32) NOT NULL	UNIQUE,
  senha 	VARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

INSERT INTO trab05.usuario(nome, email, senha)
VALUES('max', 'max@email.com', '22194');

INSERT INTO trab05.usuario(nome, email, senha)
VALUES('uff', 'uff@email.com', 'iduff');



CREATE TABLE trab05.peca (
  id 		INT 		NOT NULL	AUTO_INCREMENT,
  nome 		VARCHAR(32) NOT NULL,
  preco 	DECIMAL		NOT NULL,
  tipo	 	VARCHAR(32) NOT NULL,
  descricao	VARCHAR(32)	NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

INSERT INTO trab05.peca(nome, preco, tipo, descricao)
VALUES('amd ryzen 5 7600', 1200, 'processador', '6 cores, 12 threads, am5');

INSERT INTO trab05.peca(nome, preco, tipo, descricao)
VALUES('nvidia rtx 4070', 3900, 'placa de video', '12gb gddr6x');



CREATE TABLE trab05.montagem (
  id 					INT 		NOT NULL	AUTO_INCREMENT,
  nome					VARCHAR(32)	NOT NULL,
  gabinete_id			INT			NOT NULL,
  fonte_id				INT			NOT NULL,
  placa_mae_id			INT			NOT NULL,
  processador_id		INT			NOT NULL,
  placa_de_video_id		INT			NOT NULL,
  armazenamento_id		INT			NOT NULL,
  memoria_ram_id		INT			NOT NULL,
  cooler_processador_id	INT			NOT NULL,
  cooler_gabinete_id	INT			NOT NULL,
  usuario_id			INT			NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (gabinete_id) REFERENCES peca(id),
  FOREIGN KEY (fonte_id) REFERENCES peca(id),
  FOREIGN KEY (placa_mae_id) REFERENCES peca(id),
  FOREIGN KEY (processador_id) REFERENCES peca(id),
  FOREIGN KEY (placa_de_video_id) REFERENCES peca(id),
  FOREIGN KEY (armazenamento_id) REFERENCES peca(id),
  FOREIGN KEY (memoria_ram_id) REFERENCES peca(id),
  FOREIGN KEY (cooler_processador_id) REFERENCES peca(id),
  FOREIGN KEY (cooler_gabinete_id) REFERENCES peca(id),
  FOREIGN KEY (usuario_id) REFERENCES usuario(id)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

INSERT INTO trab05.montagem(nome, gabinete_id, fonte_id, placa_mae_id, processador_id, placa_de_video_id, armazenamento_id, memoria_ram_id, cooler_processador_id, cooler_gabinete_id, usuario_id)
VALUES("pc 1", 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

INSERT INTO trab05.montagem(nome, gabinete_id, fonte_id, placa_mae_id, processador_id, placa_de_video_id, armazenamento_id, memoria_ram_id, cooler_processador_id, cooler_gabinete_id, usuario_id)
VALUES("pc 2", 2, 2, 2, 2, 2, 2, 2, 2, 2, 2);