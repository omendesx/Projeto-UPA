const senha = document.querySelector("#senha");
const nome = document.querySelector("#nome");
const destino = document.querySelector("#destino");
const recentes = document.querySelector("#recentes");

let ultimaChamadaNarrada = null;

function narrar(chamada) {
    const text =
        `Paciente ${chamada.atendimento.paciente.nome}, ` +
        `senha ${chamada.atendimento.senha}, ` +
        `dirija-se a ${chamada.sala}.`;

    speechSynthesis.cancel();

    const voice = new SpeechSynthesisUtterance(text);
    voice.lang = "pt-BR";
    voice.rate = 0.9;
    voice.pitch = 1;

    speechSynthesis.speak(voice);
}

async function atualizarPainel() {
    try {
        const chamada = await apiRequest("/chamadas/ultima");

        if (chamada) {
            senha.textContent = chamada.atendimento.senha;
            nome.textContent = chamada.atendimento.paciente.nome;
            destino.textContent = `${chamada.destino} — ${chamada.sala}`;

            if (chamada.id !== ultimaChamadaNarrada) {
                ultimaChamadaNarrada = chamada.id;
                narrar(chamada);
            }
        }

        const lista = await apiRequest("/chamadas/recentes");

        recentes.innerHTML = lista.map(item => `
            <p>
                ${item.atendimento.senha} —
                ${item.atendimento.paciente.nome} —
                ${item.sala}
            </p>
        `).join("");
    } catch (error) {
        destino.textContent = error.message;
    }
}

atualizarPainel();
setInterval(atualizarPainel, 2000);
