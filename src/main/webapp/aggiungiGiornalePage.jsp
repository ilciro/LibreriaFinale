 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<!DOCTYPE html>
<html lang=en-it>
<head>
<meta charset="ISO-8859-1">
<title>insert new object page</title>
</head>
<link rel="stylesheet" href="css//aggiungiOggetto.css">

<body>
<h1>Inserire un nuovo oggetto</h1>

<h2> compilare il form</h2>


<form action="InserisciGiornaleServlet" method="post">
<div>

titolo:
<label for="titoloG"></label>
<input type="text" id="titoloG" name="titoloG">
<br>
<br>
tipologia:
<label for="tipoG"></label>
<input type="text" id="tipoG" name="tipoG" value="QUOTIDIANO">
<br>
<br>
lingua:
<label for="linguaG"></label>
<input type="text" id="linguaG" name="linguaG">
<br>
<br>
editore:
<label for="editoreG"></label>
<input type="text" id="editoreG" name="editoreG">
<br>
<br>
data pubb (yyyy/mm/dd)
<label for="dataG"></label>
<input type="text" id="dataG" name="dataG">
<br>
<br>
<br>
copie rimanenti:
<label for="copieG"></label>
<input type="text" id="copieG" name="copieG">
<br>
<br>
disponibilita (0->no 1->si):
<label for="dispG"></label>
<input type="text" id="dispG" name="dispG">
<br>
<br>
prezzo:
<label for="prezzoG"></label>
<input type="text" id="prezzoG" name="prezzoG">
<br>
<br>
<input type="submit" id="confermaB" name="confermaB" value="conferma" class="invia">
<input type="submit" id="annullaB" name="annullaB" value="indietro" class="annulla">

</div>
</form>

</body>
</html>