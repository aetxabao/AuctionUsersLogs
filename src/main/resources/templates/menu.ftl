<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú</title>
    <link rel="icon" type="image/x-icon" href="/imgs/icons/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <div class="main-container">
        <#if isAdministrator>
            <h1>Menú</h1>
            <form action="/admin/validate-items" method="post">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/accept.png"/>Validar artículos</button>
            </form>
            <form action="/admin/bidding-list-global" method="post">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/list.png"/>Listar artículos y pujas</button>
            </form>
            <form action="/admin/reset" method="post">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/reset.png"/>Resetear sistema</button>
            </form>
            <form action="/admin/winners-list" method="post">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/winners.png"/>Histórico ganadores</button>
            </form>
            <form action="/admin/user-management" method="post">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/users.png"/>Gestionar usuarios</button>
            </form>
            <form action="/admin/logs" method="post">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/logs.png"/>Logs usuarios</button>
            </form>
            <form action="/exit" method="get">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/exit.png"/>Salir</button>
            </form>
        <#else>
            <h1>Menú Usuario</h1>
            <form action="/user/offer" method="post">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/offer.png"/>Subir ofertas</button>
            </form>
            <form action="/user/offer-list" method="get">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/buy.png"/>Ver ofertas y pujar</button>
            </form>
            <form action="/exit" method="get">
                <button type="submit" class="btn btn-lg"><img class="icon-sm" src="/imgs/icons/exit.png"/>Salir</button>
            </form>
        </#if>
    </div>
</body>
</html>