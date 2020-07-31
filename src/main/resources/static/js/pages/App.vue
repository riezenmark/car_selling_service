<template>
    <div>
        <div style="background-color: green">
            <h3>Car Selling App</h3>
            <div v-if="!profile">
                <a href="/login">Login</a>
            </div>
            <div v-else>
                <div>{{ profile.name }}&nbsp;<a href="/logout">Logout</a></div>
                <div v-if="profile.roles.indexOf('ADMIN') !== -1">
                    <button @click="userList">Users</button>
                </div>
            </div>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        //todo add vuex store
        data() {
            return {
                //todo вынести в vuex
                cars: userData.cars,
                profile: userData.profile
            }
        },
        methods: {
            userList() {
                this.$router.push('/users')
            }
        }
    }
</script>

<style>

</style>
