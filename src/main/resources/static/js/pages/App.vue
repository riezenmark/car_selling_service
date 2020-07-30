<template>
    <div>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                users: userData.cars,
                profile: userData.profile
            }
        }
    }
</script>

<style>

</style>
