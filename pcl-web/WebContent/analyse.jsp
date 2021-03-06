<%@page import="entity.Verbrauch"%>
<%@page import="de.whs.mwa.pcl.rs.Analyse"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Analyse</title>
</head>
<body>
<p><a href="/pcl-web/home">Home</a></p>
<h2>Zähler</h3>
<h3>${model.zaehler.zaehlername} (${model.zaehler.energietyp.energietyp} [${model.zaehler.energietyp.einheit}])</h3>
<p>
<form method="post" action="/pcl-web/meter/${model.zaehler.id_zaehler}/analyse">
<input type="submit" value="Analysiere"/> die Zählerstande zwischen dem 
<input type="text" name="from" value="${model.from}"/>
 und dem 
 <input type="text" name="to" value="${model.to}"/>
</form>
</p>
<%
Analyse model = (Analyse)request.getAttribute("model");
if(model != null && model.getVerbraeuche() != null && !model.getVerbraeuche().isEmpty())
{
	%><p>
	Gesamtverbrauch im Intervall: ${model.total} ${model.zaehler.energietyp.einheit}<br/>
	Tagesverbrauch im Intervall: ${model.avg} ${model.zaehler.energietyp.einheit}
	</p>
	<table><tr><th>Datum</th><th>Zählerstand</th></tr>
	<%
	for(Verbrauch v : model.getVerbraeuche())
	{
		%><tr><td><%=v.getDatum()%></td><td><%=v.getZaehlerstand()%></td></tr><%
	}
	%></table><%
}
%>
</body>
</html>