
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>${professeur.getId()}</p>
	<p>${sessionScope.professeurSession.getId()}</p>
	<h3>Enregistrez le professeur</h3>
	<form method="post" action="ProfesseurServlet">
		<p>
			<label for="id"> Nummatricule :</label>
			<input type="text" name="id" id="id">
		</p>
		<p>
			<label for="nom"> Nom :</label>
			<input type="text" name="nom" id="nom">
		</p>
		<p>
			<label for="prenom"> Prenom :</label>
			<input type="text" name="prenom" id="prenom">
		</p>
		<p>
			<label for="dateNaissance"> Date Naissance :</label>
			<input type="text" name="dateNaissance" id="dateNaissance">
		</p>
		<p>
			<label for="diplome">Diplome :</label>
			<input type="text" name="diplome" id="diplome">
		</p>
	
		<input type="submit" value="Enregistrer">
	</form>
	<a href="/FirstServlet/ModuleServlet"> Aller a module</a>
</body>
</html>