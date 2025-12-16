<%@ page session="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>

<html>
<head><title>All Registered Users</title></head>
<body>
<h2>All Registered Users</h2>

<s:if test="usersList != null && !usersList.isEmpty()">
    <s:iterator value="usersList">
        <p>
            ID: <s:property value="id"/> <br/>
            Username: 
            <a href="viewOtherProfile.action?viewUserId=<s:property value='id'/>">
                <s:property value="username"/>
            </a><br/>
            Email: <s:property value="email"/> <br/>
        </p>
        <hr/>
    </s:iterator>
</s:if>

<s:else>
    <p>No users found.</p>
</s:else>

</body>
</html>
