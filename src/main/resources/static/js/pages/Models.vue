<template>
    <div>
        <v-row dense>
            <v-col class="col-12 col-md-4">
                <v-combobox v-model="addMark" label="Новая марка" clearable
                            @change="enableDisableAddModel" :items="makerNames"></v-combobox>
            </v-col>
            <v-col class="col-12 col-md-2">
            </v-col>
            <v-col class="col-12 col-md-4">
                <v-autocomplete v-model="changeMark" label="Марка" clearable
                                @change="enableDisableChangeModel" :items="makerNames"></v-autocomplete>
            </v-col>
            <v-col class="col-12 col-md-2">
                <v-btn outlined :disabled="changeBtnDisabled" class="indigo--text" @click="setNewModelParameters">
                    Изменить
                </v-btn>
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
                                label="Модель" clearable @change="enableDisableChangeAndDeleteBtn"
                                :items="modelNames"></v-autocomplete>
            </v-col>
            <v-col class="col-12 col-md-2">
                <v-btn :disabled="deleteBtnDisabled" outlined class="indigo--text" @click="deleteModel">Удалить</v-btn>
            </v-col>
        </v-row>
        <v-divider></v-divider>
        <v-row>
            <v-col class="col-12 col-md-4">
                <v-text-field v-model="makerParam.text" :disabled="makerParam.disabled"></v-text-field>
            </v-col>
            <v-col class="col-12 col-md-4">
                <v-text-field v-model="modelParam.text" :disabled="modelParam.disabled"></v-text-field>
            </v-col>
            <v-col class="col-12 col-md-4">
                <v-btn :disabled="submitChangesBtnDisabled" outlined class="indigo--text" @click="submitChanges">
                    Подтвердить изменения
                </v-btn>
            </v-col>
        </v-row>
    </div>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        data() {
            return {
                makerNames: [],
                modelNames: [],
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
                deleteBtnDisabled: true,
                submitChangesBtnDisabled: true,
                makerParam: {
                    text: '',
                    disabled: true
                },
                modelParam: {
                    text: '',
                    disabled: true
                },
                previousModel: '',
                previousMaker: ''
            }
        },
        computed: mapState(['makers']),
        methods: {
            getModels() {
                this.$resource('/models').get({makerName: this.changeMark}).then(result =>
                    result.json().then(data =>
                        data.forEach(model => this.modelNames.push(model.name))
                    )
                )
            },
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
                this.changeModel.text = ''
                this.modelNames = []
                if (this.changeMark) {
                    this.changeModel.disabled = false
                    this.getModels()
                } else {
                    this.changeModel.disabled = true
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
                    maker: {
                        name: this.addMark
                    },
                    name: this.addModel.text
                }

                this.$resource('/models').save(model)

                this.addMark = ''
                this.addModel.text = ''
                this.addModel.disabled = true
                this.addBtnDisabled = true
            },
            setNewModelParameters() {
                this.makerParam.text = this.changeMark
                this.modelParam.text = this.changeModel.text
                this.previousMaker = this.changeMark
                this.previousModel = this.changeModel.text
                this.changeMark = ''
                this.makerParam.disabled = false
                this.modelParam.disabled = false
                this.submitChangesBtnDisabled = false
                this.enableDisableChangeModel()
            },
            submitChanges() {
                const modelInfo = []
                const previousModel = {
                    name: this.previousModel,
                    maker: {
                        name: this.previousMaker
                    },
                }
                const model = {
                    name: this.modelParam.text,
                    maker: {
                        name: this.makerParam.text
                    }
                }
                modelInfo.push(previousModel)
                modelInfo.push(model)
                this.$resource('/models').update(modelInfo)
                this.makerParam.text = ''
                this.modelParam.text = ''
                this.previousModel = ''
                this.previousMaker = ''
                this.makerParam.disabled = true
                this.modelParam.disabled = true
                this.submitChangesBtnDisabled = true
            },
            deleteModel() {
                const model = {
                    maker: {
                        name: this.changeMark
                    },
                    name: this.changeModel.text
                }

                this.$resource('/models').remove({}, model)
                this.changeMark = ''
                this.enableDisableChangeModel()
            }
        },
        created() {
            this.makers.forEach(maker => this.makerNames.push(maker.name))
        },
    }
</script>

<style>

</style>