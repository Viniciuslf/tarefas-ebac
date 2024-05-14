CREATE TABLE estado(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	sigla VARCHAR(2) NOT NULL UNIQUE
);

CREATE TABLE cidade(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	estado_id INTEGER NOT NULL,
	CONSTRAINT fk_estado FOREIGN KEY (estado_id) REFERENCES estado(id)
);

CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	data_cadastro TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
	cpf VARCHAR(11) NOT NULL UNIQUE,
	telefone VARCHAR(11) NOT NULL UNIQUE,
	cidade_id INTEGER,
	CONSTRAINT fk_cidade FOREIGN KEY (cidade_id) REFERENCES cidade(id)
);

CREATE TABLE venda(
	id SERIAL PRIMARY KEY,
	cliente_id INTEGER NOT NULL,
	desconto FLOAT NOT NULL DEFAULT 0.0,
	total FLOAT NOT NULL,
	data_venda TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE produto(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	descricao TEXT NOT NULL,
	estoque FLOAT NOT NULL, 
	unidade VARCHAR(3)
);

CREATE TABLE produto_venda(
	id SERIAL PRIMARY KEY,
	venda_id INTEGER,
	produto_id INTEGER,
	CONSTRAINT fk_venda_produto FOREIGN KEY (venda_id) REFERENCES venda(id),
	CONSTRAINT fk_produto_venda FOREIGN KEY (produto_id) REFERENCES produto(id)
);

INSERT INTO estado (nome, sigla)
VALUES ('Acre', 'AC'), 
('Alagoas', 'AL'), 
('Amapá', 'AP'), 
('Amazonas', 'AM'), 
('Bahia', 'BA'), 
('Ceará', 'CE'), 
('Distrito Federal', 'DF'), 
('Espírito Santo', 'ES'), 
('Goiás', 'GO'), 
('Maranhão', 'MA'), 
('Mato Grosso', 'MT'), 
('Mato Grosso do Sul', 'MS'), 
('Minas Gerais', 'MG'), 
('Pará', 'PA'), ('Paraíba', 'PB'), 
('Paraná', 'PR'), 
('Pernambuco', 'PE'), 
('Piauí', 'PI'), 
('Rio de Janeiro', 'RJ'), 
('Rio Grande do Norte', 'RN'), 
('Rio Grande do Sul', 'RS'), 
('Rondônia', 'RO'), 
('Roraima', 'RR'), 
('Santa Catarina', 'SC'),
('São Paulo', 'SP'), 
('Sergipe', 'SE'), 
('Tocantins', 'TO');

INSERT INTO cidade(nome, estado_id)
VALUES ('Catalão', 9),
('Ouvidor', 9),
('Goiandira', 9),
('Uberlândia', 13),
('Cosmópolis', 25),
('Guarulhos', 25),
('Recanto', 7),
('Taguatinga', 7),
('Rondonópolis', 11);

INSERT INTO cliente (nome, cpf, telefone, cidade_id)
VALUES ('Vinicius L', '70436227190', '64992125546', 1),
('Dapheny Gonalves', '57161807162', '64998071654', 7),
('Adilson Carlos', '76276480192', '61823184521', 2),
('Rubnag Vieira', '89176452392', '77647726774', 4),
('Marcos Silva', '82372323272', '19122382342', 6),
('Clotilde Novaes', '82765791812', '12921186429', 5);

ALTER TABLE cliente
RENAME COLUMN nome TO nome_cliente;

UPDATE cliente
SET nome_cliente = 'Dapheny Gonçalves'
WHERE id=2;

UPDATE cliente
SET nome_cliente = 'Vinicius'
WHERE id=1;

ALTER TABLE cliente
RENAME COLUMN nome_cliente TO nome;

INSERT INTO produto (nome, descricao, estoque, unidade)
VALUES ('Mouse', 'Mouse perfeito para devs, chega de dor no pulso!', 50, 'und'),
('Notebook', 'Feito para desenvolvedores fanáticos por desenvolvimento. Chega de pomodoro! O negócio é trampar..', 10, 'und'),
('Coca-Cola', 'Acompanha café se você apresentar sua carteirinha de desenvolvedor', 1000.2, 'ml');

INSERT INTO venda (cliente_id, desconto, total)
VALUES (1, 10.3, 255.9),
(1, 100.1, 30.0),
(4, 1.0, 99.9),
(2, 10.2, 99.0),
(1, 400.0, 399.9),
(1, 10.0, 1239.8),
(4, 49.0, 1023.8);

SELECT * FROM venda;

INSERT INTO produto_venda(venda_id, produto_id)
VALUES(1, 2),
(2, 1),
(3, 3),
(4, 2),
(5, 3),
(6, 2)
(7, 3);

SELECT 
	c.nome as nome_cliente,
	ci.nome as cidade_nome,
	es.nome as estado_nome,
	p.nome as produto_nome,
	v.total as total_venda,
	v.data_venda as data_venda
FROM cliente as c 
	JOIN cidade as ci ON c.cidade_id = ci.id
	JOIN estado as es ON ci.estado_id = es.id
	JOIN venda as v ON v.cliente_id = c.id
	JOIN produto_venda as pv ON pv.venda_id = v.id
	JOIN produto as p ON pv.produto_id = p.id
ORDER BY c.nome, ci.nome, es.nome, p.nome, v.total, v.data_venda;

