<template>
    <div>
        <div style="background-color: green"><h3>Car Selling App</h3></div>
        <!--todo имя профиля (из динамического роутера?) и логаут если пользователь зарегистрирован-->
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        //todo add vuex store
        data() {
            return {
                cars: userData.cars,
                profile: userData.profile
            }
        }
    }
</script>

<style>

</style>
