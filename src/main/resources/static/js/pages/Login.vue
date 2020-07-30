<template>
    <div>
        <form>
            <div>
                <label>Username:&nbsp;
                    <input type="text" placeholder="Write username" v-model="username">
                </label>
            </div>
            <div>
                <label>Password:&nbsp;
                    <input type="password" placeholder="Write password" v-model="password">
                </label>
            </div>
            <div>
                <input type="button" value="Login" @click="login">
            </div>
        </form>
        Don't have an account? <button @click="signUpPage">Sign Up</button>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                username: '',
                password: ''
            }
        },
        methods: {
            login() {
                const user = {
                    username: this.username,
                    password: this.password
                }

                this.$resource('/login').save({}, user).then(result =>
                    result.json().then(data => {
                        //todo json response
                        if (data === true) {
                            //todo [изменить редирект]
                            this.$router.push('/')
                        } else {
                            this.username = ''
                            this.password = ''
                        }
                    })
                )
            },
            signUpPage() {
                this.$router.push('/signup')
            }
        }
    }
</script>

<style>

</style>