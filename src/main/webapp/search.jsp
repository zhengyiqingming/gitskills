<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>


        <form action="${pageContext.request.contextPath}/book/find" method="post">
            <input type="text" name="keywords" value="${requestScope.keywords}"> <input type="submit" value="搜索"><br>
        </form>

        <hr>

        <c:forEach items="${requestScope.books}" var="book">
            <ul>
                <li><a href="${pageContext.request.contextPath}/book/findOne?id=${book.id}">${book.id}</a></li>
                <li>${book.name}</li>
                <li>${book.price}</li>
                <li>${book.author}</li>
                <li>${book.content}</li>
            </ul>
        </c:forEach>




</body>
</html>