<template>
    <footer>
        <a href="/">Codeforces</a> &copy; 2020 by Mike Mirzayanov
        <div class="app-info">
            Total Users : {{ usersCount }}
        </div>
        <div class="app-info">
            Total Posts : {{ postsCount }}
        </div>
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["postsCount", "usersCount"]
}
</script>

<style scoped>

</style>