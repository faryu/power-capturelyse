
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
<h2>Hallo ${model.uservname} ${model.username}</h2>
<p>
<h3>Adressen:</h3>
<% 
entity.User model = (entity.User)request.getAttribute("model");
%>Adressen: <%=model.getAdresse().size() %><br><%
for(entity.Adresse a : model.getAdresse())
{
	%><a href="/pcl-web/address/<%=a.getId_adresse()%>"><%=a.getStrasse()%>, <%=a.getPlz()%> <%=a.getOrt()%></a><br><%
}
%>
<br><a href="/pcl-web/address/new">Erstelle neue Adresse</a>
</p>
</body>
</html>