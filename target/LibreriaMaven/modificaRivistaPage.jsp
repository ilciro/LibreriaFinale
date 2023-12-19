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

<p> paramteri </p>

<p> sysbena id : ${beanS.getIdB()}</p>
<p> bean id : ${beanR.getIdB()}</p>

<form action="ModificaRivistaServlet" method="post">

<div class="column">
titolo:
<input type="text" id="titoloR" name="titoloR" value="${beanR.getTitoloB() }">
<br>
<br>
categoria:
<input type="text" id="categoriaR" name="categoriaR" value="${beanR.getTipologiaB() }">
<br>
<br>
autore:
<input type="text" id="autoreR" name="autoreR" value="${beanR.getAutoreB() }">
<br>
<br>
lingua:
<input type="text" id="linguaR" name="linguaR" value="${beanR.getLinguaB() }">
<br>
<br>
editore:
<input type="text" id="editoreR" name="editoreR" value="${beanR.getEditoreB() }">
<br>
<br>
descrizione:
<label for="descR"></label>
<textarea rows="10" cols="50">
${beanR.getDescrizioneB() }
</textarea>
<br>
<br>
data pubblicazione:
<input type="text" id="dataR" name="dataR" value="${beanR.getDataB() }">
<br>
<br>
disponibilita:
<input type="text" id="dispL" name="dispL" value="${beanR.getDispB() }">
<br>
<br>
prezzo:
<input type="text" id="prezzoL" name="prezzoL" value="${beanR.getPrezzoB() }">
<br>
<br>
copieRimanenti :
<input type="text" id="copieR" name="copieR" value="${beanR.getCopieRimB() }">
<br>
<br>

<input type="submit" id="listaB" name="listaB" value="prendi dati" class="genera">


</div>
<div class="column">
elenco categorie possibili:
<label for="categoriePossibili"></label>
<textarea rows="10" cols="50" id="categoriePossibili" name="categoriePossibili" >
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
</div>
<div class="column">

titolo da aggiornare:
<input type="text" id="titoloNR" name="titoloNR">
<br>
<br>
categoria  da aggiornare:
<input type="text" id="categoriaNR" name="categoriaNR" >
<br>
<br>
autore da aggiornare:
<input type="text" id="autoreNR" name="autoreNR" >
<br>
<br>
lingua da aggiornare:
<input type="text" id="linguaNR" name="linguaNR" >
<br>
<br>
editore da aggiornare:
<input type="text" id="editoreNR" name="editoreNR" >
<br>
<br>
descrizione da aggiornare:
<label for="descNR"></label>
<textarea rows="10" cols="50" id="descNR" name="descNR">
</textarea>
<br>
<br>
data pubblicazione da aggiornare (usare il '/');
<br>
usare il formato ('mm/dd/yyyy')
<input type="text" id="dataNR" name="dataNR" >
<br>
<br>
disponibilita da aggiornare:
<input type="text" id="dispNR" name="dispNR">
<br>
<br>
prezzo da aggiornare:
<input type="text" id="prezzoNR" name="prezzoNR" >
<br>
<br>
copieRimanenti da aggiornare :
<input type="text" id="copieNR" name="copieNR" >
<br>
<br>

<input type="submit" id="buttonI" name="buttonI" value="aggiorna" class="invia">
<input type="submit" id="buttonA" name="buttonA" value="indietro" class="annulla">

</div>


</form>


</body>
</html>