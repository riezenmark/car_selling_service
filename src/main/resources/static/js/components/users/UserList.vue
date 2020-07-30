<template>
    <div>
        <user-form :users="users" :user="user"/>
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
    import UserForm from 'components/users/UserForm.vue'

    export default {
        props: ['users'],
        components: {
            UserRow,
            UserForm
        },
        data() {
            return {
                user: null
            }
        },
        methods: {
            editUser(user) {
                this.user = user
            },
            deleteUser(user) {
                this.$resource('/message{/id}').remove({id: user.id}).then(result => {
                    if (result.ok) {
                        this.users.splice(this.users.indexOf(user), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>