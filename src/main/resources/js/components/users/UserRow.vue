<template>
  <tr>
    <td>{{ user.id }}</td>
    <td>{{ user.username }}</td>
    <td>{{ user.active }}</td>
    <td>{{ user.accountNonExpired }}</td>
    <td>{{ user.accountNonLocked }}</td>
    <td>{{ user.credentialsNonExpired }}</td>
    <td>{{ user.authorities }}</td>
    <td>
      <v-btn outlined class="indigo--text" @click="del">Delete</v-btn>
      <v-btn outlined class="indigo--text" @click="update">Update</v-btn>
    </td>
  </tr>
</template>

<script>
export default {
  props: ['user', 'deleteUser'],
  methods: {
    del() {
      this.deleteUser(this.user)
    },
    update() {
      this.$router.push('users/' + this.user.id)
    }
  }
}
</script>

<style>

</style>
