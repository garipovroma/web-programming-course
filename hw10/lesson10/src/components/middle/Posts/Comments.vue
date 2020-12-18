<template>
    <div class="comments">
        <div v-for="comment in comments" :key="comment.id">
            <article>
                <div class="footer">
                    [{{ userLoginById[comment.userId] }}]: {{ comment.text }}
                </div>
            </article>
        </div>
    </div>
</template>

<script>
export default {
    name: "Comments",
    props: ["comments", "userLoginById"]
}
</script>

<style scoped>
    .comments article .footer {
        margin-right: 30rem;
        padding: 0.5rem;
    }
</style>