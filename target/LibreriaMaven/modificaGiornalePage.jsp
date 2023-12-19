<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<p> parametri </p>
<p> sysbean id : ${beanS.getIdB()}</p>
<p> beanG id : ${beanG.getIdB()}</p>
<p> data : ${beanG.getDataB()}</p>

<form action="ModificaGiornaleServlet" method="post">


<div class="columnG">
titolo:
<input type="text" id="titoloG" name="titoloG" value="${beanG.getTitoloB()}">
<br>
<br>
tipologia:
<input type="text" id="tipoG" name="tipoG" value="QUOTIDIANO">
<br>
<br>
lingua:
<input type="text" id="linguaG" name="linguaG" value="${beanG.getLinguaB() }">
<br>
<br>
editore:
<input type="text" id="editoreG" name="editoreG" value="${beanG.getEditoreB() }">
<br>
<br>
data pubb (yyyy/mm/dd) :
<input type="text" id="dataG" name="dataG" value="${beanG.getDataB() }">
<br>
<br>
copie rimanenti:
<input type="text" id="copieG" name="copieG" value="${beanG.getCopieRimanentiB() }">
<br>
<br>
disponibilita (0->no 1->si):
<input type="text" id="dispG" name="dispG" value="${beanG.getDisponibilitaB() }">
<br>
<br>
prezzo:
<input type="text" id="prezzoG" name="prezzoG" value="${beanG.getPrezzoB() }">
<br>
<br>
<input type="submit" id="buttonG" name="buttonG" value="prendi dati" class="genera">

</div>
<div class="columnG">

titolo da modificare:
<input type="text" id="titoloNG" name="titoloNG">
<br>
<br>
tipologia da modificare:
<input type="text" id="tipoG" name="tipoG" value="QUOTIDIANO" disabled>
<br>
<br>
lingua da modificare:
<input type="text" id="linguaNG" name="linguaNG">
<br>
<br>
editore da modificare:
<input type="text" id="editoreNG" name="editoreNG" >
<br>
<br>
data pubb (yyyy/mm/dd) da modificare :
<input type="text" id="dataNG" name="dataNG" >
<br>
<br>
copie rimanenti da modificare:
<input type="text" id="copieNG" name="copieNG" >
<br>
<br>
disponibilita (0->no 1->si) da modificare:
<input type="text" id="dispNG" name="dispNG" >
<br>
<br>
prezzo da modificare:
<input type="text" id="prezzoNG" name="prezzoNG">
<br>
<br>
<br>
<br>
<input type="submit" id="confermaB" name="confermaB" value="conferma" class="invia">
<input type="submit" id="annullaB" name="annullaB" value="indietro" class="annulla">
</div>

</form>



</body>
</html>