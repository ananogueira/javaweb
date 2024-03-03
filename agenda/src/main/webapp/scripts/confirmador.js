/**
 * Confirmação de eliminação de um contacto
 * 
 * @author: Ana Nogueira
 * @param idcon
 */

function confirmar(idcon) {
    let resposta = confirm("Confirma a eliminação deste contacto?")
    if (resposta === true) {
        //alert(idcon)
        // Passo 23.2
        window.location.href = "delete?idcon=" + idcon
    }
}