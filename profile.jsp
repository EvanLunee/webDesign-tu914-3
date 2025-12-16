<%@ page session="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>

<html>
<head><title>User Profile</title></head>
<body>
<h2>User Profile</h2>

<s:if test="otherUser != null">
    <p>Username: <s:property value="otherUser.username"/></p>
    <p>Email: <s:property value="otherUser.email"/></p>
</s:if>

<s:elseif test="user != null">
    <p>Username: <s:property value="user.username"/></p>
    <p>Email: <s:property value="user.email"/></p>
</s:elseif>

<s:else>
    <p>No profile information available.</p>
</s:else>

</body>
</html>
