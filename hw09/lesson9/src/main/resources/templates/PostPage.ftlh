<#import "commons.ftlh" as c>

<@c.page>
    <@c.post post=post/>
    <div class="comment">
        <div class="comments-header" style="color: var(--caption-color); font-weight: bold;font-size: 1.25rem;">
            Comments:
        </div>
        <#if user??>
            <div class="create-comment">
                <form method="post" action="">
                    <input type="hidden" name="action" value="comment"/>
                    <div class="field">
                        <div class="name">
                            <label for="password" style="color: var(--caption-color); font-weight: bold;font-size: 1.25rem;">Write Comment:</label>
                        </div>
                        <div class="value">
                            <textarea id="text" name="text" cols="40"></textarea>
                        </div>
                        <div class="error" style="color:red; font-size:0.75rem;">
                            <@c.error "commentForm.text"/>
                        </div>
                    </div>
                    <div class="button-field">
                        <input type="submit" value="Write Comment">
                    </div>
                </form>
            </div>
        </#if>
        <#list post.comments as comment>
            <article>
                <div class="footer" style="margin-right: 30rem; padding: 0.5rem">
                    [${comment.user.login}]: ${comment.text}
                </div>
            </article>
        </#list>
    </div>
</@c.page>
