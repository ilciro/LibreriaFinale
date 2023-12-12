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


<p> id oggetto </p>
<p>libro bean : ${beanL.getIdB()}</p>
<p> sysbean :${beanS.getIdB()}</p>
<p> type: ${beanS.getTypeB() }</p>


<form action ="InserisciLibroServlet" method="post">

<div>

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
ADOLESCENTI_RAGAZZI
ARTE
CINEMA_FOTOGRAFIA
BIOGRAFIE
DIARI_MEMORIE
CALENDARI_AGENDE
DIRITTO
DIZINARI_OPERE
ECONOMIA
FAMIGLIA
SALUTE_BENESSERE
FANTASCIENZA_FANTASY
FUMETTI_MANGA
GIALLI_THRILLER
COMPUTER_GIOCHI
HUMOR
INFORMATICA
WEB_DIGITAL_MEDIA
LETTERATURA_NARRATIVA
LIBRI_BAMBINI
LIBRI_SCOLASTICI
LIBRI_UNIVERSITARI
RICETTARI_GENERALI
LINGUISTICA_SCRITTURA
POLITICA
RELIGIONE
ROMANZI_ROSA
SCIENZE
TECNOLOGIA_MEDICINA
NON_VALIDO
</textarea>

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


</div>
</form>

</body>
</html>