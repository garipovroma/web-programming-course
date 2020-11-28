<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class = "roundbox">
        <#if user??>
            <div class = "userpic">
                <img alt="Codeforces" title="Codeforces"
                     src="/img/userpic.jpg">
            </div>
            <div class = "userinfo">
                <ul>
                    <li>Id: ${user.id}</li>
                    <li>Login: ${user.login}</li>
                    <li>Creation Time:${user.creationTime}</li>
                </ul>
            </div>
        <#else>
            No such user.
        </#if>
    </div>
</@c.page>
