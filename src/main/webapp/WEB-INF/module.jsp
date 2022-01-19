
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Module page</title>
</head>
<body>	<p>${professeur.getId()}</p>
		<p>${sessionScope.professeurSession.getId()}</p>
		
		<p>Veuillez enregistrer un module : </p>
		<form method="post" action="ModuleServlet">
			<p>
				<label for="titre">Titre</label>
				<input type="text" name="titre" id="titre">
			</p>
			<p>
				<label for="nb_heures_cours">Nombre Heures Cours :</label>
				<input type="text" name="nb_heures_cours" id="nb_heures_cours">
			</p>
			<p>
				<label for="nb_heures_td">Nombre Heures Td :</label>
				<input type="text" name="nb_heures_td" id="nb_heures_td">
			</p>
			<p>
				<label for="nb_heures_tp">Nombre Heures Tp :</label>
				<input type="text" name="nb_heures_tp" id="nb_heures_tp">
			</p>
			<input type="submit" value="Enregistrer">
		</form>
		<a href="/FirstServlet/ProfesseurServlet">Retour vers professeur</a>
</body>
</html>