CREATE TABLE pessoa (
  id_pessoa int(10) NOT NULL AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  cpf varchar(11) NOT NULL,
  data_nascimento date NOT NULL,
  PRIMARY KEY (id_pessoa),
  UNIQUE KEY UK_cpf (cpf)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE conta (
  id_conta int(10) NOT NULL AUTO_INCREMENT,
  id_pessoa int(10) NOT NULL,
  saldo numeric(18,2) NOT NULL DEFAULT 0,
  limite_saque_diario numeric(18,2) NOT NULL DEFAULT 0,
  ativa boolean,
  data_criacao timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_conta),
  FOREIGN KEY (id_pessoa)
        REFERENCES pessoa(id_pessoa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE transacao (
  id_transacao int(10) NOT NULL AUTO_INCREMENT,
  id_conta int(10) NOT NULL,
  valor numeric(18,2) NOT NULL DEFAULT 0,
  tipo_operacao varchar(1) NOT NULL,
  data_transacao timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_transacao),
  FOREIGN KEY (id_conta)
        REFERENCES conta(id_conta)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;