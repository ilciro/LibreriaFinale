<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=it>
<head>
<link rel="stylesheet" href="css/scrittore.css">
<meta charset="UTF-8">
<title>scrittore page</title>
</head>
<body>

<h1> Benvenuto!! Loggato come scrittore</h1>

<h2> Scegli cosa vuoi fare </h2>

<p> isLogged from bean : ${beanS.isLoggedB()}</p>


<form action="ServletScrittore" method="post">
<div>
<table>
<caption>
scegliere tra libri , giornali e riviste, gestione,cerca,logout e visualizza profilo
come scrittore
</caption>
<tr>

<th scope="col">
libro scrittore
</th>
<th>
giornale scrittore
</th>
<th>
rivista scrittore
</th>
<th>
gestione scrittore
</th>
<th>
ricerca scrittore
</th>
<th>
logout da scrittore
</th>
<th>
visualizza profilo scrittore
</th>
</tr>
<tr>
<td>
<img id ="libro scrittore" alt="source not found" src="immagini/libro.png_860.png" width=100  height=100 >
</td>
<td>
<img id ="giornale scrittore" alt="source not found" src="immagini/newspaper-284-542534.png" width=100  height=100 >
</td>
<td>
<img id ="rivista scrittore"alt="source not found" src="immagini/una-rivista_318-1607.jpg" width=100  height=100 >
</td>
<td>
<img id ="profilo scrittore" alt="source not found" src="immagini/vector-users-icon-png_302626.jpg" width=100  height=100 >
</td>
<td>
<img id ="cerca scrittore"alt="source not found" src="immagini/Search-icon-13.png" width=100  height=100 >
</td>
<td>
<img id ="logout scrittore" alt="source not found" src="immagini/icons8-logout-rounded-48.png" width=100  height=100 >
</td>
<td>
<img id ="libro scrittore" alt="source not found" src="immagini/Profile-PNG-High-Quality-Image-180x180.png" width=100  height=100>
</td>
</tr>
<tr>
<td>
<input type="submit"  id="buttonL" name="buttonL"  value="libri" class="libri">
</td>
<td>
<input type="submit" id="buttonG" name="buttonG"  value="giornali" class="giornali">
</td>
<td>
<input type="submit" id="buttonR" name="buttonR"  value="riviste" class="riviste">
</td>
<td>
<input type="submit" id="buttonGestione"  name="buttonGestione" value="gestione" class="gestione">
</td>
<td>
<input type="submit" id="buttonRic" name="buttonRic"  value="ricerca" class="ricerca">
</td>
<td>
<input type="submit" id="logoutB" name="logoutB"  value="logout" class="logout">
</td>
<td>
<input type="submit" id="profiloB" name="profiloB"  value="vai al profilo" class="profilo">
</td>
</tr>
</table>
</div>
</form>

</body>
</html>