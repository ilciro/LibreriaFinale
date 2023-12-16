<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang=it>
<head>
<link rel="stylesheet" href="css//profilo.css">
<meta charset="UTF-8">
<title>Profilo page</title>
</head>
<body>

<h1>Benvenuto nella schermata del profilo</h1>

<h2> Scegliere cosa fare</h2>

<p> parametri :</p>

<p> email : ${beanUb.getEmailB()}</p>
<p> ruolo : ${beanUb.getRuoloB()}</p>

<form action="ProfiloServlet" method="post">

<c:set var = "tipo" scope = "session" value = "${beanUb.getRuoloB() }"/>

<c:choose>
<c:when test="${ tipo=='A'}">


<table>
<caption>
elenco azioni possibili
</caption>

<tr>
<td>

<textarea id="utenti" name="utenti" rows="25" cols="85" class="textareaProp">
${beanUb.getStringB()}
</textarea>
</td>

<td>
<input type="submit" class="prendiDati" id="genera lista" name="genera lista" value="genera lista" >
</td>
<td>
<input type="submit" class="nuovoUtente" id="inserisci" name="inserisci" value="nuovo utente">
</td>
<td>
<input type="text" id="idOgg" name="idOgg">
<br>
<input type="submit" class="modifica" id="modifica" name="modifica" value="modifica utente">
</td>
<td>
<input type="submit" class="cancella" id="elimina" name="elimina" value="cancella utente">
</td>
<td>
<input type="submit" class="annulla" id="indietro" name="indietro" value="indietro">
</td>
</tr>
</table>


</c:when>
</c:choose>



</form>

</body>
</html>