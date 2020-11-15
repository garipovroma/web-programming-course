<#-- @ftlvariable name="title" type="java.lang.String" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <p>
    <div class="form-box">
        <div class="header">Article</div>
        <div class="body">
            <form method="post" action="" id="articleText">
                <input type="hidden" name="action" value="createArticle"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title" value="${title!}"/>
                    </div>
                </div>
                <div class="field">
                    <textarea rows="10" cols="51" id="articleText" name="articleText"></textarea>
                </div>
                <form class="button-field">
                    <input type="submit" value="Create">
                </form>
            </form>
        </div>
    </div>
    </p>
    <script>
        $(function() {
            $(".enter form").submit(function() {
                const title = $(this).find("input[name='title']").val();
                const articleText = $(this).find("input[name='articleText']").val();
                return ajax({
                    action: "createArticle",
                    title: title,
                    articleText: articleText
                }, this);
            })
        })
    </script>
</@c.page>
