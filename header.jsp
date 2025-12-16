<%@ page session="true" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="background-color:#eee; padding:10px;">

    <s:if test="#session.username != null">
        Logged in as <b><s:property value="#session.username"/></b> |
        <a href="index.jsp">Home</a> |
        <a href="viewProfile.action">My Profile</a> |
        <a href="viewItems.action">Items</a> |
        <a href="viewMyBids.action">My Bids</a> |
        <a href="viewAllUsers.action">All Users</a> |
        <a href="logout.action">Logout</a>
    </s:if>
    <s:else>
        <a href="login.jsp">Login</a> |
        <a href="register.jsp">Register</a>
    </s:else>
    
</div>
<hr/>
