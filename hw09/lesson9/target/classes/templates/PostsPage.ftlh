<#import "commons.ftlh" as c>

<@c.page>
<#list user.posts as post>
    <@c.post post=post/>
</#list>
    <#if user.posts?size == 0>
        You haven't your own posts on Codeforces.
    </#if>
</@c.page>
