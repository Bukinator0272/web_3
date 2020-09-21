<html lang="ru">
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <meta charset="UTF-8">
</head>
<body>
<div class="main">
    <form class="form" action="authorization" method="get">
        <label for="login">Login</label>
        <input type="text" required id="login" name="login" placeholder="Login">

        <label for="password">Password</label>
        <input type="password" required id="password" name="password" placeholder="Password">

        <input class="submit_btn" type="submit" value="Sign in">

        <p class="error">${error}</p>
    </form>
</div>
</body>
</html>
