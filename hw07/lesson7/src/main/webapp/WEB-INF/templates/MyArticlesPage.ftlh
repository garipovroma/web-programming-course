<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles datatable">
        <div class="caption">My Articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hide/Show</th>
            </tr>
            </thead>
            <tbody>
                <#list articles as article>
                    <#if article.userId == user.id>
                        <tr>
                            <td class="articleId">${article.id}</td>
                            <td>${article.title}</td>
                            <td>
                                <form>
                                    <input type="submit" name="hiddenValue" value="${article.hidden?then('Show', 'Hide')}-${article.id}">
                                </form>
                            </td>
                        </tr>
                    </#if>
                </#list>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            $(".articles form").submit(function() {
                const hideOrShowValue = $(this).find("input[name='hiddenValue']").val();
                const ind = hideOrShowValue.lastIndexOf("-");
                const newText = (hideOrShowValue.substring(0, ind) === "Hide") ? "Show" : "Hide";
                const articleId = hideOrShowValue.substring(ind + 1, hideOrShowValue.length);
                const context = $(this);
                return ajax({
                    action: "setArticleHiddenField",
                    articleId: articleId,
                    value: newText
                }, this, function (response) {
                    if (response["error"]) {
                        context.find(".error").text(response["error"]);
                    } else {
                        context.find("input").val(newText + "-" + articleId);
                        if (response["redirect"]) {
                            location.href = response["redirect"];
                        }
                    }
                });
            });
        });
    </script>
</@c.page>
