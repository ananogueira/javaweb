/**
 * Validação de formulário
 * 
 * @author Ana Nogueira
 */

function validar() {
	let nome = frmContacto.nome.value
	let telemovel = frmContacto.telemovel.value
	if (nome === "") {
		alert('Preencha o campo Nome')
		frmContacto.nome.focus();
		return false
	} else if (telemovel === "") {
		alert('Preencha o campo Telemóvel')
		frmContacto.telemovel.focus();
		return false
	} else {
		document.forms["frmContacto"].submit()
	}
}