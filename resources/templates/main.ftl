<#macro page_body>
    <text>Placeholder</text>
</#macro>

<#macro main userIdPrincipalName>
<#-- @ftlvariable name="userIdPrincipalName" type="String" -->
    <!doctype html>
    <html lang="en">
    <head>
        <title>Nano Blogging Service</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta charset="utf-8">
    </head>
    <body class="d-flex flex-column h-100">
    <header>
        <div class="navbar navbar-dark bg-dark shadow-sm">
            <div class="container">
                <a href="/" class="font-weight-bold navbar-brand">
                    📝 𝓝𝓐𝓝𝓞 𝓑𝓛𝓞𝓖𝓖𝓘𝓝𝓖 𝓢𝓔𝓡𝓥𝓘𝓒𝓔
                </a>
                <div class="navbar-nav flex-row flex-grow-1 gap-3 justify-content-end">
                    <#if userIdPrincipalName?has_content>
                        <a href="/feed/${userIdPrincipalName}" class="nav-link">
                            Hello, ${userIdPrincipalName}
                        </a>
                        <form action="/logout" method="post" class="mt-0 mb-0">
                            <input class="btn btn-outline-light" type="submit" value="Logout">
                        </form>
                    <#else>
                        <div class="navbar-text">
                            Hello, Guest
                        </div>
                        <a href="/login" class="btn btn-outline-light">
                            Login
                        </a>
                    </#if>
                </div>
            </div>
        </div>
    </header>
    <main class="flex-shrink-0 mt-3">
        <div class="container col-xs-12 col-lg-8">
            <@page_body/>
        </div>
    </main>
    </body>
    </html>
</#macro>