<#-- @ftlvariable name="uri" type="java.lang.String" -->
<#-- @ftlvariable name="post.user_id" type="java.lang.Long" -->
<#-- @ftlvariable name="post.text" type="java.lang.String" -->

<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-vs-coronavirus-65.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userlink user "nameOnly"/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
            {"hrefs": ["/index", "/"], "name": "Home"},
            {"hrefs": ["/users", "/user"], "name": "Users"},
            {"hrefs": ["/misc/help"], "name": "Help"}
            ]/>
            <ul>
                <#list items as item>
                    <li>
                        <#if item["hrefs"]?seq_contains(uri)>
                            <a href="${item.hrefs[0]}"
                               class="chosen-item">${item.name}</a>
                        <#else>
                            <a href="${item.hrefs[0]}">${item.name}</a>
                        </#if>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts?reverse as p>
            <section>
                <div class="header">Post # ${p.id}</div>
                <div class="body">
                    <@getPostText p true/>
                </div>
                <div class="footer">
                    <a href="/post?post_id=${p.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2020 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#macro userlink user mode>
    <#if mode == "nameOnly">
        <a href="/user?handle=${user.handle}">${user.name}</a>
    <#elseif mode == "colors">
        <a href="/user?handle=${user.handle}" style="color:${user.color}; text-decoration: none; font-weight: bold">${user.name}</a>
    </#if>
</#macro>

<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>

<#function count items key value>
    <#assign x = 0!/>
    <#list items as item>
        <#if item[key] == value>
            <#assign x = x + 1!/>
        </#if>
    </#list>
    <#return x/>
</#function>

<#macro getPostText p shorten>
    <#assign text = p.text !/>
    <#if text?length <= 250 || !shorten>
        ${text}
    <#else>
        ${text?substring(0, 250)} …
    </#if>
</#macro>

<#macro post p shorten>
    <link rel="stylesheet" href="/css/postsList.css">
    <main>
        <article>
            <div class="title">${p["title"]}</div>
            <#assign author = c.findBy(users, "id", p.userId)!/>
            <#assign text = p.text>
            <div class="information">By <@userlink author "colors"/>, 2 days ago, translation</div>
            <div class="body">
                <@getPostText p shorten/>
            </div>
            <ul class="attachment">
                <li>Announcement of <a href="#">Codeforces Round #510 (Div. 1)</a></li>
                <li>Announcement of <a href="#">Codeforces Round #510 (Div. 2)</a></li>
            </ul>
            <div class="footer">
                <div class="left">
                    <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    2 days ago
                    <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a href="#">68</a>
                </div>
            </div>
        </article>
    </main>
</#macro>