const formLogin = document.querySelector("#formLogin");
const message = document.querySelector("#message");

const currentUser = auth.getUser();

if (currentUser) {
    location.href = routeByRole(currentUser.cargo);
}

formLogin.addEventListener("submit", async event => {
    event.preventDefault();
    message.textContent = "Entrando...";

    const data = Object.fromEntries(new FormData(formLogin));

    try {
        const user = await apiRequest("/auth/login", {
            method: "POST",
            body: JSON.stringify(data)
        });

        auth.setUser(user);
        location.href = routeByRole(user.cargo);
    } catch (error) {
        message.textContent = error.message;
        message.className = "message error-message";
    }
});

const formLogin = document.querySelector("#formLogin");
const message = document.querySelector("#message");

const currentUser = auth.getUser();

if (currentUser) {
    location.href = routeByRole(currentUser.cargo);
}

formLogin.addEventListener("submit", async event => {
    event.preventDefault();
    message.textContent = "Entrando...";

    const data = Object.fromEntries(new FormData(formLogin));

    try {
        const user = await apiRequest("/auth/login", {
            method: "POST",
            body: JSON.stringify(data)
        });

        auth.setUser(user);
        location.href = routeByRole(user.cargo);
    } catch (error) {
        message.textContent = error.message;
        message.className = "message error-message";
    }
});
