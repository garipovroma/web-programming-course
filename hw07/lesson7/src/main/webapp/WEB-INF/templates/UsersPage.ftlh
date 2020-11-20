<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if user?? && user.admin>
                    <th>Admin</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user_id"></td>
                    <td class="user_login"></td>
                    <#if user?? && user.admin>
                        <td class="user_admin">
                            <div class="adminValue"></div>
                            <a class="adminLink"></a>
                        </td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");

            $.ajax({
                dataType: "json",
                data: {
                    action: "findAll"
                },
                success: function(response) {
                    response["users"].forEach(function(user) {
                        $noData.hide();
                        const $tr = $($(".users template").prop("content")).clone();
                        $tr.find(".user_id").text(user["id"]);
                        $tr.find(".user_login").text(user["login"]);
                        const $admin = $tr.find(".user_admin");
                        $admin.find(".adminLink").attr("href", "#");
                        $admin.find(".adminLink").attr("data-userId", user["id"]);
                        $admin.find(".adminLink").attr("data-userAdmin", user["admin"]);
                        $admin.find(".adminLink").text(user["admin"] ? "Remove Admin" : "Set Admin");
                        $admin.find(".adminValue").text(user["admin"]);
                        $tr.find("tr").attr("data-userId", user["id"]);
                        $(".users tbody").append($tr);
                    });
                    $(".users .user_admin .adminLink").click(function() {
                        const userId = $(this).attr("data-userId");
                        const userAdminValue = $(this).attr("data-userAdmin");
                        const context = $(this).parent();
                        const newLinkText = (userAdminValue === "true" ? "Set Admin" : "Remove Admin");
                        const newValueText = (userAdminValue === "true" ? "false" : "true");
                        return ajax({
                            action: "setAdminField",
                            userId: userId,
                            userAdminValue: userAdminValue
                        }, this, function (response) {
                            if (response["error"]) {
                                context.find(".error").text(response["error"]);
                            } else {
                                context.find(".adminLink").text(newLinkText);
                                context.find(".adminLink").attr("data-userAdmin", newValueText);
                                context.find(".adminValue").text(newValueText);
                                if (response["redirect"]) {
                                    location.href = response["redirect"];
                                }
                            }
                        });
                    });
                    $(".users tr").click(function() {
                        $.ajax({
                            dataType: "json",
                            data: {
                                action: "findUser",
                                userId: $(this).attr("data-userId")
                            },
                            success: function (response) {
                                notify(response["user"].creationTime);
                            },
                            type: "POST"
                        })
                    });
                },
                type: "POST"
            });
        })
    </script>
</@c.page>
