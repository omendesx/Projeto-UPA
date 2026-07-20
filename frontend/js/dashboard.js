auth.requireRole("ADMIN");
renderUser();

const form = document.querySelector("#formUsuario");
const usuarioId = document.querySelector("#usuarioId");
const usuarios = document.querySelector("#usuarios");
const message = document.querySelector("#message");
const cancelEdit = document.querySelector("#cancelEdit");

let cache = [];

async function carregarUsuarios() {
    try {
        cache = await apiRequest("/usuarios");

        usuarios.innerHTML = cache.map(user => `
            <article class="list-item">
                <strong>${user.nome}</strong>
                <p>${user.email}</p>
                <p>${user.cargo} — ${user.ativo ? "Ativo" : "Inativo"}</p>
                <button onclick="editarUsuario(${user.id})">Editar</button>
                <button class="danger" onclick="excluirUsuario(${user.id})">Excluir</button>
            </article>
        `).join("");
    } catch (error) {
        message.textContent = error.message;
    }
}

window.editarUsuario = id => {
    const user = cache.find(item => item.id === id);
    if (!user) return;

    usuarioId.value = user.id;
    form.elements.nome.value = user.nome;
    form.elements.email.value = user.email;
    form.elements.senha.value = "";
    form.elements.cargo.value = user.cargo;
    form.elements.ativo.value = String(user.ativo);
};

window.excluirUsuario = async id => {
    if (!confirm("Excluir este funcionário?")) return;

    try {
        await apiRequest(`/usuarios/${id}`, { method: "DELETE" });
        await carregarUsuarios();
    } catch (error) {
        message.textContent = error.message;
    }
};

form.addEventListener("submit", async event => {
    event.preventDefault();

    const data = Object.fromEntries(new FormData(form));
    data.ativo = data.ativo === "true";

    try {
        const id = usuarioId.value;

        await apiRequest(id ? `/usuarios/${id}` : "/usuarios", {
            method: id ? "PUT" : "POST",
            body: JSON.stringify(data)
        });

        form.reset();
        usuarioId.value = "";
        message.textContent = "Funcionário salvo.";
        await carregarUsuarios();
    } catch (error) {
        message.textContent = error.message;
    }
});

cancelEdit.addEventListener("click", () => {
    form.reset();
    usuarioId.value = "";
});

carregarUsuarios();
