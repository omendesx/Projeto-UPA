const PAGE_SIZE = 10;

function renderPaginatedList({
    container,
    items,
    page,
    renderItem,
    emptyMessage,
    onPageChange
}) {
    if (items.length === 0) {
        container.innerHTML = `<p>${emptyMessage}</p>`;
        return 1;
    }

    const totalPages = Math.ceil(items.length / PAGE_SIZE);
    const currentPage = Math.min(Math.max(page, 1), totalPages);
    const start = (currentPage - 1) * PAGE_SIZE;
    const visibleItems = items.slice(start, start + PAGE_SIZE);

    container.innerHTML = `
        ${visibleItems.map(renderItem).join("")}
        ${totalPages > 1 ? `
            <nav class="pagination" aria-label="Navegação da lista">
                <button type="button" data-page="${currentPage - 1}"
                    ${currentPage === 1 ? "disabled" : ""}>
                    Anterior
                </button>
                <span>Página ${currentPage} de ${totalPages}</span>
                <button type="button" data-page="${currentPage + 1}"
                    ${currentPage === totalPages ? "disabled" : ""}>
                    Próxima
                </button>
            </nav>
        ` : ""}
    `;

    container.querySelectorAll(".pagination button:not([disabled])")
        .forEach(button => {
            button.addEventListener("click", () => {
                onPageChange(Number(button.dataset.page));
            });
        });

    return currentPage;
}
