--
-- Estrutura da tabela "pais"
--

DROP TABLE IF EXISTS pais;
CREATE TABLE pais (
  id int AUTO_INCREMENT PRIMARY KEY,
  nome varchar(60) DEFAULT NULL,
  nome_pt varchar(60) DEFAULT NULL,
  sigla varchar(2) DEFAULT NULL,
  bacen int DEFAULT NULL
);

--
-- Estrutura da tabela "estado"
--
DROP TABLE IF EXISTS estado;
CREATE TABLE estado (
  id int AUTO_INCREMENT PRIMARY KEY,
  nome varchar(75) DEFAULT NULL,
  uf varchar(2) DEFAULT NULL,
  ibge int DEFAULT NULL,
  pais int DEFAULT NULL,
  ddd varchar(50) DEFAULT NULL
);

  
--
-- Estrutura da tabela "cidade"
--
DROP TABLE IF EXISTS cidade;
CREATE TABLE cidade (
  id int AUTO_INCREMENT PRIMARY KEY,
  nome varchar(120) DEFAULT NULL,
  uf int DEFAULT NULL,
  ibge int DEFAULT NULL,
  lat_lon varchar(100) DEFAULT NULL,
  latitude varchar(50) DEFAULT NULL,
  longitude varchar(50) DEFAULT NULL
);