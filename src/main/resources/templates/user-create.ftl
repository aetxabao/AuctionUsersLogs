<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EditUsr</title>
    <link rel="icon" type="image/x-icon" href="/imgs/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/layout.css">
</head>
<body>
<div class="main-container">
    <h1>Crear</h1>

    <div class="w12 input-element-vertical">
        <h2>Usuario</h2>
    </div>

    <form action="/admin/user/create" method="post" class="layout">
        <div class="w6 input-element-vertical center">
            <input type="text" class="input-md" id="nombre" placeholder="nombre" name="username" title="nombre del usuario" required="required"/>
            <select class="input-md" id="rol" name="rol" required="required">
                <option value="ADMIN">ADMIN</option>
                <option value="USER" selected>USER</option>
            </select>
        </div>
        <div class="w6 input-element-vertical center">
            <input type="text" class="input-md" id="clave" placeholder="clave" name="password" title="clave del usuario" required="required"/>
            <button type="submit" class="btn btn-md"><img class="icon-sm" src="/imgs/icons/users.png"/>Crear usuario</button>
        </div>
    </form>

    <div id="menu">
        <a href="/menu">
            <img class="icon-lg" src="/imgs/icons/menu.png" alt="menu"/>
        </a>
    </div>
    <div id="return">
        <a href="/exit">
            <img class="icon-md" src="/imgs/icons/exit.png" alt="salir" title="salir"/>
        </a>
    </div>
</div>
</body>
</html>