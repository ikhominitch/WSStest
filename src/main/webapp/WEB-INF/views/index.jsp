<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quadratic equation</title>
</head>
<body>
<h2>Please fill the form below</h2>

<form method="post" action="${pageContext.request.contextPath}" commandName="dto">
    <input type="number" step="any" width="50" required name="a"> x² +
    <input type="number" step="any" size="5" required name="b"> x +
    <input type="number" step="any" size="5" required name="c"> = 0
    <button type="submit">Calculate</button>
</form>

</body>
</html>
