// Mensaje con texto
function Mensaje(titulo, texto, icono) {
    Swal.fire({title: titulo, text: texto, icon: icono, showConfirmButton: false, timer: 3000});
};
// Mensaje sin texto
function Mensaje2(titulo, icono) {
    Swal.fire({title: titulo, icon: icono});
};
function Mensaje3(titulo, texto, icono) {
    Swal.fire({title: titulo, text: texto, icon: icono});
};
// Mensaje para redirigir con texto
function MensajeRedirect(link, titulo, texto, icono) {
    Swal.fire({title: titulo, text: texto, icon: icono, confirmButtonText: 'Ok', allowOutsideClick: false
    }).then(function (result) {
        console.log(result);
        if (result.value) {
            window.location.href = link;
        }
    });
}