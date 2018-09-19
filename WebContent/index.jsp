<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tradutor</title>
<link rel="stylesheet" href="./css/estilo-minificado.css" />
<link rel="dns-prefetch" href="https://code.jquery.com/">
<!-- <link rel="prefetch" href="image.png"> -->
</head>
<body>
	<button class="btn" id="btn-theme">Mudar para Tema Escuro</button>
	<div id="tbl_container">
		<main>
		<h1>Tradutor</h1>
		<form action="traduzir" id="form_traduzir" autocomplete="off">
			<button id="btn_idioma" class="link" aria-role="button">Inverter
				idioma</button>
			<input type="checkbox" name="from_pt" id="from_pt" /> <label
				for="tradutor" id="lbl_tradutor">Palavra para traduzir do
				português para o inglês</label> <input type="text" id="tradutor"
				class="_input" placeholder="Palavra a ser traduzida" name="palavra" required>
			<input type="submit" id="btn_enviar" value="Traduzir"
				class="btn " />
		</form>
		<div id="resultado" class="hidden">
			<h4>Resultados</h4>
			<ul>
			</ul>
		</div>
		<button class="btn" id="btn_add_word">Adicionar uma nova
			palavra</button>
		<form action="traduzir" id="form_newword" method="post" class="hidden" autocomplete="off">
			<input type="text" id="new_word" name="new_word"
				placeholder="Nova palavra" class="_input" pattern="^[a-zA-ZáàãâÀÁÃÂéèêÉÈÊíÍiÌòÒÔôúÚ]+(?<=[a-záàãâÀÁÃÂéèêÉÈÊíÍiÌòÒÔôúÚ])\s?[a-zA-ZáàãâÀÁÃÂéèêÉÈÊíÍiÌòÒÔôúÚ]+$" required /> <select name="idioma"
				class="_input">
				<option value="Portugu&ecirc;s">Idioma da palavra
					(Portugu&ecirc;s)</option>
				<option value="Ingl&ecirc;s">Idioma da palavra
					(Ingl&ecirc;s)</option>
			</select> <input type="text" name="translations" id="translations"
				placeholder="Significado1, Significado2, SignificadoN" title="Exemplo: Significado1, Significado2, SignificadoN" pattern="^[a-zA-ZáàãâÀÁÃÂéèêÉÈÊíÍiÌòÒÔôúÚ]+[a-zA-ZáàãâÀÁÃÂéèêÉÈÊíÍiÌòÒÔôúÚ,]*(?<=[A-Za-záàãâÀÁÃÂéèêÉÈÊíÍiÌòÒÔôúÚ])$"
				class="_input" required> <input type="submit" class="btn _input" 
				value="Enviar nova palavra" id="btn_send_new" />
		</form>
		<span id="status" class="invisible carregando">Enviando...</span>
		</main>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="./js/script-minificado.js"></script>
</body>
</html>