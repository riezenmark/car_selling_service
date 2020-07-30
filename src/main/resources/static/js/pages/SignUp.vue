<template>
    <form action="/registration" method="post">
        <!--todo перенести регистрацию с отдельной страницы на кнопку в App, меняющуюся в зависимости от статуса пользователя-->
        <div>
            <label>Username:&nbsp;
                <input type="text" placeholder="Write username" v-model="username"/>
            </label>
        </div>
        <!--todo видимый пароль при нажатии-->
        <div>
            <label>Password:&nbsp;
                <input type="password" placeholder="Write password" v-model="password"/>
            </label>
        </div>
        <!--todo проверка пароля на фронте на совпадение и формат, запрос на существующего пользователя по времени-->
        <div>
            <label>Confirm Password:&nbsp;
                <input type="password" placeholder="Confirm password" v-model="confirmedPassword">
            </label>
        </div>
        <div>
            <input type="button" value="Sign Up" @click="register">
        </div>
    </form>
</template>

<script>
    export default {
        data() {
            return {
                username: '',
                password: '',
                confirmedPassword: ''
            }
        },
        methods: {
            register() {
                const user = {
                    username: this.username,
                    password: this.password,
                    confirmedPassword: this.confirmedPassword
                }

                this.$resource('/registration').save({}, user).then(result =>
                    result.json().then(data => {
                        //todo json response
                        if (data === true) {
                            //todo залогинить сразу
                            //todo проверить location.href [изменить редирект]
                            window.location.replace('/users')
                        } else {
                            this.username = ''
                            this.password = ''
                            this.confirmedPassword = ''
                        }
                    })
                )
            }
        }
    }
</script>

<style>

</style>