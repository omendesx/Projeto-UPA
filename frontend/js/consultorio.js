auth.requireRole("MEDICO");
renderUser();

const queue = document.querySelector("#queue");
const refreshQueue = document.querySelector("#refreshQueue");
const patientData = document.querySelector("#patientData");
const formProntuario = document.querySelector("#formProntuario");
const formSolicitacao = document.querySelector("#formSolicitacao");
const atendimentoId = document.querySelector("#atendimentoId");
const prontuarioId = document.querySelector("#prontuarioId");
const finishButton = document.querySelector("#finishButton");
const message = document.querySelector("#message");

let currentAtendimento = null;

async function carregarFila() {
    try {
        const atendimentos = await apiRequest("/atendimentos/fila/medico");

        if (atendimentos.length === 0) {
            queue.innerHTML = "<p>Nenhum paciente aguardando.</p>";
            return;
        }

        queue.innerHTML = atendimentos.map(item => `
            <article class="list-item">
                <strong>${item.senha} — ${item.paciente.nome}</strong>
                <p>Risco: ${item.classificacaoRisco}</p>
                <p>${item.motivo}</p>
                <button onclick="selecionarPaciente(${item.id})">
                    Chamar e atender
                </button>
            </article>
        `).join("");
    } catch (error) {
        queue.innerHTML = `<p>${error.message}</p>`;
    }
}

window.selecionarPaciente = async id => {
    try {
        currentAtendimento = await apiRequest(`/atendimentos/${id}`);
        const triagem = await apiRequest(`/triagens/atendimento/${id}`);

        atendimentoId.value = id;

        patientData.innerHTML = `
            <strong>${currentAtendimento.paciente.nome}</strong>
            <p>Senha: ${currentAtendimento.senha}</p>
            <p>Risco: ${currentAtendimento.classificacaoRisco}</p>
            <p>Sintomas: ${triagem.sintomas || "Não informado"}</p>
            <p>Pressão: ${triagem.pressaoArterial || "Não informada"}</p>
            <p>Dor: ${triagem.nivelDor ?? "Não informada"}</p>
        `;

        await apiRequest("/chamadas", {
            method: "POST",
            body: JSON.stringify({
                atendimentoId: id,
                destino: "CONSULTORIO",
                sala: "Consultório 01"
            })
        });

        message.textContent = "Paciente chamado para o consultório.";
    } catch (error) {
        message.textContent = error.message;
    }
};

formProntuario.addEventListener("submit", async event => {
    event.preventDefault();

    if (!atendimentoId.value) {
        message.textContent = "Selecione um paciente.";
        return;
    }

    const data = Object.fromEntries(new FormData(formProntuario));
    data.atendimentoId = Number(data.atendimentoId);

    try {
        const prontuario = await apiRequest("/prontuarios", {
            method: "POST",
            body: JSON.stringify(data)
        });

        prontuarioId.value = prontuario.id;
        message.textContent = "Prontuário salvo.";
    } catch (error) {
        message.textContent = error.message;
    }
});

formSolicitacao.addEventListener("submit", async event => {
    event.preventDefault();

    if (!prontuarioId.value) {
        message.textContent = "Salve o prontuário primeiro.";
        return;
    }

    const data = Object.fromEntries(new FormData(formSolicitacao));
    data.prontuarioId = Number(data.prontuarioId);

    try {
        await apiRequest("/solicitacoes", {
            method: "POST",
            body: JSON.stringify(data)
        });

        message.textContent = "Solicitação criada.";
        formSolicitacao.reset();
    } catch (error) {
        message.textContent = error.message;
    }
});

finishButton.addEventListener("click", async () => {
    if (!atendimentoId.value) {
        message.textContent = "Selecione um paciente.";
        return;
    }

    try {
        await apiRequest(
            `/prontuarios/atendimento/${atendimentoId.value}/finalizar`,
            { method: "PATCH" }
        );

        message.textContent = "Atendimento finalizado.";
        formProntuario.reset();
        formSolicitacao.reset();
        atendimentoId.value = "";
        prontuarioId.value = "";
        patientData.textContent = "Selecione um paciente.";
        await carregarFila();
    } catch (error) {
        message.textContent = error.message;
    }
});

refreshQueue.addEventListener("click", carregarFila);

carregarFila();
