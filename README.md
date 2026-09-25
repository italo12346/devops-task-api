# DevOps Task API

API REST de gerenciamento de tarefas construída com Spring Boot, criada como projeto de estudo para praticar backend Java, boas práticas de arquitetura em camadas e, na sequência, um pipeline completo de CI/CD com testes automatizados, análise de qualidade (SonarQube), varredura de segurança (Trivy) e deploy em nuvem (AWS/EC2).

Este é o primeiro projeto de uma série focada em DevSecOps: unir desenvolvimento backend com entrega contínua e segurança integrada ao pipeline, do primeiro commit até a aplicação rodando em produção.

## Sobre o projeto

O objetivo aqui não é só ter uma API funcionando, mas entender **cada peça** do ciclo de entrega de software: da modelagem dos dados no banco até o container passando pela esteira de CI/CD. Todo o código foi escrito manualmente, camada por camada (model → repository → service → controller), com foco em entender o "porquê" de cada decisão — não só o "como".

## Stack

- **Java 21**
- **Spring Boot 4.1.1**
- **Spring Data JPA** / Hibernate
- **PostgreSQL**
- **Gradle**
- **Bean Validation** (jakarta.validation)
- Testes com **JUnit 5** e **Mockito**

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/health` | Verifica se a aplicação está no ar |
| GET | `/tasks` | Lista todas as tasks |
| GET | `/tasks/{id}` | Busca uma task por id |
| POST | `/tasks` | Cria uma nova task |
| PUT | `/tasks/{id}` | Atualiza uma task existente |
| DELETE | `/tasks/{id}` | Remove uma task |

## Arquitetura

O projeto segue uma separação clássica de camadas:

```
model/       → entidade JPA (Task), mapeia a tabela no Postgres
repository/  → acesso a dados via Spring Data JPA
service/     → regras de negócio
controller/  → endpoints REST, tradução HTTP ↔ DTOs
dto/         → TaskRequest (entrada) e TaskResponse (saída), isolando a API da entidade do banco
exception/   → tratamento centralizado de erros (404, 400)
```

## Como rodar localmente

### Pré-requisitos

- Java 21
- Docker (para o Postgres)

### Passo a passo

1. Suba um container do Postgres:
```bash
docker run --name devops-postgres -e POSTGRES_PASSWORD=sua_senha -e POSTGRES_DB=devops_task -p 5432:5432 -d postgres
```

2. Copie o `.env.example` para `.env` e preencha com suas credenciais:
```bash
cp .env.example .env
```

3. Rode a aplicação:
```bash
./gradlew bootRun
```

4. Teste:
```bash
curl http://localhost:8080/health
```

### Rodando os testes

```bash
./gradlew test
```

## Roadmap (pipeline CI/CD)

- [x] API REST com CRUD completo
- [x] Testes unitários e de integração
- [x] Dockerfile (build multi-stage)
- [x] GitHub Actions rodando testes a cada Push e Pull Request
- [ ] Análise de qualidade com SonarQube
- [ ] Varredura de vulnerabilidades com Trivy
- [x] Análise de qualidade com SonarQube
- [x] Varredura de vulnerabilidades com Trivy
- [ ] Build e publicação da imagem no Docker Hub
- [ ] Deploy automatizado em AWS/EC2
- [ ] Health check pós-deploy

## Licença

Projeto de estudo, livre para uso e referência.