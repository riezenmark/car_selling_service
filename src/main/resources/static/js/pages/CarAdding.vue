<template>
    <v-card>
        <v-form class="ma-5">
            <v-row>
                <v-col class="col-12 col-md-6">
                    <v-autocomplete v-model="markText" label="Марка" @change="enableDisableModel"
                                    clearable :items="makerNames"></v-autocomplete>
                    <v-text-field v-model="price" label="Цена" @change="enableDisableBtn"
                                  @keyup="enableDisableBtn"></v-text-field>
                    <v-file-input label="Фото"></v-file-input>
                </v-col>
                <v-col class="col-12 col-md-6">
                    <v-autocomplete v-model="model.text" :disabled="model.disabled" label="Модель"
                                    @change="enableDisableBtn"
                                    clearable :items="modelNames"></v-autocomplete>
                    <v-combobox label="Год выпуска" clearable></v-combobox>
                </v-col>
            </v-row>
            <v-row justify="space-around">
                <v-col class="col-12 col-md-4">
                    <v-radio-group label="Коробка передач">
                        <v-radio label="Автомат"></v-radio>
                        <v-radio label="Механика"></v-radio>
                    </v-radio-group>
                </v-col>
                <v-col class="col-12 col-md-4">
                    <v-radio-group label="Тип двигателя">
                        <v-radio label="Бензин"></v-radio>
                        <v-radio label="Гибрид"></v-radio>
                        <v-radio label="Дизель"></v-radio>
                        <v-radio label="Электро"></v-radio>
                    </v-radio-group>
                </v-col>
                <v-col class="col-12 col-md-4">
                    <v-btn :disabled="addBtnDisabled" outlined class="indigo--text mt-5">Опубликовать</v-btn>
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
                markText: '',
                model: {
                    text: '',
                    disabled: true
                },
                addBtnDisabled: true,
                price: ''
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
            }
        },
        created() {
            this.makers.forEach(maker => this.makerNames.push(maker.name))
        }
    }
</script>

<style>

</style>