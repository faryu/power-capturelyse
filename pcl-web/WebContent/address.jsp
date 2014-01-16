<%@page import="entity.Zaehler"%>
<%@page import="entity.Adresse"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adresse</title>
</head>
<body>
<h2>${model.strasse}, ${model.plz} ${model.ort}</h3>
<p>
<h3>Zähler:</h3>
<% 
Adresse model = (Adresse)request.getAttribute("model");
%>Anzahl: <%=model.getZaehler().size() %><br><%
for(Zaehler a : model.getZaehler())
{
	%><a href="/pcl-web/meter/<%=a.getId_zaehler()%>"><%=a.getZaehlername()%>, <%=a.getEnergietyp().getEnergietyp()%></a><br><%
}
%>
<br><a href="/pcl-web/address/<%=model.getId_adresse()%>/add_meter">Erstelle neuen Zähler</a>
</p>
</body>
</html>