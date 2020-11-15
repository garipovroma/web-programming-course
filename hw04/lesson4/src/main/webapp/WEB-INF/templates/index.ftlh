<#import "./common.ftlh" as c>

<@c.page>
    <ul>
        <#list posts?reverse as p>
            <li>
                <@c.post p true/>
            </li>
        </#list>
    </ul>
</@c.page>
