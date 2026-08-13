document.getElementById('registroFecha').valueAsDate = new Date();

function calculateTotal() {
    const comercializables = parseInt(document.getElementById('huevosComercializables').value) || 0;
    const rotos = parseInt(document.getElementById('huevosRotos').value) || 0;
    const totalValueElement = document.getElementById('totalValue');
    const total = comercializables + rotos;

    // Efecto de animación al cambiar el número
    if (totalValueElement.innerText != total) {
        totalValueElement.classList.add('scale-125', 'text-primary');
        setTimeout(() => {
            totalValueElement.classList.remove('scale-125', 'text-primary');
        }, 200);
    }

    totalValueElement.innerText = total.toLocaleString();
}

function addInsumoRow() {
    const container = document.getElementById('insumosContainer');
    const template = document.getElementById('insumoRowTemplate');
    const emptyState = document.getElementById('emptyInsumos');

    emptyState.classList.add('hidden');

    const clone = template.content.cloneNode(true);
    const row = clone.querySelector('.insumo-row');

    // Animación de entrada
    row.style.opacity = '0';
    row.style.transform = 'translateY(10px)';
    container.appendChild(clone);

    requestAnimationFrame(() => {
        const newRow = container.lastElementChild;
        newRow.style.transition = 'all 0.3s ease-out';
        newRow.style.opacity = '1';
        newRow.style.transform = 'translateY(0)';
    });
}

function removeInsumoRow(btn) {
    const row = btn.closest('.insumo-row');
    const container = document.getElementById('insumosContainer');
    const emptyState = document.getElementById('emptyInsumos');

    row.style.opacity = '0';
    row.style.transform = 'translateX(20px)';

    setTimeout(() => {
        row.remove();
        if (container.children.length === 0) {
            emptyState.classList.remove('hidden');
        }
    }, 300);
}

// Manejar menú móvil
const mobileToggle = document.getElementById('mobile-menu-toggle');
const sidebar = document.getElementById('sidebar');

mobileToggle?.addEventListener('click', () => {
    sidebar.classList.toggle('-translate-x-full');
});

// Inicializar con una fila para mejor UX
window.addEventListener('load', () => {
    addInsumoRow();
});

// Manejar envío del formulario
document.getElementById('productionForm').addEventListener('submit', (e) => {
    e.preventDefault();
    const btn = e.target.querySelector('button[type="submit"]');
    const originalText = btn.innerHTML;

    btn.disabled = true;
    btn.innerHTML = `
        <span class="flex items-center gap-2">
            <svg class="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
            </svg>
            Procesando...
        </span>
    `;

    // Simulación de éxito
    setTimeout(() => {
        btn.innerHTML = <span class="flex items-center gap-2"><span class="material-symbols-outlined">check_circle</span> Registro Exitoso</span>;
        btn.classList.replace('bg-primary', 'bg-tertiary-container');
        btn.classList.add('text-on-tertiary-container');

        setTimeout(() => {
            btn.disabled = false;
            btn.innerHTML = originalText;
            btn.classList.replace('bg-tertiary-container', 'bg-primary');
            btn.classList.remove('text-on-tertiary-container');
        }, 3000);
    }, 1500);
});