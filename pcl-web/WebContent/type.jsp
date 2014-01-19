<%@page import="entity.Energietyp"%>
<%@page import="interfaces.EnergietypVerwaltungInterface"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Energietypenverwaltung</title>
</head>
<body>
<p><a href="/pcl-web">Startseite</a></p>
<h3>Aktuell verfügbar:</h3>
<% 
EnergietypVerwaltungInterface model = (EnergietypVerwaltungInterface)request.getAttribute("model");
for(Energietyp a : model.showEnergietypen())
{
	%><%=a.getEnergietyp()%> [<%=a.getEinheit()%>]<br><%
}
%>
<br><a href="/pcl-web/type/new">Erstelle neuen Energietypen</a>
</body>
</html>