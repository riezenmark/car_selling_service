<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться:
            <a href="/login">Login</a>
        </div>
        <div v-else>
        <!--todo profile name-->
            <div>{{profile.name}}&nbsp;<a href="/logout">Logout</a></div>
            <user-list :users="users"/>
        </div>
    </div>
</template>

<script>
    import UserList from 'components/users/UserList.vue'

    export default {
        components: {
            UserList
        },
        data() {
            return {
                users: userData.cars,
                profile: userData.profile
            }
        }
    }
</script>

<style>

</style>
