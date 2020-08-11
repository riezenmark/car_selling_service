<template>
    <div>
        <v-layout row>
            <v-text-field
                    label="Поиск пользователей"
                    placeholder="Введите имя"
                    v-model="search"
                    @keyup.enter="searchForUsers"
                    class="pl-3"/>
            <v-btn @click="searchForUsers" icon class="mr-3">
                <v-icon>mdi-account-search</v-icon>
            </v-btn>
        </v-layout>
        <v-simple-table>
            <thead>
            <tr>
                <th class="text-left">ID</th>
                <th class="text-left">Name</th>
                <th class="text-left">Email</th>
                <th class="text-left">Gender</th>
                <th class="text-left">Locale</th>
                <th class="text-left">Last Visit</th>
                <th class="text-left">Actions</th>
            </tr>
            </thead>
            <tbody>
            <user-row v-for="user in users"
                      :key="user.id"
                      :user="user"
                      :deleteUser="deleteUser"
            />
            </tbody>
        </v-simple-table>
    </div>
</template>

<script>
    import UserRow from 'static/js/components/users/UserRow.vue'

    export default {
        components: {
            UserRow
        },
        data() {
            return {
                user: null,
                users: [],
                search: ''
            }
        },
        methods: {
            deleteUser(user) {
                this.$resource('/admin/users{/id}').remove({id: user.id}).then(result => {
                    if (result.ok) {
                        this.users.splice(this.users.indexOf(user), 1)
                    }
                })
            },
            searchForUsers() {
                this.users = []
                this.$resource('/admin/users').get({q: this.search}).then(result =>
                    result.json().then(data =>
                        data.forEach(user => this.users.push(user))
                    )
                )
            }
        },
        created() {
            this.$resource('/admin/users').get().then(result =>
                result.json().then(data =>
                    data.forEach(user => this.users.push(user))
                )
            )
        }
    }
</script>

<style>

</style>