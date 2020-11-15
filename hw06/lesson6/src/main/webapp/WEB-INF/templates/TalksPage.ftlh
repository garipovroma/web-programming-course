<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="messages" type="ru.itmo.wp.model.domain.Talk[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <p>
        <div class="form-box">
            <div class="header">Message</div>
            <div class="body">
                <form method="post" action="" id="message">
                    <input type="hidden" name="action" value="sendMessage"/>
                    <div class="field">
                        <div class="name">
                            <label for="recipient">Recipient:</label>
                        </div>
                        <div class="value">
                            <select form="message" name="recipientUserId" id="recipientUserId">
                                <#list users! as listuser>
                                    <#if listuser.id != user.id>
                                        <option id="recipientUserId" name="recipientUserId" value="${listuser.id}">${listuser.login}</option>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="field">
                        <textarea rows="5" cols="51" id="message" name="message"></textarea>
                    </div>
                    <form class="button-field">
                        <input type="submit" value="Send">
                    </form>
                    <#if error??>
                        <div class="error">
                            ${error!}
                        </div>
                    </#if>
                </form>
            </div>
        </div>
    </p>
    <p>
        <div class="datatable">
            <div class="caption">Messages</div>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Message</th>
                </tr>
                </thead>
                <tbody>
                <#list messages!?reverse as message>
                    <tr>
                        <td>${message.id}</td>
                        <#if message.sourceUserId == user.id>
                            <td style="font-weight: bold">${message.sourceUserLogin}</td>
                        <#else>
                            <td>${message.sourceUserLogin}</td>
                        </#if>
                        <#if message.targetUserId == user.id>
                            <td style="font-weight: bold">${message.targetUserLogin}</td>
                        <#else>
                            <td>${message.targetUserLogin}</td>
                        </#if>
                        <td>${message.text}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </p>
</@c.page>
