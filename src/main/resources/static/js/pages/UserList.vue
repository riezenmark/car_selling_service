<template>
    <div>
        <h1>User list</h1>
        <user-row v-for="user in users"
                  :key="user.id"
                  :user="user"
                  :deleteUser="deleteUser"
                  :users="users"/>
    </div>
</template>

<script>
    import UserRow from 'components/users/UserRow.vue'

    export default {
        components: {
            UserRow
        },
        data() {
            return {
                user: null,
                users: []
            }
        },
        methods: {
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