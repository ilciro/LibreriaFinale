<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang=it>
<head>
<link rel="stylesheet" href="css//riviste.css">
<meta charset="UTF-8">
<title>pagina riviste</title>
</head>
<body>
<h1> Elenco oggetti prensenti nel db</h1>

<form action="RivisteServlet" method="post">
<table>
<caption>riepilogo rivista</caption>
<tr>
<th>
titolo
</th>
<th>
tipologia
</th>
<th>
autore
</th>
<th>
lingua
</th>
<th>
editore
</th>
<th>
data pubblicazione
</th>
<th>
disp
</th>
<th>
prezzo
</th>
<th>
copie rimanenti
</th>
<th>
id
</th>
<c:forEach items="#{beanR.listaRivisteB }" var="lista">


<tr>


<td>${ lista.getTitoloB() }</td>
<td>${ lista.getTipologiaB() }</td>
<td>${ lista.getAutoreB() }</td>
<td>${ lista.getLinguaB() }</td>
<td>${ lista.getEditoreB() }</td>
<td>${ lista.getDataPubbB()}</td>
<td>${ lista.getDispB() }</td>
<td>${ lista.getPrezzoB() }</td>
<td>${ lista.getCopieRimB() }</td>
<td>${ lista.getIdB()}</td>

</tr>
</c:forEach>


</table>
<br>
<br>
<div>

Inserire id di oggetto scelto:


<input type="text" id="idOgg" name="idOgg">
<br>
<br>



<input type="submit" class="invia" id="procedi" name="procedi" value="procedi">

<input type="submit" class="genera" id="genera lista" name="genera lista" value="genera lista" >

<input type="submit" class="annulla" id="annulla" name="annulla" value="indietro">
<br>
</div>

</form>

</body>
</html>