<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pujas</title>
    <link rel="icon" type="image/x-icon" href="/imgs/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/layout.css">
    <link rel="stylesheet" href="/css/table.css">
</head>
<body>
<div class="main-container">
    <h1>Logs</h1>
    <hr/>
    <form class="layout no-margin-top" action="/admin/logs/user-logs" method="post">
        <select class="w5 input-md bigger-margin-top" name="username">
            <#list usernames as username>
            <option value="${username}"
                    <#if username == selectedUsername>
                    selected
                    </#if>
            >${username}</option>
            </#list>
        </select>
        <span class="w2"></span>
        <button type="submit" class="w5 btn"><img class="icon-sm" src="/imgs/icons/refresh.png"/>Actualizar</button>
    </form>
    <hr/>
    <#if uar??>
        <h2>${uar.username}</h2>
        <#if uar.auditRecords?size == 0>
            <p class="w12 alert bigger-margin-bottom">No hay registros</p>
            <hr/>
        <#else>
            <table>
                <thead>
                <tr>
                    <th class="wp8">E.</th>
                    <th class="wp42">Instante</th>
                    <th class="wp50">Acceso</th>
                </tr>
                </thead>
                <tbody>
                    <#list uar.auditRecords as ar>
                    <tr>
                        <td>
                            <#if ar.legal == false>
                                <img src="/imgs/icons/cancel.png" class="icon-sm" alt="error"/>
                            <#else>
                                <img src="/imgs/icons/accept.png" class="icon-sm" alt="ok"/>
                            </#if>
                        </td>
                        <td>${ar.timestamp}</td>
                        <td class="txt-left">${ar.path}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
    </#if>

    <p>${instanteActual}</p>
    <div class="input-element-horizontal no-margin-top center">
        <a class="w5 btn btn-md" href="/admin/logs"><img class="icon-sm" src="/imgs/icons/return.png" alt="volver"/>Volver</a>
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
    </div></div>
</body>
</html>