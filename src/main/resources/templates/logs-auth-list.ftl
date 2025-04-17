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
    <h1>Logins</h1>
    <table>
        <thead>
            <tr>
                <th class="wp8">E.</th>
                <th class="wp7">R.</th>
                <th class="wp25">Usuario</th>
                <th class="wp60">Instante</th>
            </tr>
        </thead>
        <tbody>
            <#list logins as log>

    <#if log.legal == false >
        <#if log.rol == "ADMIN">
            <tr class="bg-red">
        <#else>
            <tr class="bg-yellow">
        </#if>
    <#else>
        <#if log.rol == "ADMIN">
            <tr class="bg-orange">
        <#else>
            <tr>
        </#if>
    </#if>
                <td>
                    <#if log.legal == false>
                        <img src="/imgs/icons/cancel.png" class="icon-sm" alt="error"/>
                    <#else>
                        <img src="/imgs/icons/accept.png" class="icon-sm" alt="ok"/>
                    </#if>
                </td>
                <td>
                    <#if log.rol == "ADMIN">
                        A
                    <#elseif log.rol == "USER">
                        U
                    <#else>
                        -
                    </#if>
                </td>
                <td>${log.username}</td>
                <td>${log.timestamp}</td>
            </tr>
            </#list>
        </tbody>
    </table>
    <p>${instanteActual}</p>
    <div class="layout no-margin-top">
        <a class="w5 btn btn-md" href="/admin/logs"><img class="icon-sm" src="/imgs/icons/return.png" alt="volver"/>Volver</a>
        <span class="w2"></span>
        <a class="w5 btn btn-md" href="/admin/logs/logins"><img class="icon-sm" src="/imgs/icons/refresh.png" alt="actualizar"/>Actualizar</a>
    </div>
    <#include "common-menu-return.ftl">
</div>
</body>
</html>