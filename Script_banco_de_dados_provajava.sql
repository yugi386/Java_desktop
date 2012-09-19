-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: Set 19, 2012 as 12:17 PM
-- Versão do Servidor: 5.5.8
-- Versão do PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `provajava`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cargos`
--

CREATE TABLE IF NOT EXISTS `cargos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `cargos`
--

INSERT INTO `cargos` (`id`, `descricao`) VALUES
(1, 'administrador'),
(2, 'apicultor'),
(3, 'Médico');

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) DEFAULT NULL,
  `telefone` varchar(15) NOT NULL,
  `limiteCredito` decimal(10,2) DEFAULT NULL,
  `dataultimaCompra` date DEFAULT NULL,
  `rg` varchar(12) DEFAULT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `ativo` smallint(6) DEFAULT NULL,
  `observacao` text,
  `rua` varchar(40) DEFAULT NULL,
  `bairro` varchar(40) DEFAULT NULL,
  `cidade` varchar(40) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_usuarios_estado_id` (`estado_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`id`, `nome`, `telefone`, `limiteCredito`, `dataultimaCompra`, `rg`, `cpf`, `ativo`, `observacao`, `rua`, `bairro`, `cidade`, `estado_id`) VALUES
(1, 'Marisa', '3333-4444', '800.00', '2010-02-01', '12345', '67890', 1, 'todas', 'da lua', 'centro', 'são josé dos campos', 25),
(2, 'maria', '123456', '1.00', '2010-01-02', '123', '200400', 0, 'novo testes', 'acacias', 'novo horizonte', 'caçapava', 25);

-- --------------------------------------------------------

--
-- Estrutura da tabela `contatos_fornecedores`
--

CREATE TABLE IF NOT EXISTS `contatos_fornecedores` (
  `fornecedores_id` int(11) NOT NULL,
  `contatos_id` int(11) NOT NULL,
  PRIMARY KEY (`fornecedores_id`,`contatos_id`),
  KEY `FK_contatos_fornecedores_contatos_id` (`contatos_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `contatos_fornecedores`
--

INSERT INTO `contatos_fornecedores` (`fornecedores_id`, `contatos_id`) VALUES
(1, 2),
(1, 6),
(2, 1),
(4, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `departamentos`
--

CREATE TABLE IF NOT EXISTS `departamentos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Extraindo dados da tabela `departamentos`
--

INSERT INTO `departamentos` (`id`, `descricao`) VALUES
(3, 'Administração'),
(6, 'Recursos Humanos');

-- --------------------------------------------------------

--
-- Estrutura da tabela `departamentos_usuarios`
--

CREATE TABLE IF NOT EXISTS `departamentos_usuarios` (
  `usuarios_id` int(11) NOT NULL,
  `departamentos_id` int(11) NOT NULL,
  PRIMARY KEY (`usuarios_id`,`departamentos_id`),
  KEY `FK_departamentos_usuarios_departamentos_id` (`departamentos_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `departamentos_usuarios`
--

INSERT INTO `departamentos_usuarios` (`usuarios_id`, `departamentos_id`) VALUES
(1, 6),
(2, 3),
(5, 6),
(7, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `estados`
--

CREATE TABLE IF NOT EXISTS `estados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Extraindo dados da tabela `estados`
--

INSERT INTO `estados` (`id`, `descricao`) VALUES
(1, 'Acre'),
(2, 'Alagoas'),
(3, 'Amapá'),
(4, 'Amazonas'),
(5, 'Bahia'),
(6, 'Ceará'),
(7, 'Distrito Federal'),
(8, 'Espírito Santo'),
(9, 'Goiás'),
(10, 'Maranhão'),
(11, 'Mato Grosso'),
(12, 'Mato Grosso do Sul'),
(13, 'Minas Gerais'),
(14, 'Pará'),
(15, 'Paraíba'),
(16, 'Paraná'),
(17, 'Pernambuco'),
(18, 'Piauí'),
(19, 'Rio de Janeiro'),
(20, 'Rio Grande do Norte'),
(21, 'Rio Grande do Sul'),
(22, 'Rondônia'),
(23, 'Roraima'),
(24, 'Santa Catarina'),
(25, 'São Paulo'),
(26, 'Sergipe'),
(27, 'Tocantins');

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedores`
--

CREATE TABLE IF NOT EXISTS `fornecedores` (
  `id` tinyint(1) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) DEFAULT NULL,
  `telefone` varchar(15) NOT NULL,
  `valorComprado` decimal(10,2) DEFAULT NULL,
  `dataultimaCompra` date DEFAULT NULL,
  `ie` varchar(12) DEFAULT NULL,
  `cnpj` varchar(15) DEFAULT NULL,
  `ativo` int(6) DEFAULT NULL,
  `observacao` text,
  `rua` varchar(40) DEFAULT NULL,
  `bairro` varchar(40) DEFAULT NULL,
  `cidade` varchar(40) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_usuarios_estado_id` (`estado_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `fornecedores`
--

INSERT INTO `fornecedores` (`id`, `nome`, `telefone`, `valorComprado`, `dataultimaCompra`, `ie`, `cnpj`, `ativo`, `observacao`, `rua`, `bairro`, `cidade`, `estado_id`) VALUES
(1, 'Rosana verissimo', '3323-1020', '125.00', '2002-01-01', '0987654321', '1550', 0, 'novo fornecedor', 'alameda 2', 'vista nova', 'minduri', 13),
(2, 'Miguel', '3323-2010', '100.00', '2013-02-28', '123456', '09', 1, 'gerais', 'rua das orquideas', 'centro', 'jacarei', 25);

-- --------------------------------------------------------

--
-- Estrutura da tabela `representantes_clientes`
--

CREATE TABLE IF NOT EXISTS `representantes_clientes` (
  `clientes_id` int(11) NOT NULL,
  `representantes_id` int(11) NOT NULL,
  PRIMARY KEY (`clientes_id`,`representantes_id`),
  KEY `FK_representantes_clientes_representantes_id` (`representantes_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `representantes_clientes`
--

INSERT INTO `representantes_clientes` (`clientes_id`, `representantes_id`) VALUES
(0, 1),
(0, 2),
(0, 5),
(0, 6),
(1, 2),
(2, 1),
(2, 2),
(2, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) DEFAULT NULL,
  `login` varchar(40) NOT NULL,
  `senha` varchar(40) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `observacao` text,
  `ativo` smallint(6) DEFAULT NULL,
  `cargo_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_usuarios_cargo_id` (`cargo_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `nome`, `login`, `senha`, `dataNascimento`, `sexo`, `salario`, `email`, `observacao`, `ativo`, `cargo_id`) VALUES
(1, 'Mario', 'junior', '111', '1977-02-26', 'M', '144.00', 'email@123.com.br', 'usuário novo', 0, 1),
(2, 'Mireia', '123', '111', '2012-01-01', 'F', '600.00', 'email@123.com.br', 'observacoes gerais', 0, 1),
(6, 'teste', '111', '111', '2000-01-02', 'M', '1200.00', 'teste@teste.com.br', 'nenhuma', 1, 2);
