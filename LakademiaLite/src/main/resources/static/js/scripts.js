document.getElementById("fechaNac").addEventListener("blur", comprobarEdad);
document.getElementById("dni").addEventListener("blur", comprobarDni);
document.getElementById("email").addEventListener("blur", comprobarCorreo);
document.getElementById("password2").addEventListener("blur", comprobarPassword);

function revisarFormulario(){
	let resultado = false;
	resultado = comprobarEdad()&&
				comprobarDni()&&
				comprobarCorreo()&&
				comprobarPassword()

    registro.enviar.className = resultado ? "btn btn-success mb-2":"btn btn-danger mb-2";

	return resultado;
}


function comprobarEdad (){
    let error = "errorEdad";
    let fechaNacimiento = registro.fechaNac;
    let fecha = fechaNacimiento.valueAsDate;
    let fechaActual = new Date();
    let esAdulto = fechaNacimiento.value!="";
    if(esAdulto){
        esAdulto = fecha.getFullYear() + 18 <= fechaActual.getFullYear() 
    }
    modificarApariencia(esAdulto,fechaNacimiento,error);
    return esAdulto;
}

function comprobarDni(){
    let dni = registro.dni;
    let error="errorDni";
	let resultado = dni.value!=="";
	if(resultado){
		dni.value = dni.value.toUpperCase();
		let numDNI = dni.value.substring(0,8); 
		let letraDNI = dni.value.substring(8);
		let letras = ['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J',
		 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'];
		resultado = (numDNI > 0 && numDNI < 99999999) && (letras[numDNI%23]==letraDNI);					
	}
	modificarApariencia(resultado,dni,error);
	return resultado;
}

function comprobarCorreo(){
    let error = "errorCorreo";
    let correo = registro.email;
	let resultado = correo.value!=="";
	if(resultado){
		let partesCorreo = correo.value.split('@');
		resultado = partesCorreo.length==2;
		if(resultado){
			let partesDominio = partesCorreo[1].split('.');
			resultado = partesDominio.length>1;
		}			
	}
	modificarApariencia(resultado,correo,error)
	return resultado;
}

function comprobarPassword(){
    let error = "errorPwd";
	let pwd1 = registro.password;
	let pwd2 = registro.password2;
	let resultado = pwd1.value!=="" && pwd2.value!=="";
	if(pwd1.value !== pwd2.value){
		resultado = false;		
	}
	modificarApariencia(resultado,pwd2,error);
	return resultado;
}

function modificarApariencia (estado, campo, error){
    if(estado){
		campo.classList.add("border-success");
		campo.classList.remove("border-danger");
        document.getElementById(error).style.visibility='hidden';
	}
	else{
		campo.classList.add("border-danger");
		campo.classList.remove("border-success");
        document.getElementById(error).style.visibility='visible';
       
	}
}
