<template>
    <div>
        <input type="text" placeholder="Write username" v-model="username"/>
        <input type="text" placeholder="Write password" v-model="password"/>
        <input type="text" placeholder="Write email" v-model="email"/>
        <input type="button" value="Save" @click="save"/>
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return i
            }
        }
        return -1
    }

    export default {
        props: ['users', 'user'],
        data() {
            return {
                id: '',
                username: '',
                password: '',
                email: ''
            }
        },
        watch: {
            //todo проверить без oldValue
            user: function(newValue, oldValue) {
                this.id = newValue.id
                this.username = newValue.username
                this.email = newValue.email
            }
        },
        methods: {
            save() {
                const user = {
                    username: this.username,
                    password: this.password,
                    email: this.email
                }

                if (this.id) {
                    this.$resource('/users{/id}').update({id: this.id}, user).then(result =>
                        result.json().then(data => {
                            const index = getIndex(user, data.id)
                            this.users.splice(index, 1, data)
                            this.id = '';
                            this.username = ''
                            this.password = ''//todo исчезающую строку пароля и set password в sql(json view?)
                            this.email = ''
                        })
                    );
                } else {
                    this.$resource('/users{/id}').save({}, user).then(result =>
                        result.json().then(data => {
                            this.users.push(data)
                            this.username = ''
                            this.password = ''
                            this.email = ''
                        })
                    )
                }
            }
        }
    }
</script>

<style>

</style>
