<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register user</title>
</head>
<body>
<p><a href="/pcl-web">Startseite</a></p>
<form method="post" action="/pcl-web/user/register">
        Username: <input type="text" name="uname" value="${model.uname}"/> ${model.errors.uname}
        <br/>
        Password: <input type="password" name="password" value="${model.password}"/> ${model.errors.password}
        <br/>
        Vorname:  <input type="text" name="vname" value="${model.vname}"/> ${model.errors.vname}
        <br/>
        Nachname: <input type="text" name="name" value="${model.name}"/> ${model.errors.name}
        <br/>
        <input type="submit"/>
</form>
</body>
</html>