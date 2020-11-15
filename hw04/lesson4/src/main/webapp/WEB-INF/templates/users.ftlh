<#import "./common.ftlh" as c>

<@c.page>
    <link rel="stylesheet" href="/css/dataTable.css">
    <div class="datatable">
        <div class="caption">Users</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Handle</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <#list users as u>
                <tr>
                    <td><#if user?? && user.id == u.id>*</#if>${u.id}</td>
                    <td>${u.handle}</td>
                    <td><@c.userlink u "colors"/></td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
<ul>
</ul>
</@c.page>
