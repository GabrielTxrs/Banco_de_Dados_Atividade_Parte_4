use parte4;

DROP TABLE TB_ITEM;
DROP TABLE TB_PEDIDO;
DROP TABLE TB_CLIENTE;

SELECT * FROM TB_CLIENTE;
SELECT * FROM TB_PEDIDO;
SELECT * FROM TB_ITEM;

CREATE TABLE TB_CLIENTE (
	ID_CLIENTE INT NOT NULL UNIQUE,
	NOME VARCHAR(30),
	SOBRENOME VARCHAR(50),
	EMAIL VARCHAR(30),
	IDADE INT,
PRIMARY KEY (ID_CLIENTE)
);

CREATE TABLE TB_PEDIDO (
ID_PEDIDO INT NOT NULL UNIQUE,
ID_CLIENTE INT NOT NULL,
DATA_PEDIDO DATE NOT NULL,
PRECO_TOTAL FLOAT,

PRIMARY KEY (ID_PEDIDO),
FOREIGN KEY (ID_CLIENTE) REFERENCES TB_CLIENTE (ID_CLIENTE)
);

CREATE TABLE TB_ITEM (
	ID_ITEM INT NOT NULL UNIQUE,
	ID_PEDIDO INT,
	NOME VARCHAR(50),
	QUANTIDADE INT,
	PRECO FLOAT,

PRIMARY KEY (ID_ITEM),
FOREIGN KEY (ID_PEDIDO) REFERENCES TB_PEDIDO (ID_PEDIDO)
);

CREATE TABLE tb_usuario (
  ID_USUARIO int NOT NULL AUTO_INCREMENT,
  PRIMEIRO_NOME varchar(30) NOT NULL,
  SOBRENOME varchar(60) DEFAULT NULL,
  DATA_NASCIMENTO date DEFAULT NULL,
  IDADE smallint DEFAULT NULL,
  TELEFONE varchar(20) DEFAULT NULL,
  SENHA varchar(100) NOT NULL,
  PRIMARY KEY (ID_USUARIO)
);


