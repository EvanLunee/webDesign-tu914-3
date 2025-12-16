<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="header.jsp" %>
<html>
<head><title>Add Item</title></head>
<body>
<h2>Add an item to marketpace</h2>
<s:form action="addItem" method="post">
    <s:textfield name="title" label="Title"/><br/>
    <s:textarea name="description" label="Description"/><br/>
    <s:textfield name="startingPrice" label="Starting Price"/><br/>
    <s:submit value="Add Item"/>
</s:form>
</body>
</html>
