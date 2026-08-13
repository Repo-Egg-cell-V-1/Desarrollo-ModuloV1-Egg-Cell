document.querySelector('button.bg-secondary').addEventListener('click', () => {
    const toast = document.getElementById('toast');
    toast.classList.remove('translate-y-24');
    setTimeout(() => {
        toast.classList.add('translate-y-24');
    }, 3000);
});

// Las animaciones hover de las tarjetas KPI se manejan mediante CSS transitions