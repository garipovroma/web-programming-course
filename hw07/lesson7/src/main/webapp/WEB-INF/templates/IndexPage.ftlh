<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <template>
            <article>
                    <div class="title"></div>
                    <div class="information"></div>
                    <div class="body"></div>
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
        </template>
        <div class="noData">
            No data
        </div>
    </div>
<script>
    $(function () {
        $("aside section").click(function () {
            $(this).css("background-color", "purple");
        });
    })
    $(function () {
        const $noData = $(".articles .noData");
        $noData.text("Loading...");

        $.ajax({
            dataType: "json",
            data: {
                action: "action"
            },
            success: function(response) {
                response["articles"].forEach(function(article) {
                    $noData.hide();
                    const $article = $($(".articles template").prop("content")).clone();
                    $article.find(".title").text(article["title"]);
                    $article.find(".information")
                        .text("By " + article["userId"] + ", " + article["creationTime"] + ", translation");
                    $article.find(".body").text(article["text"]);
                    if (!article["hidden"]) {
                        $(".articles").append($article);
                    }
                });
            },
            type: "POST"
        });

    })
</script>
</@c.page>
