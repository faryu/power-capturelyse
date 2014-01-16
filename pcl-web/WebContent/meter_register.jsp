<%@page import="entity.Energietyp"%>
<%@page import="de.whs.mwa.pcl.rs.Meter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Neuer Stromzähler</title>
</head>
<body>
	<form method="post" action="/pcl-web/address/${model.aid}/add_meter">
		Name: <input type="text" name="name" value="${model.name}" />
		${model.errors.name} <br /> Typ: <select name="type">
			<%			
				Meter model = (Meter) request.getAttribute("model");
				for (Energietyp t : model.getEnergieTypVerwaltung().showEnergietypen()) {
			%><option value="<%=t.getId_energietyp()%>"><%=t.getEnergietyp()%> [<%=t.getEinheit()%>]</option>
			<%
				}
			%>
		</select> ${model.errors.type} <br /> <input type="submit" />
	</form>
</body>
</html>