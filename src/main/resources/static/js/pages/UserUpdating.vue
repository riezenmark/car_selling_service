<template>
  <v-card>
    <div v-if="profile && profile.authorities.includes('ADMIN')">
      <v-row class="ma-5">
        <v-col class="col-12 col-md-6">
          <v-checkbox v-model="USER" label="user" @change="enableDisableBtn"></v-checkbox>
          <v-checkbox v-model="ADMIN" label="admin" @change="enableDisableBtn"></v-checkbox>
        </v-col>
        <v-col class="col-12 col-md-6">
          <v-text-field v-model="username" label="Имя пользователя" @change="enableDisableBtn"
                        @keyup="enableDisableBtn"></v-text-field>
          <v-btn :disabled="btnDisabled" outlined class="indigo--text" @click="update">Update</v-btn>
        </v-col>
      </v-row>
    </div>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      ADMIN: false,
      USER: false,
      btnDisabled: true
    }
  },
  methods: {
    enableDisableBtn() {
      this.btnDisabled = this.ADMIN === false && this.USER === false || this.username.length === 0
    },
    update() {
      const user = {
        username: this.username,
        authorities: []
      }
      if (this.ADMIN === true) {
        user.authorities.push('ADMIN')
      }
      if (this.USER === true) {
        user.authorities.push('USER')
      }
      this.$resource('/api/users{/id}').update({id: parseInt(this.$route.params.id)}, user)
      this.$router.push('/')
    }
  },
  created() {
    this.$resource('/api/users{/id}').get({id: parseInt(this.$route.params.id)})
        .then(result => result.json()
            .then(data => {
              this.username = data.username
              if (data.authorities.includes('ADMIN')) {
                this.ADMIN = true
              }
              if (data.authorities.includes('USER')) {
                this.USER = true
                this.btnDisabled = false
              }
            }))
  }
}
</script>

<style>

</style>
