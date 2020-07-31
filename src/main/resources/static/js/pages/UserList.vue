<template>
    <div>
        <h1>User list</h1>
        <user-row v-for="user in users"
                  :key="user.id"
                  :user="user"
                  :editUser="editUser"
                  :deleteUser="deleteUser"
                  :users="users"/>
    </div>
</template>

<script>
    import UserRow from 'components/users/UserRow.vue'

    export default {
        props: ['users'],
        components: {
            UserRow
        },
        data() {
            return {
                user: null,
                users: this.users
            }
        },
        methods: {
            editUser(user) {
                this.user = user
            },
            deleteUser(user) {
                this.$resource('/users{/id}').remove({id: user.id}).then(result => {
                    if (result.ok) {
                        this.users.splice(this.users.indexOf(user), 1)
                    }
                })
            }
        },
        created() {
            this.$resource('/users{/id}').get().then(result =>
                result.json().then(data =>
                    data.forEach(user => this.users.push(user))
                )
            )
        }
    }
</script>

<style>

</style>