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
    <h1>Alertas</h1>
    <#if mapa?keys?size == 0>
        <hr/>
        <p class="w12 alert bigger-margin-bottom">No hay alertas</p>
        <hr/>
    <#else>
        <#list mapa as username, auditRecords>
            <h2>${username}</h2>
            <table>
                <thead>
                <tr>
                    <th class="wp50">Instante</th>
                    <th class="wp50">Acceso ilegal</th>
                </tr>
                </thead>
                <tbody>
                <#list auditRecords as ar>
                    <tr>
                        <td>${ar.timestamp}</td>
                        <td class="txt-left">${ar.path}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </#list>
    </#if>
    <p>${instanteActual}</p>
    <div class="layout no-margin-top">
        <a class="w5 btn btn-md" href="/admin/logs"><img class="icon-sm" src="/imgs/icons/return.png" alt="volver"/>Volver</a>
        <span class="w2"></span>
        <a class="w5 btn btn-md" href="/admin/logs/alerts"><img class="icon-sm" src="/imgs/icons/refresh.png" alt="actualizar"/>Actualizar</a>
    </div>
    <#include "common-menu-return.ftl">
</div>
</body>
</html>