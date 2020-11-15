<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if handle??>
        <#assign u = c.findBy(users, "handle", handle)!/>
    <#elseif user_id?? && user_id?is_number>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>

    <#if u?has_content>
        <div class = "roundbox">
            <div class = "userinfo">
                <@c.userlink u "colors"/>
                <ul>
                    <li>id: ${u.id}</li>
                    <li>handle: ${u.handle}</li>
                    <li>name: ${u.name}</li>
                    <li>posts: <a href="/posts?user_id=${u.id}">${c.count(posts, "userId", u.id)}</a></li>
                </ul>
            </div>
            <div class = "userpic">
                <img alt="Codeforces" title="Codeforces"
                     src="/img/userpic.jpg">
            </div>
        </div>
        <div class = "arrows">
            <#list users as cur_user>
                <#if cur_user.id == u.id>
                    <#if cur_user?is_first>
                        ←
                    <#else>
                        <a href="user?handle=${users[cur_user_index - 1].handle}">←</a>
                    </#if>
                    <#if cur_user?is_last>
                        →
                    <#else>
                        <a href="user?handle=${users[cur_user_index + 1].handle}">→</a>
                    </#if>
                </#if>
            </#list>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
