<template>
    <div>
        <Post v-for="post in Object.values(this.posts).sort((x, y) => x.id - y.id)"
              :post="post" :userName="getUserNameByUserId(post.user.id)" :key="post.id"/>
    </div>
</template>

<script>
import Post from "@/components/middle/Posts/Post";

export default {
    name: "Index",
    props: ["posts", "userNameByUserId"],
    components : {
        Post
    },
    methods : {
        getUserNameByUserId : function(id) {
            return this.userNameByUserId[id];
        }
    }
}
</script>

<style scoped>

</style>