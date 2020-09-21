<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <meta charset="UTF-8">
</head>
<body>
<div class="main">
    <table class="table">
        <caption><h1>Hello, ${customerData.name}!</h1></caption>
        <tr>
            <th>Account number</th>
            <th>chequeBooks</th>
        </tr>
        <c:forEach var="chequeBook" items="${customerData.chequeBooks}">
            <tr>
                <td>${chequeBook.accountNumber}</td>
                <td>
                    <table>
                        <tr>
                            <th>Service type</th>
                            <th>Price</th>
                            <th>Count</th>
                            <th>Cost</th>
                        </tr>
                        <c:forEach var="cheque" items="${chequeBook.cheques}">
                            <tr>
                                <td>${cheque.serviceType}</td>
                                <td>${cheque.price}</td>
                                <td>${cheque.count}</td>
                                <td>${cheque.cost}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h1><a href="result.xml">Click me</a></h1>
</div>
</body>
</html>
