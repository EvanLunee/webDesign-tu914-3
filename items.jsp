<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>
<html>
<head><title>Items</title></head>
<body>
<h2>Available Items</h2>
<s:iterator value="itemsList">
    <p>
        <b><s:property value="title"/></b> - $<s:property value="price"/><br/>
        Owner: <s:property value="owner"/><br/>
        Description: <s:property value="description"/><br/>
        <a href="makeBid.action?itemId=<s:property value='id'/>">Make a Bid</a>
    </p>
    <hr/>
</s:iterator>
<a href="addItem.jsp">Add Item</a>
</body>
</html>
