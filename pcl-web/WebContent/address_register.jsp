<%@page import="entity.Energietyp"%>
<%@page import="entity.Zaehler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Neue Adresse</title>
</head>
<body>
<p><a href="/pcl-web/home">Home</a></p>
<form method="post" action="/pcl-web/address/new">
        Strasse: <input type="text" name="strasse" value="${model.strasse}"/> ${model.errors.strasse}
        <br/>
        Plz: <input type="text" name="plz" value="${model.plz}"/> ${model.errors.plz}
        <br/>
        Ort: <input type="text" name="ort" value="${model.ort}"/> ${model.errors.ort}
        <br/>
        <input type="submit"/>
</form>
</body>
</html>