<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang=it>
<head>
<link rel="stylesheet" href="css//modificaOggetto.css">
<meta charset="UTF-8">
<title>modif object page</title>
</head>
<body>
<h1>Modificare oggetto scelto</h1>

<h2> Scegliere i campi da modificare</h2>

<p> paramteri: </p>
<p> id da sysrtem : ${beanS.getIdB()}</p>
<p> id da libro bean : ${beanL.getIdB()}</p>


<form action="ModificaLibroServlet" method="post">


<div class="column">
titolo:
<input type="text" id="titoloL" name="titoloL" value="${beanL.getTitoloB() }">
<br>
<br>
numero pagine:
<input type="text" id="pagineL" name="pagineL" value="${beanL.getNumeroPagineB()}">
<br>
<br>
codice isbn:
<input type="text" id="codiceL" name="codiceL" value="${beanL.getCodIsbnB() }">
<br>
<br>
editore:
<input type="text" id="editoreL" name="editoreL" value="${beanL.getEditoreB() }">
<br>
<br>
autore:
<input type="text" id="autoreL" name="autoreL" value="${beanL.getAutoreB() }">
<br>
<br>
lingua:
<input type="text" id="linguaL" name="linguaL" value="${beanL.getLinguaB() }">
<br>
<br>
categoria:
<input type="text" id="categoriaL" name="categoriaL" value="${beanL.getCategoriaB() }">
<br>
<br>
data pubblicazione
<input type="text" id="dataL" name="dataL" value="${beanL.getDateB()}">
<br>
<br>
recensione:
<label for="recL"></label>
<textarea rows="10" cols="50" id="recL" name="recL">
${beanL.getRecensioneB() }
</textarea>
<br>
<br>
nr copie rimanenti:
<input type="text" id="copieL" name="copieL" value="${beanL.getNrCopieB() }">
<br>
<br>
descrizione :
<label for="descL"></label>
<textarea rows="10" cols="50" name="descL" id="descL">
${beanL.getDescB() }</textarea>
<br>
<br>
disponibilita:
<input type="text" id="dispL" name="dispL" value="${beanL.getDisponibilitaB() }">
<br>
<br>
prezzo:
<input type="text" id="prezzoL" name="prezzoL" value="${beanL.getPrezzoB() }">
<br>
<br>

<input type="submit" id="listaB" name="listaB" value="prendi dati" class="genera">


</div>
<div class="column">
elenco categorie possibili:
<label for="categoriePossibili"></label>
<textarea rows="10" cols="50" id="categoriePossibili" name="categoriePossibili" >
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

</div>
<div class="column">
 titolo da aggiornare:
 <input type="text" id="titoloNL" name="titoloNL">
 <br>
<br>
numero pagine da aggiornare:
<input type="text" id="pagineNL" name="pagineNL" >
<br>
<br>
codice isbn da aggiornare:
<input type="text" id="codiceNL" name="codiceNL" >
<br>
<br>
editore da aggiornare:
<input type="text" id="editoreNL" name="editoreNL" >
<br>
<br>
autore da aggiornare:
<input type="text" id="autoreNL" name="autoreNL" >
<br>
<br>
lingua da aggiornare:
<input type="text" id="linguaNL" name="linguaNL">
<br>
<br>
categoria da aggiornare:
<input type="text" id="categoriaNL" name="categoriaNL" >
<br>
<br>
data pubblicazione da aggiornare (usare il '/');
<br>
usare il formato ('mm/dd/yyyy')
<input type="text" id="dataNL" name="dataNL">
<br>
<br>
recensione da aggiornare:
<label for="recNL"></label>
<textarea rows="10" cols="50" id="recNL" name="recNL">
</textarea>
<br>
<br>
nr copie rimanenti da aggiornare:
<input type="text" id="copieNL" name="copieNL" >
<br>
<br>
descrizione da aggiornare:
<label for="descNL"></label>
<textarea rows="10" cols="50" name="descNL" id="descNL">
</textarea>
<br>
<br>
disponibilita da aggiornare:
<input type="text" id="dispNL" name="dispNL" >
<br>
<br>
prezzo da aggiornare:
<input type="text" id="prezzoNL" name="prezzoNL" >
<br>
<br>

<input type="submit" id="buttonI" name="buttonI" value="aggiorna" class="invia">
<input type="submit" id="buttonA" name="buttonA" value="indietro" class="annulla">

</div>

</form>


</body>
</html>