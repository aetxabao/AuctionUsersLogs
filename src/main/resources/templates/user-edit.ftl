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
    <div class="main-container layout">
        <h1>Editar</h1>

        <div class="w12 input-element-vertical">
            <h2>Usuario: ${user.nombre}</h2>
        </div>

        <div class="w6 input-element-vertical">
            <form action="/admin/user/change/rol/${user.nombre}" method="post">
                <select class="input-md" id="rol" name="rol" required="required">
                    <option value="ADMIN"
                    <#if user.rol == "ADMIN">selected</#if>
                    >ADMIN</option>
                    <option value="USER"
                    <#if user.rol == "USER">selected</#if>
                    >USER</option>
                </select>
                <button type="submit" class="btn btn-md"><img class="icon-sm" src="/imgs/icons/rol-user.png"/>Cambiar Rol</button>
            </form>
        </div>
        <div class="w6 input-element-vertical">
            <form action="/admin/user/change/password/${user.nombre}" method="post">
                <input type="text" class="input-md" id="clave" placeholder="clave" name="password" title="clave del usuario" required="required"/>
                <button type="submit" class="btn btn-md"><img class="icon-sm" src="/imgs/icons/pw-user.png"/>Cambiar Clave</button>
            </form>
        </div>

        <div id="menu">
            <a href="menu">
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