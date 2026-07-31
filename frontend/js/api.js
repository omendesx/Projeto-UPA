const isDevelopmentHost =
    ["localhost", "127.0.0.1"].includes(location.hostname);

const API_URL = isDevelopmentHost
    ? "https://projeto-upa.onrender.com/api"
    : `${location.origin}/api`;

async function apiRequest(path, options = {}) {
    let response;

    try {
        response = await fetch(`${API_URL}${path}`, {
            ...options,
            headers: {
                "Content-Type": "application/json",
                ...(options.headers || {})
            }
        });
    } catch {
        throw new Error(
            `Não foi possível conectar à API em ${API_URL}. ` +
            "Confirme se o Spring Boot está iniciado."
        );
    }

    if (!response.ok) {
        let message = "Não foi possível concluir a operação.";

        try {
            const error = await response.json();
            message = error.message || message;
        } catch {
            // Usa mensagem padrão.
        }

        throw new Error(message);
    }

    if (response.status === 204) {
        return null;
    }

    return response.json();
}
