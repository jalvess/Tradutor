$(document).ready(function() {
	$('#btn_enviar').on('click', traduzir);
	$('#btn_add_word').on("click", toggleNewWordForm);
	$('#tradutor').on("keyup", toggleBtnNewWord).on("keyup",limpaResultado);
	$('#btn-theme').on("click", toggleDarkTheme);
	$('#btn_idioma').on("click", toggleLanguage);
	$('#btn_send_new').on("click", sendNewWord);
	$('#translations').on('keyup blur', verifyFormNewWord);
	$('#new_word').on('keyup blur', verifyFormNewWord);
});
function limpaResultado(){
	$('#resultado ul').children().remove();
	$('#resultado').addClass('hidden');
}

function traduzir(e) {
	e.preventDefault();
	$('#status').removeClass('invisible');
	$('#status').text("Traduzindo...");
	$.ajax({
		url : 'traduzir',
		method : 'GET',
		data : $('#form_traduzir').serialize(),
		success : function(data) {
			var res = data.replace(/[\[\]]/g,"").split(',');
			$('#status').addClass("invisible");
			$('#status').text("Enviando...");
			$('#resultado').removeClass("hidden");
			res.forEach(function(t){
				$('#resultado ul').append($('<li />').text(t.trim()));
			})
		},
		error: function(data) {
			console.log(data);
			if(data.status == 415)
			$('#status').text("Palavra não encontrada");
			$('#status').addClass("error");
			setTimeout(function(){
				$('#status').addClass("invisible");
				$('#status').removeClass("error");
				$('#status').text("Enviando...");
			},6000);
		}
	});
}

function verifyFormNewWord() {
	if ($(this).val() == '' || $(this).prop('validity').patternMismatch
			|| $(this).prop('validity').valueMissing) {
		if (!$(this).hasClass('input_warning')) {
			$(this).addClass('input_warning');
		}
	} else {
		if ($(this).hasClass('input_warning')) {
			$(this).removeClass('input_warning');
		}
	}
	isNewWordBtnSubmitValid();
}

function isNewWordBtnSubmitValid() {
	var isValido = $('#form_newword')[0].checkValidity();
	if (!isValido) {
		disableBtn('#btn_send_new', true);
	} else {
		disableBtn('#btn_send_new', false);
	}
	return isValido;
}

function disableBtn(btn, desativa) {
	$(btn).prop("disabled", desativa);
}

function sendNewWord(e) {
	e.preventDefault();
	if (isNewWordBtnSubmitValid()) {
		$('#status').removeClass('invisible');
		disableBtn('#btn_send_new', true);
		$.ajax({
			url : 'traduzir',
			method : 'post',
			processData : false,
			data : $('#form_newword').serialize(),
			success : function() {
				$('#status').text('Palavra enviada!');
				$('#status').addClass('success');
				$('#status').removeClass('carregando');
				setTimeout(function() {
					$('#status').text('Enviando...');
					$('#status').removeClass('success');
					$('#status').addClass('invisible carregando');
				}, 6000);
				disableBtn('#btn_send_new', false);
			},
			error : function(info) {
				if (info.status == 415) {// parametros vazios
					if ($('#new_word').val() == '') {
						$('#new_word').addClass('input_warning');
					}
					if ($('#translations').val() == '') {
						$('#translations').addClass('input_warning');
					}
				} else {
					$('#status').text('Erro no servidor');
					$('#status').addClass('error');
				}
			}
		});
	}
}

function toggleLanguage(e) {
	e.preventDefault();
	if ($("input[name=from_pt]").prop("checked")) {
		$("#lbl_tradutor").html(
				"Palavra para traduzir do portugu&ecirc;s para o ingl&ecirc;s");
	} else {
		$("#lbl_tradutor").html(
				"Palavra para traduzir do ingl&ecirc;s para o portugu&ecirc;s");
	}
	$("input[name=from_pt]").prop("checked",
			!$("input[name=from_pt]").prop("checked"));
}

function toggleNewWordForm() {
	$('#form_newword').toggleClass('hidden');
	$('#form_traduzir').toggleClass('hidden');
	if ($('#form_newword').hasClass('hidden')) {
		$('#btn_add_word').text("Adicionar uma nova palavra");
	} else {
		$('#btn_add_word').text("Ocultar menu de nova palavra");
	}
}

function toggleBtnNewWord() {
	if ($(this).val())
		$('#btn_add_word').addClass('hidden');
	else
		$('#btn_add_word').removeClass('hidden');
}

function toggleDarkTheme() {
	$('body, h1, label, .btn, #resultado, #status').toggleClass('dark');
	if ($('body, h1, label, .btn').hasClass('dark')) {
		$('#btn-theme').text("Mudar para Tema Claro");
	} else {
		$('#btn-theme').text("Mudar para Tema Escuro");
	}
}