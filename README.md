# DSW03

Grupo:
- Isabella Costa Pires, 726540


SGBD: MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/turismo
spring.datasource.username=root
spring.datasource.password=ROOT

# Funcionalidades AA-3

REST API -- CRUD 1
 de clientes
Cria um novo cliente [Create - CRUD] Feito
POST http://localhost:8080/clientes

Retorna a lista de clientes [Read - CRUD] Feito
GET http://localhost:8080/clientes

Retorna o cliente de id = {id} [Read - CRUD] Feito
GET http://localhost:8080/clientes/{id}

Atualiza o cliente de id = {id} [Update - CRUD] Feito
PUT http://localhost:8080/clientes/{id}

Remove o cliente de id = {id} [Delete - CRUD] Feito
DELETE http://localhost:8080/clientes/{id}


REST API -- CRUD de agências de turismo

Cria uma nova agência de turismo [Create - CRUD] Feito
POST http://localhost:8080/agencias
Exemplo: de JSON

Retorna a lista de agências de turismo [Read - CRUD] Feito
GET http://localhost:8080/agencias

[
    {
        "id": 2,
        "usuario": {
            "id": 2,
            "nome": "Admin",
            "email": "admin@email.com",
            "password": "$2a$10$nZu9j3unfiWMRT4.Orql0.8T9AV2MpV9opnYBfv1.TbcXCw68CnnG",
            "role": "ROLE_ADMIN"
        },
        "cnpj": "asd",
        "nome": "Admin",
        "descricao": "asd"
    },
    {
        "id": 3,
        "usuario": {
            "id": 4,
            "nome": "Agencia",
            "email": "agencia@email.com",
            "password": "$2a$10$fMx89lV.Np4g7kZH8U7MYuN2QQLf5NPsNrqO6yDzbYetajSTVitBi",
            "role": "ROLE_Agencia"
        },
        "cnpj": "123123",
        "nome": "Agencia",
        "descricao": null
    }
]

Retorna a agência de turismo de id = {id} [Read - CRUD] Feito
GET http://localhost:8080/agencias/{id}

Atualiza a agência de turismo de id = {id} [Update - CRUD] Feito
PUT http://localhost:8080/agencias/{id}


Remove a agência de turismo de id = {id} [Delete - CRUD] Feito
DELETE http://localhost:8080/agencias/{id}


CRUD: Create, Read, Update & Delete. ↩
REST API -- Retorna a lista de pacotes turísticos [Read - CRUD] Feito
GET http://localhost:8080/pacotes
[
    {
        "id": 1,
        "agencia": {
            "id": 3,
            "usuario": {
                "id": 4,
                "nome": "Agencia",
                "email": "agencia@email.com",
                "password": "$2a$10$fMx89lV.Np4g7kZH8U7MYuN2QQLf5NPsNrqO6yDzbYetajSTVitBi",
                "role": "ROLE_Agencia"
            },
            "cnpj": "123123",
            "nome": "Agencia",
            "descricao": null
        },
        "destino": "asd3",
        "partida": "2021-11-17T22:43",
        "duracao": 12,
        "valor": 12
    }
]

REST API -- Retorna a lista de pacotes turísticos do cliente de id = {id} [Read - CRUD] Feito
GET http://localhost:8080/pacotes/clientes/{id}

REST API -- Cria um novo pacote turístico na agência de id = {id} [Create - CRUD] Feito
POST http://localhost:8080/pacotes/agencias/{id}

REST API -- Retorna a lista de pacotes turísticos da agência de id = {id} [Read - CRUD] Feito
GET http://localhost:8080/pacotes/agencias/{id}

REST API -- Retorna a lista de pacotes turístivos cujo destino = {nome} [Read - CRUD] Feito
GET http://localhost:8080/pacotes/destinos/{nome}

# Funcionalidades AA-2
R1: CRUD 1 de clientes (requer login de administrador). Feito

R2: CRUD de agências de turismo (requer login de administrador). Feito

R3: Cadastro de pacotes turísticos para venda (requer login da agência via e-mail + senha). Feito
Depois de fazer login, a agência de turismo pode cadastrar um pacote turístico para venda. O
cadastro de pacotes turísticos deve possuir os seguintes dados: CNPJ da agência de turismo,
destinos (cidade/estado/país), data de partida, duração (em dias), valor, fotos (no máximo 10
imagens) dos locais turísticos a serem visitados. Por fim, é necessária, no cadastro, a
descrição (arquivo PDF) com o roteiro detalhado do pacote turístico.

R4: Listagem de todos os pacotes turísticos em uma única página (não requer login). O
sistema deve prover a funcionalidade de filtrar os pacotes turísticos por destino, por agência
de turismo ou por data de partida. Feito parcialmente

R5: Compra de pacote turístico (requer login do cliente via e-mail + senha). Ao clicar em um
pacote turístico (requisito R4), o cliente pode efetuar a compra do pacote. O cliente e a
agência de turismo devem ser informados (via e-mail) sobre a compra. Nesse caso, o sistema
deve também informar um horário para uma reunião (via videoconferência) entre o cliente e
a agência de turismo para acertar os detalhes da compra/venda do pacote turístico -- o link
da videoconferência (google meet, zoom, etc) deve estar presente no corpo da mensagem
enviada. Feito parcialmente

R6: Listagem de todos os pacotes turísticos de um cliente (requer login do cliente via e-mail +
senha). Depois de fazer login, o cliente pode visualizar todos os seus pacotes turísticos
adquiridos. Feito 

R7: Listagem de todos os pacotes turísticos de uma agência de turismo (requer login da
agência via e-mail + senha). Depois de fazer login, a agência pode visualizar todos os seus
pacotes turísticos cadastrados. O sistema apenas deve prover a funcionalidade de filtrar
apenas os pacotes "vigentes" -- com a data de partida posterior a data atual do sistema. Feito

R8: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro
de sua escolha. Feito

R9: O sistema deve validar (tamanho, formato, etc) todas as informações (campos nos
formulários) cadastradas e/ou editadas. Feito