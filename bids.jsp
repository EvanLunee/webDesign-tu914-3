<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>
<html>
<head><title>Bids</title></head>
<body>
<h2>Current Bids</h2>
<s:iterator value="bidsList">
    <p>
        Amount: $<s:property value="amount"/><br/>
        Item/User: <s:property value="item"/><s:property value="user"/><br/>
        Date: <s:property value="created"/>
    </p>
    <hr/>
</s:iterator>
</body>
</html>
