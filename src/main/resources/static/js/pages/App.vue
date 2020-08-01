<template>
    <v-app>
        <v-navigation-drawer app></v-navigation-drawer>
        <v-app-bar app>
            CarSellingApp
            <div v-if="!profile">
                <a href="/login">Login</a>
            </div>
            <div v-else>
                {{ profile.name }}
                <a href="/logout">Logout</a>
                <div v-if="profile.email === 'riezenmark@gmail.com'">
                    <button @click="userList">Users</button>
                </div>
            </div>
        </v-app-bar>
        <v-main>
            <v-container fluid>
                <router-view></router-view>
            </v-container>
        </v-main>
        <v-footer app></v-footer>
    </v-app>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        computed: mapState(['profile']),
        methods: {
            userList() {
                this.$router.push('/users/list')
            }
        }
    }
</script>

<style>

</style>
