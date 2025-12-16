<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Login</h2>
<s:form action="login" method="post">
    <s:textfield name="username" label="Username"/><br/>
    <s:password name="password" label="Password"/><br/>
    <s:submit value="Login"/>
</s:form>
<s:actionerror/>
</body>
</html>
