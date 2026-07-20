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

Configure as variáveis:

DATABASE_URL=jdbc:postgresql://HOST/BANCO?sslmode=require
DATABASE_USERNAME=USUARIO
DATABASE_PASSWORD=SENHA
FRONTEND_URL=http://127.0.0.1:5500

Depois:

cd backend
mvnw.cmd spring-boot:run

### Front-End

Abra a pasta frontend no VS Code e execute o index.html com Live Server.

## Usuário administrador inicial

ADMIN_EMAIL=admin@upa.com
ADMIN_PASSWORD=admin123

Altere essas credenciais no Railway antes da apresentação.
