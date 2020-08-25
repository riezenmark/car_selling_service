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
        <th class="text-left">Username</th>
        <th class="text-left">Active</th>
        <th class="text-left">AccountNonExpired</th>
        <th class="text-left">AccountNonLocked</th>
        <th class="text-left">CredentialsNonExpired</th>
        <th class="text-left">Authorities</th>
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
import UserRow from 'components/users/UserRow.vue'

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
      this.$resource('/api/users{/id}').remove({id: user.id}).then(result => {
        if (result.ok) {
          this.users.splice(this.users.indexOf(user), 1)
        }
      })
    },
    searchForUsers() {
      this.users = []
      this.$resource('/api/users').get({q: this.search}).then(result =>
          result.json().then(data =>
              data.forEach(user => this.users.push(user))
          )
      )
    }
  },
  created() {
    this.$resource('/api/users').get().then(result =>
        result.json().then(data =>
            data.forEach(user => this.users.push(user))
        )
    )
  }
}
</script>

<style>

</style>