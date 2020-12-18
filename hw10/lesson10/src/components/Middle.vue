<template>
    <div class="middle">
        <Sidebar :posts="viewPosts"/>
        <main>
            <Index v-if="page === 'Index'" :posts="post === undefined ? posts : [post]"
                   :postsCommentsCount="getPostCommentsCount()" :userNameByUserId="getUserNamesByUserId()"
                   :commentsBool="post!==undefined"/>
            <Enter v-if="page === 'Enter'"/>
            <WritePost v-if="page === 'WritePost'"/>
            <EditPost v-if="page === 'EditPost'"/>
            <Register v-if="page === 'Register'"/>
            <Users v-if="page === 'Users'" :users="users"/>
        </main>
    </div>
</template>

<script>
import Sidebar from "@/components/sidebar/Sidebar";
import Index from "@/components/middle/Index";
import Enter from "@/components/middle/Enter";
import WritePost from "@/components/middle/WritePost";
import EditPost from "@/components/middle/EditPost";
import Register from "@/components/middle/Register";
import Users from "@/components/middle/Users";

export default {
    name: "Middle",
    data: function () {
        return {
            page: "Index",
            post: undefined
        }
    },
    components: {
        WritePost,
        Enter,
        Index,
        Sidebar,
        EditPost,
        Register,
        Users
    }, methods: {
        getPostCommentsCount : function() {
            let result = {};
            Object.values(this.posts).forEach(currentPost => {
                const value = Object.values(this.$root.$data.comments).filter((x) => x.postId === currentPost.id).length;
                result[currentPost.id] = (value === undefined ? 0 : value);
            });
            return result;
        },
        getUserNamesByUserId: function () {
            let result = {};
            /*function onlyUnique(value, index, self) {
                return self.indexOf(value) === index;
            }*/
            Object.values(this.posts).forEach(currentPost => {
                const value = Object.values(this.users).filter((x) => x.id === currentPost.userId)[0].name;
                result[currentPost.userId] = value;
            });
            return result;
        }
    },
    props: ["posts", "users"],
    computed: {
        viewPosts: function () {
            return Object.values(this.posts).sort((a, b) => b.id - a.id).slice(0, 2);
        }
    }, beforeCreate() {
        this.$root.$on("onChangePage", (page) => {this.page = page; this.post = undefined;});
        this.$root.$on("onShowPost", (page, post) => {this.page = page; this.post = post;});
    }
}
</script>

<style scoped>

</style>