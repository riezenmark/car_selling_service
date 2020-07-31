<template>
    <div>
        <i>({{ user.id }})</i> {{ user.username }} {{ user.password }} {{ user.email }}
        <span>
            <input type="button" value="Delete" @click="del">
        </span>
    </div>
</template>

<script>
    export default {
        props: ['user', 'deleteUser', 'users'],
        methods: {
            del() {
                this.deleteUser(this.user)
            }
        }
    }
</script>

<style>

</style>
