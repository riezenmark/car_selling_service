<template>
    <div>
        <i>({{ user.id }})</i> {{ user.name }} {{ user.email }}&nbsp;
        {{ user.gender }} {{ user.locale }} {{ user.lastVisit }}&nbsp;
        <img :src="user.userpic" alt="Userpic" style="width: 50px" height="50px">
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
