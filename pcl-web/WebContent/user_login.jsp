<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<form method="post" action="/pcl-web/user/login">
        Username: <input type="text" name="uname" value="${model.uname}"/> ${model.errors.uname}
        <br/>
        Password: <input type="password" name="password" value="${model.password}"/> ${model.errors.password}
        <br/>
        <input type="submit" value="Login"/>
</form>
</body>
</html>