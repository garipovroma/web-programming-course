<template>
  <section>
    <div class="header">
      {{ post.title }}
    </div>
    <div class="body">
      {{ post.text }}
    </div>
    <div class="footer">
      <a href="#" @click.prevent="showPost()">View all</a>
    </div>
  </section>
</template>

<script>
export default {
    name: "SidebarPost",
    props: ["post"],
    methods: {
        showPost: function() {
            this.$root.$emit("onShowPost", "Index", this.post);
        }
    }
}
</script>

<style scoped>
section {
  margin-bottom: 1rem;
}
</style>