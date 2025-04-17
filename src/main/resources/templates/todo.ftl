<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menú</title>
    <link rel="icon" type="image/x-icon" href="/imgs/icons/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/layout.css">
</head>
<body>
    <div class="main-container">

        <h1>TODO</h1>

        <form action="" method="post" class="layout">

            <div class="w12 input-element-vertical">
                <h2>Para hacer ${username}</h2>
            </div>

        </form>

        <hr/>

        <div class="input-element-horizontal bigger-margin-bottom">
            <form action="/exit" method="get" class="no-min-display">
                <button type="submit" class="btn btn-md no-margin-bottom no-margin-top"><img class="icon-sm" src="/imgs/icons/exit.png"/>Salir</button>
            </form>
            <form action="/menu" method="get">
                <button type="submit" class="btn btn-md no-margin-bottom no-margin-top"><img class="icon-sm" src="/imgs/icons/menu.png"/>Menú</button>
            </form>
        </div>

        <div id="menu">
            <a href="/menu">
                <img class="icon-lg" src="/imgs/icons/menu.png" alt="menu" title="menu"/>
            </a>
        </div>
        <div id="return">
            <a href="/menu">
                <img class="icon-lg" src="/imgs/icons/return.png" alt="regresar" title="regresar"/>
            </a>
        </div>
    </div>
</body>
</html>