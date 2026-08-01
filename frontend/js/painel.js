const senha = document.querySelector("#senha");
const nome = document.querySelector("#nome");
const destino = document.querySelector("#destino");
const recentes = document.querySelector("#recentes");
const hora = document.querySelector("#hora");
const dia = document.querySelector("#data-painel");
const relogio = document.querySelector("#relogio");
const btnTema = document.querySelector(".btn-tema");
const body = document.querySelector("body");
const painelCabeçalho = document.querySelector(".painel-cabecalho");
let ultimaChamadaNarrada = null;


let estadoTema = true;
btnTema.addEventListener("click", () => {
  if (estadoTema) {
    painelCabeçalho.style.color = "white";
    body.style.background = "radial-gradient(circle at 30% 55%, #00483d55, transparent 35%), #010a1a";
  } else {
    body.style.background = "var(--fundo)";
    painelCabeçalho.style.color = "var(--linha)";
  }
  estadoTema = !estadoTema;
});



async function time() {
  const data = new Date();
  relogio.textContent = data.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });
  dia.textContent = data.toLocaleDateString('pt-BR', { weekday: 'long', day: '2-digit', month: '2-digit', year: 'numeric' });



}


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
    time();
    const chamada = await apiRequest("/chamadas/ultima");

    if (chamada) {
      senha.textContent = chamada.atendimento.senha;
      nome.textContent = chamada.atendimento.paciente.nome;
      destino.textContent = ` ${chamada.sala}`;

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
