<h1 align="center">
  PicPay Desafio Backend Sênior
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=Youtube&message=@giulianabezerra&color=8257E5&labelColor=000000" alt="@giulianabezerra" />
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Desafio" />
</p>

<p align="center">
  <strong>Este projeto foi desenvolvido com base nas aulas ministradas por <a href="https://www.youtube.com/@giulianabezerra" target="_blank">Giuliana Bezerra</a> no YouTube, com o intuito de aprimorar a prática e aprofundar o conhecimento técnico. A Giuliana merece todos os créditos pela didática clara e contribuição generosa à comunidade.</strong>
</p>

---

Projeto elaborado [neste vídeo](https://youtu.be/YcuscoiIN14) para solucionar [este desafio](https://github.com/PicPay/picpay-desafio-backend?tab=readme-ov-file) voltado a uma vaga backend com perfil sênior. A solução desenvolvida é uma versão simplificada da plataforma PicPay.

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc)
- [Spring for Apache Kafka](https://spring.io/projects/spring-kafka)
- [Docker Compose](https://docs.docker.com/compose/)
- [H2](https://www.h2database.com/html/main.html)

## Como Executar

- Clonar o repositório:
```bash
git clone https://github.com/giuliana-bezerra/picpay-desafio-backend.git

```
- Executar o Kafka:
```
docker-compose up
```
- Executar a aplicação Spring Boot
- Acessar aplicação em `http://localhost:8080`.

## Arquitetura

![Desenho de Arquitetura](.github/Desenho%20de%20Arquitetura.png)

![Diagrama de Atividades](.github/Diagrama%20de%20Atividades.png)

## API

- http :8080/transaction value=100.0 payer=1 payee=200
```
HTTP/1.1 200
Connection: keep-alive
Content-Type: application/json
Date: Tue, 05 Mar 2024 19:07:52 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

{
    "createdAt": "2024-03-05T16:07:50.749774",
    "id": 20,
    "payee": 2,
    "payer": 1,
    "value": 100.0
}
```

- http :8080/transaction
```
HTTP/1.1 200
Connection: keep-alive
Content-Type: application/json
Date: Tue, 05 Mar 2024 19:08:13 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

[
    {
        "createdAt": "2024-03-05T16:07:50.749774",
        "id": 20,
        "payee": 2,
        "payer": 1,
        "value": 100.0
    }
]
```
