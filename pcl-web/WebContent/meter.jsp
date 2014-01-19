<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="entity.Verbrauch"%>
<%@page import="entity.Zaehler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zaehler</title>
</head>
<body>
<p><a href="/pcl-web/home">Home</a></p>
<h2>Zähler</h3>
<h3>${model.zaehlername} (${model.energietyp.energietyp} [${model.energietyp.einheit}])</h3> <p><a href="/pcl-web/meter/${model.id_zaehler}/analyse">Zur Analyse</a></p>
<p>
<form method="post" action="/pcl-web/meter/${model.id_zaehler}/add_reading">
Der Zählerstand <input type="text" name="reading" /> wurde am 
<input type="text" name="date" value="<%=new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date())%>"/>
<input type="submit" value="abgelesen"/>
</form>
</p>
<table><tr><th>Datum</th><th>Zählerstand</th></tr>
<%
Zaehler model = (Zaehler)request.getAttribute("model");
for(Verbrauch v : model.getVerbrauch())
{
	%><tr><td><%=v.getDatum()%></td><td><%=v.getZaehlerstand()%></td></tr><%
}
%>
</table>
</body>
</html>