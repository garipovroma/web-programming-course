<template>
    <div class="post">
        <article>
            <div class="title">
                <a style="text-decoration: none; color:var(--caption-color)" @click.prevent="showPost()" href="#">
                    {{ post.title }}
                </a>
            </div>
            <div class="information">
                By {{ userName }}
            </div>
            <div class="body">
                {{ post.text }}
            </div>
            <div class="footer">
                <div class="left">
                    <img src="../../../assets/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                    <span class="positive-score">+173</span>
                    <img src="../../../assets/img/votedown.png" title="Vote Down" alt="Vote Down"/>
                </div>
                <div class="right">
                    <img src="../../../assets/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                    <img src="../../../assets/img/comments_16x16.png" title="Comments" alt="Comments"/>
                    <a href="#"> {{ commentsCount }} </a>
                </div>
            </div>
        </article>
        <Comments v-if="commentsBool" :comments="getComments()" :userLoginById="getUsersForComments()"/>
    </div>
</template>

<script>

import Comments from "@/components/middle/Posts/Comments";

export default {
    name: "Post",
    props: ["post", "commentsCount", "userName", "commentsBool"],
    components: {
        Comments
    },
    methods : {
        getComments: function() {
            const result = Object.values(this.$root.$data.comments).filter((x) => x.postId === this.post.id);
            return result;
        },
        getUsersForComments: function() {
            let result = {};
            let comments = Object.values(this.$root.$data.comments).filter((x) => x.postId === this.post.id);
            Object.values(this.$root.$data.users).forEach((user) => {
                let filtred = comments.filter((comment) => comment.userId === user.id)[0];
                if (filtred !== undefined)
                    result[filtred.userId] = user.login;
            });
            return result;
        }, showPost: function() {
            this.$root.$emit("onShowPost", "Index", this.post);
        }
    }
}
</script>

<style scoped>
</style>