<template>
  <v-row>
    <car-card v-for="car in cars" :key="car.id" :car="car" :deleteCar="deleteCar"></car-card>
  </v-row>
</template>

<script>
import CarCard from 'components/cars/CarCard.vue'

export default {
  components: {
    CarCard
  },
  methods: {
    deleteCar(car) {
      this.$resource('/api/cars{/id}').remove({id: car.id}).then(result => {
        if (result.ok) {
          this.cars.splice(this.cars.indexOf(car), 1)
        }
      })
    }
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