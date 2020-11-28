<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <div class="notice form-box">
        <div class="header">Create Notice</div>
        <div class="body">
            <form method="post" action="" id="noticeText">
                <input type="hidden" name="action" value="newNotice"/>
                <div class="field">
                    <textarea rows="10" cols="51" id="noticeText" style="resize: none" name="text"></textarea>
                    <@c.error "noticeForm.text"/>
                </div>
                <div class="button-field">
                    <input type="submit" value="Create Notice">
                </div>
            </form>
        </div>
    </div>
</@c.page>
