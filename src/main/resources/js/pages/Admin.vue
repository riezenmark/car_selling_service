<template>
    <div>
    <v-row justify="center" class="mt-5">
        <v-btn outlined class="indigo--text" @click="userList">Пользователи</v-btn>
    </v-row>
    <v-row justify="center" class="mt-5">
        <v-btn outlined class="indigo--text" @click="modelsList">Модели машин</v-btn>
    </v-row>
    </div>
</template>

<script>
    export default {
        methods: {
            userList() {
                this.$router.push('/admin/users')
            },
            modelsList() {
                this.$router.push('/admin/models')
            }
        }
    }
</script>

<style>

</style>