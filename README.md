# Sistema UPA

Projeto acadêmico fullstack para simular o fluxo de atendimento de uma UPA.

## Tecnologias

- Front-End: HTML, CSS e JavaScript
- Back-End: Java 17 e Spring Boot
- Banco: PostgreSQL no Neon
- API: Railway
- Front-End publicado: Vercel

## Perfis

- ADMIN
- RECEPCIONISTA
- ENFERMEIRO
- MEDICO

## Fluxo

1. Recepcionista cadastra o paciente e gera atendimento.
2. Enfermeiro chama o paciente e registra a triagem.
3. O painel público exibe e narra a chamada.
4. Médico chama o paciente e registra o prontuário.
5. Médico cria solicitações e finaliza o atendimento.
6. Admin gerencia funcionários.

## Execução local

### Back-End

Sem variáveis de ambiente, o back-end usa um banco H2 local salvo em
`backend/data`. Para usar PostgreSQL, configure as variáveis:

DATABASE_URL=jdbc:postgresql://HOST/BANCO?sslmode=require
DATABASE_USERNAME=USUARIO
DATABASE_PASSWORD=SENHA
FRONTEND_URL=http://127.0.0.1:5500

Depois:

cd backend
mvnw.cmd spring-boot:run

Com o back-end iniciado, acesse `http://localhost:8080/`. O Spring tambÃ©m
serve os arquivos da pasta `frontend`, portanto nÃ£o Ã© necessÃ¡rio iniciar o
Live Server separadamente.

### Front-End

Os arquivos do front-end ficam na pasta `frontend` e sÃ£o incluÃ­dos
automaticamente pelo Maven ao executar ou empacotar o back-end.

## Usuário administrador inicial

ADMIN_EMAIL=admin@upa.com
ADMIN_PASSWORD=admin123

Altere essas credenciais no Railway antes da apresentação.

## Deploy no Railway

O `Dockerfile` da raiz empacota o front-end junto com o Spring Boot. No
Railway, publique a raiz deste repositório e configure estas variáveis:

```text
DATABASE_URL=jdbc:postgresql://HOST/BANCO?sslmode=require&channelBinding=require
DATABASE_USERNAME=USUARIO
DATABASE_PASSWORD=SENHA
ADMIN_EMAIL=admin@upa.com
ADMIN_PASSWORD=uma-senha-forte
```

Não inclua usuário e senha dentro de `DATABASE_URL`. O Railway fornece a
variável `PORT` automaticamente. Depois do deploy, a interface e a API ficam
no mesmo domínio gerado pelo Railway.
