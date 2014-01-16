<%@page import="entity.Energietyp"%>
<%@page import="interfaces.EnergietypVerwaltungInterface"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Energietypenverwaltung</title>
</head>
<body>
<h3>Aktuell verfügbar:</h3>
<% 
EnergietypVerwaltungInterface model = (EnergietypVerwaltungInterface)request.getAttribute("model");
List<Energietyp> typen = model.showEnergietypen();
for(Energietyp a : typen)
{
	%><%=a.getEnergietyp()%> [<%=a.getEinheit()%>]<br><%
}
%>
<br><a href="/pcl-web/type/new">Erstelle neuen Energietypen</a>
</body>
</html>