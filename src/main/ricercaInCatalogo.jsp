<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang=it>
<head>
<link rel="stylesheet" href="css//ricercaInCatalogo.css">
<meta charset="UTF-8">
<title>ricerca nel catalogo page</title>
</head>
<body>

<h1> Benvenuto nella sezione per ricercare </h1>

<h2> Inserire titolo/autore e selezionare id per visualizzarlo</h2>
<c:set var = "tipo" scope = "session" value = "${beanS.getType() }"/>

<form action="RicercaCatalogoServlet" method="post">
<c:choose>
<c:when test="${ tipo=='libro'}">
<div class="column">
titolo / autore da cercare nel catalogo dei libri:
<input type="text" id="cercaL" name="cercaL">
</div>
<div class="column">
<input type="submit" name="cercaB" id="cercaB" value="cerca" class="invia"> 
</div>
</c:when>
<c:when test="${tipo=='giornale' }">
<div class="column">
titolo / autore da cercare nel catalogo dei giornali:
<input type="text" id="cercaL" name="cercaL">
</div>
<div class="column">
<input type="submit" name="cercaB" id="cercaB" value="cerca" class="invia"> 
</div>
</c:when>
<c:when test="${tipo=='rivista'}">
<div class="column">
titolo / autore da cercare nel catalogo delle riviste:
<input type="text" id="cercaL" name="cercaL">
</div>
<div class="column">
<input type="submit" name="cercaB" id="cercaB" value="cerca" class="invia"> 
</div>
</c:when>
</c:choose>
<div class="column">
<table>
<caption>elenco dal db</caption>
<tr>
<th scope="col">
titolo
</th>
<th>
autore
</th>
<th>
id oggetto
</th>
</tr>
<c:forEach items="#{beanRicerca.lista }" var="lista">


<tr>

<c:choose>
<c:when test="${tipo=='libro' or tipo=='rivista' }">

<td>${ lista.getTitolo() }</td>
<td>${ lista.getAutore() }</td>
<td>${ lista.getId()}</td>
</c:when>

<c:when test="${tipo=='libro' or tipo=='rivista' }">
<td>${ lista.getTitolo() }</td>
<td> not available </td>
<td>${ lista.getId()}</td>
</c:when>
</c:choose>
</tr>
</c:forEach>
</table>
</div>

<div class="column">
<br>
<br>
visualizza oggetto
<input type="submit" name="visualizzaB" id="visualizzaB" value="visualizza" class="visualizza">


<br>
<br>
<br>
<br>
 <input type="submit" name="buttonI" id="buttonI" value="indietro" class="annulla">
 </div>

</form>

</body>
</html>