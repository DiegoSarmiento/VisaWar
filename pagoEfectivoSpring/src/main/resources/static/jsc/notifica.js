var requeridosIncorrectos=0;

// Validación de requerido en evento onInput
document.getElementById("PE-Signature").oninput = function(){ esRequerido("PE-Signature", "PE-SignatureMensaje", "Indique la url de PagoEfectivo"); };
document.getElementById("requestBody").oninput = function(){ esRequerido("requestBody", "requestBodyMensaje", "Cargue la requestBody"); };

// Validación de requerido en evento onInvalid
document.getElementById("PE-Signature").oninvalid = function(){ esRequerido("PE-Signature", "PE-SignatureMensaje", "Indique la url de PagoEfectivo"); };
document.getElementById("requestBody").oninvalid = function(){ esRequerido("requestBody", "requestBodyMensaje", "Cargue la requestBody"); };

// Permite visualizar un mensaje en caso el control sea requerido
function esRequerido(input, inputMensaje, mensajeRequerido)
{
	if(document.getElementById(input).value.length==0)
	{ document.getElementById(inputMensaje).innerHTML = mensajeRequerido; requeridosIncorrectos++; }
	else 
	{ document.getElementById(inputMensaje).innerHTML = ""; }
}

// Validar si todos los controles requeridos se encuentran llenos
function validarRequerido()
{
	requeridosIncorrectos=0;
		
	esRequerido("PE-Signature", "PE-SignatureMensaje", "Indique el PE-Signature"); 
	esRequerido("requestBody", "requestBodyMensaje", "Indique el Request Body");

	return requeridosIncorrectos;
}

//Limpiar los controles
document.getElementById("btnLimpiar").onclick = function(){  

	document.getElementById("PE-Signature").value="";
	document.getElementById("requestBody").value="";

}

//Enviar formulario a servidor
document.getElementById("btnValidar").onclick = function(){  

	if(validarRequerido()==0)
	{
		document.getElementById("notificaFormulario").setAttribute("method", "post"); 
		document.getElementById("notificaFormulario").submit();
	}
}
