<template>
    <div>
        <v-row dense>
            <v-col class="col-12 col-md-4">
                <v-combobox v-model="addMark" label="Новая марка" clearable
                            @change="enableDisableAddModel"></v-combobox>
            </v-col>
            <v-col class="col-12 col-md-2">

            </v-col>
            <v-col class="col-12 col-md-4">
                <v-autocomplete v-model="changeMark" label="Марка" clearable
                                @change="enableDisableChangeModel"></v-autocomplete>
            </v-col>
            <v-col class="col-12 col-md-2">
                <v-btn outlined :disabled="changeBtnDisabled" class="indigo--text">Изменить</v-btn>
            </v-col>
        </v-row>
        <v-row dense>
            <v-col class="col-12 col-md-4">
                <v-combobox v-model="addModel.text" :disabled="addModel.disabled" label="Новая модель машины"
                            clearable @change="enableDisableAddBtn"></v-combobox>
            </v-col>
            <v-col class="col-12 col-md-2">
                <v-btn :disabled="addBtnDisabled" outlined class="indigo--text" @click="addNewCarModel">Добавить</v-btn>
            </v-col>
            <v-col class="col-12 col-md-4">
                <v-autocomplete v-model="changeModel.text" :disabled="changeModel.disabled"
                                label="Модель" clearable @change="enableDisableChangeAndDeleteBtn"></v-autocomplete>
            </v-col>
            <v-col class="col-12 col-md-2">
                <v-btn :disabled="deleteBtnDisabled" outlined class="indigo--text">Удалить</v-btn>
            </v-col>
        </v-row>
        <v-divider></v-divider>
        <v-simple-table>
            <thead>
            <tr>
                <th>Марка</th>
                <th>Модель</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </v-simple-table>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                changeModel: {
                    disabled: true,
                    text: ''
                },
                changeMark: '',
                addModel: {
                    disabled: true,
                    text: ''
                },
                addMark: '',
                changeBtnDisabled: true,
                addBtnDisabled: true,
                deleteBtnDisabled: true
            }
        },
        methods: {
            enableDisableAddModel() {
                if (this.addMark) {
                    this.addModel.disabled = false
                } else {
                    this.addModel.disabled = true
                    this.addModel.text = ''
                    this.enableDisableAddBtn()
                }
            },
            enableDisableChangeModel() {
                if (this.changeMark) {
                    this.changeModel.disabled = false
                } else {
                    this.changeModel.disabled = true
                    this.changeModel.text = ''
                    this.enableDisableChangeAndDeleteBtn()
                }
            },
            enableDisableAddBtn() {
                if (this.addModel.text) {
                    this.addBtnDisabled = false
                } else {
                    this.addBtnDisabled = true
                    this.addModel.text = ''
                }
            },
            enableDisableChangeAndDeleteBtn() {
                if (this.changeModel.text) {
                    this.changeBtnDisabled = false
                    this.deleteBtnDisabled = false
                } else {
                    this.changeBtnDisabled = true
                    this.deleteBtnDisabled = true
                    this.changeModel.text = ''
                }
            },
            addNewCarModel() {
                const model = {
                    manufacturer: {
                        name: this.addMark
                    },
                    name: this.addModel.text
                }

                this.$resource('/models').save(model)

                this.addMark = ''
                this.addModel.text = ''
                this.addModel.disabled = true
                this.addBtnDisabled = true
            }
        }
    }
</script>

<style>

</style>