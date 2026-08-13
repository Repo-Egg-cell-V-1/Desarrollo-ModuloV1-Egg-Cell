document.getElementById('fecha').valueAsDate = new Date();

const form = document.getElementById('loteForm');
const toast = document.getElementById('successToast');

// Manejar el envío del formulario
form.addEventListener('submit', (e) => {
    e.preventDefault();

    // Mostrar toast de éxito
    toast.classList.remove('toast-hidden');
    toast.classList.add('toast-active');

    // Ocultar automáticamente después de 4 segundos
    setTimeout(() => {
        closeToast();
    }, 4000);

    // Opcional: reiniciar formulario
    // form.reset();
    // document.getElementById('fecha').valueAsDate = new Date();
});

// Función para cerrar el toast
function closeToast() {
    toast.classList.add('toast-hidden');
    toast.classList.remove('toast-active');
}

// Toggle del menú lateral en móvil
const menuBtn = document.querySelector('.md\\:hidden');
const sidebar = document.getElementById('sidebar');

let sidebarOpen = false;

menuBtn?.addEventListener('click', () => {
    sidebarOpen = !sidebarOpen;
    if (sidebarOpen) {
        sidebar.classList.remove('hidden');
        sidebar.classList.add('flex');
        sidebar.classList.add('fixed', 'inset-0', 'w-full');
    } else {
        sidebar.classList.add('hidden');
    }
});