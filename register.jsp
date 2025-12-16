<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>
<html>
<head><title>Register</title></head>
<body>
<h2>Register</h2>
<s:form action="register" method="post">
    <s:textfield name="username" label="Username"/><br/>
    <s:password name="password" label="Password"/><br/>
    <s:textfield name="email" label="Email"/><br/>
    <s:submit value="Register"/>
</s:form>
<s:actionerror/>
</body>
</html>
