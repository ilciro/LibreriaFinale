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




<form action="InserisciRivistaServlet" method="post">
<div>
titolo :
<label for="titoloL"></label>
<input type="text" id="titoloL" name="titoloL">
<br>
<br>
tipologia:
<label for="catR"></label>
<textarea  cols=25 rows=5 id="catR" name="catR" disabled>
SETTIMANALE
BISETTIMANALE
MENSILE
BIMESTRALE
TRIMESTRALE
ANNUALE
ESTIVO
INVERNALE
SPORTIVO
CINEMATOGRAFICA
GOSSIP
TELEVISIVO
MILITARE
INFORMATICA
NON_VALIDO
</textarea>

<label for="catS"></label>
<input type="text" id="catS" name="catS">
<br>
<br>
autore:
<label for="autL"></label>
<input type="text" id="autL" name="autL">
<br>
<br>
lingua:
<label for="linguaL"></label>
<input type="text" id="linguaL" name="linguaL">
<br>
<br>
editore:
<label for="editoreL"></label>
<input type="text" id="editoreL" name="editoreL">
<br>
<br>
descrizione:
<label for="descL"></label>
<input type="text" id="descL" name="descL">
<br>
<br>
data pubb (yyyy/mm/dd):
<label for="dataL"></label>
<input type="text" id="dataL" name="dataL">
<br>
<br>
disponibilita (flaggare per renderlo disponibile):
<label for="checkL">
</label>
<input type="checkbox" id="checkL" name="checkL">
<br>
<br>
prezzo:
<label for="prezzoL"></label>
<input type="text" id="prezzoL" name="prezzoL">
<br>
<br>
copie rimanenti:
<label for="copieL"></label>
<input type="text" id="copieL" name="copieL">
<br>
<br>
<input type="submit" id="confermaB" name="confermaB" value="conferma" class="invia">
<input type="submit" id="annullaB" name="annullaB" value="indietro" class="annulla">

</div>
</form>

</body>
</html>