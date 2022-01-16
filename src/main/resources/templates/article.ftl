<#-- @ftlvariable name="article" type="me.freedom4live.ktor.db.entity.ArticleBody" -->
<#-- @ftlvariable name="userIdPrincipal" type="io.ktor.auth.UserIdPrincipal" -->

<#include "main.ftl">


<#macro page_body>
    <h2 class="text-center">${article.title}</h2>
    <div class="entity card m-4">
        <div class="w-100 card-header">
            <h4 class="user font-weight-bold mb-0 pb-0 d-inline-block">
                <a href="feed/${article.author}">${article.author}</a>
            </h4>
            <span class="float-end text-secondary">
                    ${article.createdTimestamp.toString("dd-MM-yy, HH:mm:ss")}
            </span>
        </div>
        <div class="card-body">
            <h5>${article.text!""}</h5>
        </div>
    </div>
</#macro>

<@main userIdPrincipalName=(userIdPrincipal.name)!""/>