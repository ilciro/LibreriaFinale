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


 
<c:set var = "tipo" scope = "session" value = "${bean1.getType() }"/>
<form action ="InserisciOggettoServlet" method="post">

<div>
<c:choose>
<c:when test="${ tipo=='libro'}">
titolo:
<label for="titoloL"></label>
<input type="text" id="titoloL" name="titoloL">
<br>
<br>
numero pagine
<label for="nrPagL"></label>
<input type="text" id="nrPagL" name="nrPagL">
<br>
<br>
codice isbn
<label for="codL"></label>
<input type="text" id="codL" name="codL">
<br>
<br>
autore
<label for="autoreL"></label>
<input type="text" id="autoreL" name="autoreL">
<br>
<br>
editore
<label for="editoreL"></label>
<input type="text" id="editoreL" name="editoreL">
<br>
<br>
lingua
<label for="linguaL"></label>
<input type="text" id="linguaL" name="linguaL">
<br>
<br>

<p>
categoria
<label for="catL"></label>
<textarea  cols=25 rows=5 id="catL" name="catL" readonly>
${beanMOB.getTipologiaL()}
</textarea> 
<input type="submit" id="generaL" name="generaL" value="prendi lista">
<label for="catS"></label>
<input type="text" id="catS" name="catS">
</p>
<br>
<br>
data pubb (yyyy/mm/dd)
<label for="dataL"></label>
<input type="text" id="dataL" name="dataL">
<br>
<br>
recensione
<label for="recensioneL"></label>
<input type="text" id="recensioneL" name="recensioneL">
<br>
<br>
descrizione
<label for="descL"></label>
<input type="text" id="descL" name="descL">
<br>
<br>
disponibilita (flaggare per renderlo disponibile)
<label for="checkL">
</label>
<input type="checkbox" id="checkL" name="checkL">
<br>
<br>
prezzo
<label for="prezzoL"></label>
<input type="text" id="prezzoL" name="prezzoL">
<br>
<br>
copie rimanenti
<label for="copieL"></label>
<input type="text" id="copieL" name="copieL">
<br>
<br>

<input type="submit" id="confermaB" name="confermaB" value="conferma" class="invia">
<input type="submit" id="annullaB" name="annullaB" value="indietro" class="annulla">

</c:when>
</c:choose>
</div>
</form>

<form action="InserisciOggettoServletGiornale" method="post">
<div>
<c:choose>
<c:when test="${ tipo=='giornale'}">
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
data pubb (yyyy/mm/dd) :
<label for="dataG"></label>
<input type="text" id="dataG" name="dataG">
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
</c:when>
</c:choose>
</div>
</form>

<form action="InserisciOggettoServletRivista" method="post">
<div>
<c:choose>
<c:when test="${ tipo=='rivista'}">
titolo :
<label for="titoloL"></label>
<input type="text" id="titoloL" name="titoloL">
<br>
<br>
tipologia:
<label for="catR"></label>
<textarea  cols=25 rows=5 id="catR" name="catR" disabled>
${bean.getListaCategorie()}
</textarea>

<input type="submit" id="generaL" name="generaL" value="prendi lista">

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

</c:when>
</c:choose>
</div>
</form>

</body>
</html>