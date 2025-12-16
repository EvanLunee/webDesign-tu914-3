<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>
<html>
<head><title>Make a Bid</title></head>
<body>
<h2>Place a Bid</h2>
<s:form action="makeBid" method="post">
    <s:hidden name="itemId" value="%{#parameters.itemId[0]}"/>
    Bid Amount: <s:textfield name="bidAmount"/><br/>
    <s:submit value="Place Bid"/>
</s:form>
</body>
</html>
