<template>
    <v-row>
        <car-card v-for="car in cars" :key="car.id" :car="car"></car-card>
    </v-row>
</template>

<script>
    import CarCard from 'components/app/CarCard.vue'

    export default {
        components: {
            CarCard
        },
        props: ['cars'],
        data() {
            return {
                car: null
            }
        }
    }
</script>

<style>

</style>