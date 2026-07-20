auth.requireRole("ENFERMEIRO");
renderUser();

const queue = document.querySelector("#queue");
const refreshQueue = document.querySelector("#refreshQueue");
const form = document.querySelector("#formTriagem");
const atendimentoId = document.querySelector("#atendimentoId");
const message = document.querySelector("#message");

async function carregarFila() {
    try {
        const atendimentos = await apiRequest("/atendimentos/fila/triagem");

        if (atendimentos.length === 0) {
            queue.innerHTML = "<p>Nenhum paciente aguardando.</p>";
            return;
        }

        queue.innerHTML = atendimentos.map(item => `
            <article class="list-item">
                <strong>${item.senha} — ${item.paciente.nome}</strong>
                <p>${item.motivo}</p>
                <p>Ambulância: ${item.ambulancia ? "Sim" : "Não"}</p>
                <button onclick="selecionarAtendimento(${item.id})">
                    Chamar e selecionar
                </button>
            </article>
        `).join("");
    } catch (error) {
        queue.innerHTML = `<p>${error.message}</p>`;
    }
}

window.selecionarAtendimento = async id => {
    try {
        atendimentoId.value = id;

        await apiRequest("/chamadas", {
            method: "POST",
            body: JSON.stringify({
                atendimentoId: id,
                destino: "TRIAGEM",
                sala: "Triagem 01"
            })
        });

        message.textContent = "Paciente chamado para triagem.";
    } catch (error) {
        message.textContent = error.message;
    }
};

form.addEventListener("submit", async event => {
    event.preventDefault();

    const data = Object.fromEntries(new FormData(form));

    data.atendimentoId = Number(data.atendimentoId);
    data.temperatura = data.temperatura ? Number(data.temperatura) : null;
    data.frequenciaCardiaca =
        data.frequenciaCardiaca ? Number(data.frequenciaCardiaca) : null;
    data.nivelDor = data.nivelDor ? Number(data.nivelDor) : null;

    try {
        await apiRequest("/triagens", {
            method: "POST",
            body: JSON.stringify(data)
        });

        message.textContent = "Triagem registrada.";
        message.className = "message success-message";
        form.reset();
        await carregarFila();
    } catch (error) {
        message.textContent = error.message;
        message.className = "message error-message";
    }
});

refreshQueue.addEventListener("click", carregarFila);

carregarFila();
