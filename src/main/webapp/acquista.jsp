<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=it>
<head>
<link rel="stylesheet" href="css//acquista.css">

<meta charset="UTF-8">
<title>acquista page</title>
</head>
<body>
<h1>Benvenuto nella schermata per acquistare</h1>

<h2>Scegliere il metodo di pagamento</h2>


<form action="AcquistaServlet" method="post">
<table>
<caption></caption>
<tr>
<th scope="col">
</th>
</tr>
<tr>
<td>
titolo o nome oggetto :
</td>
<td>
<input type="text" id="nome" name="nome"  class="automatic" value="${bean1.getTitolo() }">
</td>

</tr>
<tr>
<td>
inserire quantita da acquistare :
</td>
<td>
<input type="text" id="quantita" name="quantita" value="${bean1.getQuantita() }">
</td>
</tr>
<tr>
<td>
totale da pagare :
</td>
<td>
<input type="text" id="totale" name="totale" value="${bean1.getSpesaT() }">
</td>
<td> 
<input type="submit" id="totaleB" name="totaleB" value="calcola">
</td>

</tr>
<tr>

<td>
<label for="metodoP">scegli metodo pagamento</label>
</td>
<td>
<input list="metodi" name="metodoP" id="metodoP">
<datalist id="metodi">
<option value="cash">
<option value="cCredito">
</option>
</datalist>

<tr>
<td>
<img alt="source not found" src="immagini/pngtree-shop-png-image_1699051.jpg" width=100  height=100 >
</td>
<td>
<img alt="source not found" src="immagini/icon.png" width=100  height=100 >

</td>
</tr>
<tr>
<td>
<input type="submit" id="negozioB" name="negozioB" value="ritiro in negozio">
</td>
<td>
<input type="submit" id="pdfB" name="pdfB" value="scarica il pdf">
</td>
</tr>

</table>
</form>

<div>
${beanA.getMex()}
</div>

</body>
</html>