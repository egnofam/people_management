<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Bonjour ${nom} </p>
	<p>Vous avez ${age} ans </p>
		
	<p>Remplissez le formulaire</p>
	<form method="post" action ="HelloWorldServlets">
		<p>
			<label for="nom">Name :</label>
			<input type="text" id="nom" name="nom">
		</p>
		<p>
			<label for="age">Age :</label>
			<input type="number" id="nom" name="age">
		</p>
		<input type="submit" value="Envoyer">
	</form>
</body>
</html>