<template>
    <div>
        <i>({{ user.id }})</i> {{ user.username }} {{ user.password }} {{ user.email }}
        <span>
            <input type="button" value="Edit" @click="edit">
            <input type="button" value="Delete" @click="del">
        </span>
    </div>
</template>

<script>
    export default {
        props: ['user', 'editUser', 'deleteUser', 'users'],
        methods: {
            edit() {
                this.editUser(this.user)
            },
            del() {
                this.deleteUser(this.user)
            }
        }
    }
</script>

<style>

</style>
