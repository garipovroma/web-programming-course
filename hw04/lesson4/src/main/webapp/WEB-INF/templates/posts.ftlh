<#-- @ftlvariable name="user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="post_id" type="java.lang.Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if user_id?? && user_id?is_number>
        <#assign u = c.findBy(users, "id", user_id)!/>
    </#if>
    <#if u?has_content>
        <#assign postsList = posts?filter(x -> x.userId == u.id)!/>
        <#if 1 < postsList?size>
            <ul>
                <#list postsList?reverse as p>
                    <li>
                        <@c.post p false/>
                    </li>
                </#list>
            </ul>
        <#elseif postsList?size == 1>
            <#list postsList?reverse as p>
                <@c.post p false/>
            </#list>
        <#else>
            This user has no posts
        </#if>

    <#else>
        No such user
    </#if>
</@c.page>
