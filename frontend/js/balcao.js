auth.requireRole("RECEPCIONISTA");
renderUser();

const form = document.querySelector("#formAtendimento");
const message = document.querySelector("#message");
const panicButton = document.querySelector("#panicButton");
const panicMessage = document.querySelector("#panicMessage");

form.addEventListener("submit", async event => {
    event.preventDefault();

    const data = Object.fromEntries(new FormData(form));
    data.ambulancia = data.ambulancia === "true";
    data.dataNascimento = data.dataNascimento || null;

    try {
        const atendimento = await apiRequest("/atendimentos", {
            method: "POST",
            body: JSON.stringify(data)
        });

        message.textContent = `Atendimento criado. Senha: ${atendimento.senha}`;
        message.className = "message success-message";
        form.reset();
    } catch (error) {
        message.textContent = error.message;
        message.className = "message error-message";
    }
});

panicButton.addEventListener("click", () => {
    panicMessage.textContent =
        "ALERTA DE PÂNICO ACIONADO NO BALCÃO. Demonstração visual.";
    panicMessage.className = "message error-message";
});
