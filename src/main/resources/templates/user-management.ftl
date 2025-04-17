<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AdmUsr</title>
    <link rel="icon" type="image/x-icon" href="/imgs/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/layout.css">
    <link rel="stylesheet" href="/css/table.css">
</head>
<body>
    <div class="main-container">
        <h1>Usuarios</h1>
        <table>
            <thead>
                <tr>
                    <th>Borrar</th>
                    <th>Nombre</th>
                    <th>Rol</th>
                    <th>Editar</th>
                </tr>
            </thead>
            <tbody>
            <#if users?size == 0>
                <tr>
                    <td colspan="4" class="center alert">Crea al menos un administrador!</td>
                </tr>
            <#else>
                <#list users as user>
                    <tr>
                        <td class="center"><a class="icon-btn" href="/admin/user/del/${user.nombre}"><img class="icon-sm" src="/imgs/icons/del-user.png" alt="eliminar"/></a></td>
                        <td>${user.nombre}</td>
                        <td>${user.rol}</td>
                        <td class="center"><a class="icon-btn" href="/admin/user/edit/${user.nombre}"><img class="icon-sm" src="/imgs/icons/edit-user.png" alt="editar"/></a></td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
        <hr/>

        <div class="input-element-horizontal bigger-margin-bottom">
            <form action="/admin/user/create" method="get">
                <button type="submit" class="btn btn-lg no-margin-bottom no-margin-top"><img class="icon-sm" src="/imgs/icons/users.png"/>Crear usuario</button>
            </form>
        </div>

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