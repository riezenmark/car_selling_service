<template>
    <v-card>
        <v-form class="ma-5">
            <v-row>
                <v-col class="col-12 col-md-6">
                    <v-autocomplete v-model="markText" label="Марка" @change="enableDisableModel"
                                    clearable :items="makerNames"></v-autocomplete>
                    <v-text-field v-model="price" label="Цена" @change="enableDisableBtn"
                                  @keyup="enableDisableBtn"></v-text-field>
                    <v-file-input multiple="false" accept="image/*" v-model="files" label="Фото"></v-file-input>
                </v-col>
                <v-col class="col-12 col-md-6">
                    <v-autocomplete v-model="model.text" :disabled="model.disabled" label="Модель"
                                    @change="enableDisableBtn"
                                    clearable :items="modelNames"></v-autocomplete>
                    <v-autocomplete v-model="year" label="Год выпуска" :items="years" clearable></v-autocomplete>
                </v-col>
            </v-row>
            <v-row justify="space-around">
                <v-col class="col-12 col-md-4">
                    <v-radio-group v-model="transmission" label="Коробка передач">
                        <v-radio label="Автомат"></v-radio>
                        <v-radio label="Механика"></v-radio>
                    </v-radio-group>
                </v-col>
                <v-col class="col-12 col-md-4">
                    <v-radio-group v-model="engineType" label="Тип двигателя">
                        <v-radio label="Бензин"></v-radio>
                        <v-radio label="Гибрид"></v-radio>
                        <v-radio label="Дизель"></v-radio>
                        <v-radio label="Электро"></v-radio>
                    </v-radio-group>
                </v-col>
                <v-col class="col-12 col-md-4">
                    <v-btn :disabled="addBtnDisabled" outlined class="indigo--text mt-5" @click="publishCar">Опубликовать</v-btn>
                    <div class="mt-5">
                        <v-btn outlined class="indigo--text" @click="closeAddingPage">Закрыть</v-btn>
                    </div>
                </v-col>
            </v-row>
        </v-form>
    </v-card>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        computed: mapState(['makers']),
        data() {
            return {
                makerNames: [],
                modelNames: [],
                years: [],
                markText: '',
                model: {
                    text: '',
                    disabled: true
                },
                addBtnDisabled: true,
                price: '',
                year: 1960,
                transmission: 0,
                engineType: 0,
                files: []
            }
        },
        methods: {
            getModels() {
                this.$resource('/models').get({makerName: this.markText}).then(result =>
                    result.json().then(data =>
                        data.forEach(model => this.modelNames.push(model.name))
                    )
                )
            },
            enableDisableModel() {
                this.model.text = ''
                this.modelNames = []
                if (this.markText) {
                    this.model.disabled = false
                    this.getModels()
                } else {
                    this.model.disabled = true
                    this.enableDisableBtn()
                }
            },
            enableDisableBtn() {
                this.addBtnDisabled = !(this.model.text && this.price);
            },
            closeAddingPage() {
                this.$router.back()
            },
            publishCar() {
                const formData = new FormData();
                formData.append("makerName", this.markText)
                formData.append("modelName", this.model.text)
                formData.append("price", this.price)
                formData.append("yearOfProduction", this.year)
                let transmission
                switch (this.transmission) {
                    case 0:
                        transmission = 'AUTOMATIC'
                        break
                    case 1:
                        transmission = 'MECHANIC'
                        break
                }
                formData.append("transmission", transmission)
                let engineType
                switch (this.engineType) {
                    case 0:
                        engineType = 'GASOLINE'
                        break
                    case 1:
                        engineType = 'DIESEL'
                        break
                    case 2:
                        engineType = 'HYBRID'
                        break
                    case 3:
                        engineType = 'ELECTRO'
                        break
                }
                formData.append("engineType", engineType)
                formData.append("file", this.files[0])

                this.$resource('/cars').save(formData)

                this.markText = ''
                this.price = 0
                this.year = 1960
                this.files = []
                this.engineType = 0
                this.transmission = 0
                this.enableDisableModel()
            }
        },
        created() {
            this.makers.forEach(maker => this.makerNames.push(maker.name))
            const year = new Date().getFullYear()
            for (let i = 1960; i <= year; i++) {
                this.years.push(i)
            }
        },
        watch: {
            'price'(value) {
                this.$nextTick(() =>
                    this.price = value.replace(/[^0-9]/g, '')
                )
            }
        }
    }
</script>

<style>

</style>