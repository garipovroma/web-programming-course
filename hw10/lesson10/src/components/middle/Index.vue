<template>
    <div class="posts">
        <Post v-for="post in Object.values(posts).sort((x, y) => x.id - y.id)"
              :post="post" :commentsCount="getCommentsCount(post)"
              :userName="getUserNameByUserId(post.userId)" :commentsBool="commentsBool" :key="post.id"/>
    </div>
</template>

<script>

import Post from "@/components/middle/Posts/Post";

export default {
    name: "Index",
    props: ["posts", "postsCommentsCount", "userNameByUserId", "commentsBool"],
    components : {
        Post
    },
    methods : {
        getCommentsCount : function(post) {
            return this.postsCommentsCount[post.id];
        },
        getUserNameByUserId : function(id) {
            return this.userNameByUserId[id];
        }
    }
}
</script>

<style scoped>

</style>