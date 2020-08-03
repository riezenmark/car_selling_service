<template>
    <v-card>
        <v-form class="ma-5">
            <v-row>
            <v-col class="col-12 col-md-6">
                <v-combobox v-model="markText" label="Марка" @change="enableDisableModel" clearable></v-combobox>
                <v-text-field v-model="price" label="Цена" @change="enableDisableBtn" @keyup="enableDisableBtn"></v-text-field>
                <v-file-input label="Фото"></v-file-input>
            </v-col>
            <v-col class="col-12 col-md-6">
                <v-autocomplete v-model="model.text" :disabled="model.disabled" label="Модель" @change="enableDisableBtn"
                            clearable></v-autocomplete>
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
    export default {
        data() {
            return {
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
            enableDisableModel() {
                if (this.markText) {
                    this.model.disabled = false
                } else {
                    this.model.disabled = true
                    this.model.text = ''
                    this.enableDisableBtn()
                }
            },
            enableDisableBtn() {
                if (this.model.text && this.price) {
                    this.addBtnDisabled = false
                } else {
                    this.addBtnDisabled = true
                    this.year.text = ''
                }
            },
            closeAddingPage() {
                this.$router.back()
            }
        }
    }
</script>

<style>

</style>