<template>
  <v-card v-if="userHasCar">
    <v-row class="ma-5">
      <v-col class="col-12 col-md-6">
        <v-autocomplete v-model="markText" label="Марка" clearable :items="makerNames"
                        @change="enableDisableModel"></v-autocomplete>
        <v-autocomplete v-model="model.text" :disabled="model.disabled" label="Модель" clearable
                        :items="modelNames" @change="enableDisableBtn"></v-autocomplete>
        <v-autocomplete v-model="year" label="Год выпуска" :items="years" clearable
                        @change="enableDisableBtn"></v-autocomplete>
        <v-text-field v-model="price" label="Цена" @change="enableDisableBtn" @keyup="enableDisableBtn"></v-text-field>
      </v-col>
      <v-col class="col-12 col-md-3">
        <v-radio-group v-model="transmission" label="Коробка передач">
          <v-radio label="Автомат"></v-radio>
          <v-radio label="Механика"></v-radio>
        </v-radio-group>
      </v-col>
      <v-col class="col-12 col-md-3">
        <v-radio-group v-model="engineType" label="Коробка передач">
          <v-radio label="Бензин"></v-radio>
          <v-radio label="Гибрид"></v-radio>
          <v-radio label="Дизель"></v-radio>
          <v-radio label="Электро"></v-radio>
        </v-radio-group>
      </v-col>
    </v-row>
    <v-row class="ma-5">
      <v-col>
        <v-btn :disabled="btnDisabled" outlined class="indigo--text" @click="updateCar">Обновить</v-btn>
        <v-btn outlined class="indigo--text" @click="closeUpdatingPage">Закрыть</v-btn>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
import {mapState} from "vuex";

export default {
  props: ['cars'],
  computed: mapState(['makers', 'addedCars', 'profile']),
  data() {
    return {
      car: null,
      makerNames: [],
      models: [],
      modelNames: [],
      years: [],
      year: null,
      price: '',
      markText: '',
      model: {
        text: '',
        disabled: false
      },
      transmission: null,
      engineType: null,
      btnDisabled: false
    }
  },
  methods: {
    userHasCar() {
      this.addedCars.forEach(car => {
        if (car.id === this.car.id) {
          return true
        }
      })
      return false
    },
    getModels() {
      let makerId
      this.makers.forEach(maker => {
        if (maker.name === this.markText) {
          makerId = maker.id
        }
      })
      this.$resource('/api/models').get({maker: makerId}).then(result =>
          result.json().then(data =>
              data.forEach(model => {
                this.models.push(model)
                this.modelNames.push(model.name)
              })
          )
      )
    },
    enableDisableModel() {
      this.model.text = ''
      this.modelNames = []
      this.models = []
      if (this.markText) {
        this.model.disabled = false
        this.getModels()
      } else {
        this.model.disabled = true
        this.enableDisableBtn()
      }
    },
    closeUpdatingPage() {
      this.$router.back()
    },
    enableDisableBtn() {
      this.btnDisabled = !(this.markText && this.model.text && this.year && this.price)
    },
    updateCar() {
      let makerId
      this.makers.forEach(maker => {
        if (maker.name === this.markText) {
          makerId = maker.id
        }
      })
      let modelId
      this.models.forEach(model => {
        if (model.name === this.model.text) {
          modelId = model.id
        }
      })
      let transmission
      switch (this.transmission) {
        case 0:
          transmission = 'AUTOMATIC'
          break
        case 1:
          transmission = 'MECHANIC'
          break
      }
      let engineType
      switch (this.engineType) {
        case 0:
          engineType = 'GASOLINE'
          break
        case 1:
          engineType = 'HYBRID'
          break
        case 2:
          engineType = 'DIESEL'
          break
        case 3:
          engineType = 'ELECTRO'
          break
      }
      const car = {
        id: parseInt(this.$route.params.id),
        maker: {
          id: makerId
        },
        model: {
          id: modelId
        },
        price: this.price,
        yearOfProduction: this.year,
        transmission: transmission,
        engineType: engineType,
        user: this.profile
      }
      this.$resource('/api/cars{/id}').update({id: car.id}, car)
      this.$router.push('/')
    }
  },
  created() {
    this.cars.forEach(c => {
      if (c.id === parseInt(this.$route.params.id)) {
        this.car = c
      }
    })
    if (this.car === null) {
      this.$router.push('/')
    }
    this.makers.forEach(maker => this.makerNames.push(maker.name))
    const year = new Date().getFullYear()
    for (let i = 1960; i <= year; i++) {
      this.years.push(i)
    }
    this.makers.forEach(maker => {
      if (maker.id === this.car.maker.id) {
        this.markText = this.makerNames[this.makerNames.indexOf(maker.name)]
      }
    })
    this.getModels()
    this.model.text = this.car.model.name
    this.year = this.car.yearOfProduction
    this.price = this.car.price
    switch (this.car.transmission) {
      case 'AUTOMATIC':
        this.transmission = 0
        break
      case 'MECHANIC':
        this.transmission = 1
        break
    }
    switch (this.car.engineType) {
      case 'GASOLINE':
        this.engineType = 0
        break
      case 'HYBRID':
        this.engineType = 1
        break
      case 'DIESEL':
        this.engineType = 2
        break
      case 'ELECTRO':
        this.engineType = 3
        break
    }
  }
}
</script>

<style>

</style>