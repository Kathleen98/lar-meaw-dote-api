# Comandos Docker E Tabela 
Registro de aprendizado

## 1. Desafio

Configurar o Docker Desktop, rodar Docker pela CLI sem `docker-compose`, subir uma imagem Postgres, executar comandos dentro do container e criar uma tabela para entender o funcionamento.

---

## 2. Registro da experiência

### Virtualização bloqueada

Ao instalar o Docker Desktop me deparei com um erro que apontava para a virtualização. Ela não estava habilitada e impedia o Docker Desktop de rodar. Precisei liberar na BIOS para conseguir seguir.

**Erro:**

```
request returned 500 Internal Server Error for API route and version
http://%2F%2F.%2Fpipe%2FdockerDesktopLinuxEngine/_ping
```


---

### Subindo o Postgres

A instalação foi feita lendo a documentação oficial da imagem: <https://hub.docker.com/_/postgres>

Comando que usei:

```powershell
docker run --name postgres `
  -e POSTGRES_PASSWORD=mysecretpassword `
  -e POSTGRES_DB=postgres-docker `
  -p 5432:5432 `
  -d postgres
```

**Estrutura do comando:**

```
docker run [flags] IMAGEM
```

Tudo antes da imagem é flag. A imagem é sempre o último argumento posicional — não é indicada por flag nenhuma.

| Flag | O que faz |
|---|---|
| `--name` | Nome do container. Sem ela o Docker cria um nome aleatório |
| `-e` | Variável de ambiente. Cada variável pede um `-e` |
| `-p host:container` | Mapeia porta. **De onde vem : para onde vai** |
| `-d` | **Detached** — libera o prompt. Sem ela o Docker fica ocupando o terminal, e derrubar o processo mataria o container |

**Sobre o `-p`:** na primeira tentativa subi sem ele. O container rodava, mas o `docker ps` mostrava só `5432/tcp`, sem seta. O Postgres estava escutando **dentro** do container e nada do Windows conseguia chegar nele — o container tem a própria pilha de rede.

Com o `-p`, aparece o mapeamento:

```
0.0.0.0:5432->5432/tcp, [::]:5432->5432/tcp
```

**Variáveis da imagem:**

- `POSTGRES_PASSWORD` — obrigatória. A imagem se recusa a subir sem ela.
- `POSTGRES_USER` — opcional. Padrão: `postgres`
- `POSTGRES_DB` — opcional. Padrão: igual ao usuário

---

### Entrando no container

```powershell
docker exec -it postgres psql -U postgres -d postgres-docker
```

| Parte | O que faz |
|---|---|
| `exec` | Executa um comando dentro de um container **que está rodando** |
| `-it` | Interativo + terminal. Sem isso o `psql` não aceita digitação |
| `-U` | Usuário do Postgres |
| `-d` | Banco de dados |

---

## 3. SQL

Comando que rodei para criar uma tabela e testar os comandos do `psql`:

```sql
CREATE TABLE cobaia (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL
);

INSERT INTO cobaia (nome) VALUES ('Pipoca'), ('Bolota'), ('Teste');
```

### Comandos do psql

| Comando | O que faz |
|---|---|
| `\l` | Lista os bancos |
| `\c <banco>` | Conecta a outro banco |
| `\dt` | Lista as tabelas do banco atual |
| `\d <tabela>` | Estrutura da tabela: colunas, tipos, chaves |
| `\du` | Lista usuários e roles |
| `\q` | Sai |

---

## 4. Ciclo de vida do container

Um container nasce quando rodamos o comando de execução e morre assim que o processo de execução for finalizado. Quando rodamos `docker run hello-world` o container se mantém até que o que ele foi programado para fazer seja concluído; assim que finalizado, o container morre.

Ao rodar `docker ps` listamos todos os containers em execução.

Ao rodar `docker ps -a` listamos todos os containers, incluindo os que já foram executados. A flag `-a` significa *all*.

Ao rodar o comando `docker stop nome_do_container` paramos o container, e será possível erguê-lo novamente com o comando `docker start nome_do_container`. Diferente do comando `docker rm -f`, que remove o container: não tem como erguê-lo novamente, apenas a imagem permanece na máquina.

---

## 5. Onde os dados vivem

Para persistir os dados, usamos a flag `-v` de volume. Com ela passamos o caminho para onde a imagem aponta que devemos salvar os dados de persistência. No caso do Postgres 18 o caminho fica em `/var/lib/postgresql`; nas versões anteriores fica em `/var/lib/postgresql/data`, conforme a documentação oficial da imagem no Docker Hub.

Esse conhecimento foi obtido após pegar informações da internet que estavam desatualizadas e precisei rever a doc oficial para entender a atualização.

---

## 6. Diagnóstico de erro

O Docker aponta erro quando o status do container é diferente de `Exited (0)`. A primeira coisa que devemos fazer para entender o que está acontecendo é rodar o comando `docker logs nome_do_container`. Com esse comando conseguimos ver o histórico até mesmo de containers que já finalizaram a execução.

Descobri esse comando e o significado do status quando subi a imagem do Postgres sem tag de versão, com isso a imagem do Postgres foi baixada na versão `latest` que é setado por padrão quando uma versão não é definida, o que é um erro, principalmente para produção. O melhor é sempre setar uma versão.