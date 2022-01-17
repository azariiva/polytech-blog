<#-- @ftlvariable name="title" type="String"-->
<#-- @ftlvariable name="articles" type="kotlin.collections.List<me.freedom4live.ktor.db.entity.ArticleBody>" -->
<#-- @ftlvariable name="userIdPrincipal" type="io.ktor.auth.UserIdPrincipal" -->
<#include "main.ftl">
<#include "send_message_form.ftl">

<#macro page_body>
    <#if userIdPrincipal??>
        <@send_message_form/>
        <hr>
    </#if>
    <h2 class="text-center">${title}</h2>
    <#list articles as article>
    <#-- @ftlvariable name="article" type="me.freedom4live.ktor.db.entity.ArticleBody" -->
        <div class="entity card m-4">
            <div class="w-100 card-header">
                <h6 class="user mb-0 pb-0 d-inline-block">
                    <a href="feed/${article.author}">${article.author}</a>
                </h6>
                <span class="float-end text-secondary">
                    <h6>${article.createdTimestamp.toString("dd-MM-yy, HH:mm:ss")}</h6>
                </span>
            </div>
            <div class="card-body">
                <h3 class="title"><a href="article/${article.id}" class="text-decoration-none text-black">
                        ${article.title}</a></h3>
                <#noautoesc>
                    <#assign text = (article.text!"")?replace("(\r\n)+", "</p><p>",'r')>
                    ${"<p>${text}</p>"}
                </#noautoesc>
            </div>
        </div>
    </#list>
</#macro>

<@main userIdPrincipalName=(userIdPrincipal.name)!""/>

