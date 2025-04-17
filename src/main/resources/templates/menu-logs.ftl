<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logs</title>
    <link rel="icon" type="image/x-icon" href="/imgs/favicon.ico">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/layout.css">
</head>
<body>
<div class="main-container">
    <h1>Menú Logs</h1>

    <div class="input-element-vertical center">
        <a class="btn btn-md no-margin-bottom" href="/admin/logs/logins"><img class="icon-sm" src="/imgs/icons/login.png" alt="login"/>Logins</a>
        <br/>
        <a class="btn btn-md no-margin-bottom" href="/admin/logs/alerts"><img class="icon-sm" src="/imgs/icons/alert.png" alt="alert"/>Alerts</a>
        <br/>
        <a class="btn btn-md no-margin-bottom" href="/admin/logs/user-logs"><img class="icon-sm" src="/imgs/icons/user-logs.png" alt="logs"/>User logs</a>
        <br/>
        <a class="btn btn-md no-margin-bottom" href="/exit"><img class="icon-sm" src="/imgs/icons/exit.png" alt="exit"/>Exit</a>
        <br/>
    </div>

    <#include "common-menu-return.ftl">
</div>
</body>
</html>