<template>
    <tr>
        <td>{{ user.id }}</td>
        <td>{{ user.name }}</td>
        <td>{{ user.email }}</td>
        <td>{{ user.gender }}</td>
        <td>{{ user.locale }}</td>
        <td>{{ user.lastVisit }}</td>
        <td><img :src="user.userpic" alt="" style="width: 50px" height="50px"></td>
        <td><v-btn @click="del" tile>Delete</v-btn></td>
    </tr>
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
