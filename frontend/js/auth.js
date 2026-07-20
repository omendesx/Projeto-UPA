const auth = {
    getUser() {
        const value = localStorage.getItem("upa-user");
        return value ? JSON.parse(value) : null;
    },

    setUser(user) {
        localStorage.setItem("upa-user", JSON.stringify(user));
    },

    logout() {
        localStorage.removeItem("upa-user");
        location.href = "login.html";
    },

    requireRole(...allowedRoles) {
        const user = this.getUser();

        if (!user) {
            location.href = "login.html";
            return null;
        }

        if (user.cargo !== "ADMIN" && !allowedRoles.includes(user.cargo)) {
            location.href = routeByRole(user.cargo);
            return null;
        }

        return user;
    }
};

function routeByRole(role) {
    const routes = {
        ADMIN: "dashboard.html",
        RECEPCIONISTA: "balcao.html",
        ENFERMEIRO: "triagem.html",
        MEDICO: "consultorio.html"
    };

    return routes[role] || "login.html";
}

function renderUser() {
    const user = auth.getUser();
    const target = document.querySelector("[data-current-user]");

    if (target && user) {
        target.textContent = `${user.nome} — ${user.cargo}`;
    }
}
