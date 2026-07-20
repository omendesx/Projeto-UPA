const API_URL =
    location.hostname === "localhost" ||
    location.hostname === "127.0.0.1"
        ? "http://localhost:8080/api"
        : "https://SEU-BACKEND.up.railway.app/api";

async function apiRequest(path, options = {}) {
    const response = await fetch(`${API_URL}${path}`, {
        headers: {
            "Content-Type": "application/json",
            ...(options.headers || {})
        },
        ...options
    });

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
