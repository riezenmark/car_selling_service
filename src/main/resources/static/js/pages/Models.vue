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
                <v-btn outlined class="indigo--text">Изменить</v-btn>
            </v-col>
        </v-row>
        <v-row dense>
            <v-col class="col-12 col-md-4">
                <v-combobox v-model="addModel.text" :disabled="addModel.disabled" label="Новая модель машины"
                            clearable></v-combobox>
            </v-col>
            <v-col class="col-12 col-md-2">
                <v-btn outlined class="indigo--text">Добавить</v-btn>
            </v-col>
            <v-col class="col-12 col-md-4">
                <v-autocomplete v-model="changeModel.text" :disabled="changeModel.disabled"
                                label="Модель" clearable></v-autocomplete>
            </v-col>
            <v-col class="col-12 col-md-2">
                <v-btn outlined class="indigo--text">Удалить</v-btn>
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
                addMark: ''
            }
        },
        methods: {
            enableDisableAddModel() {
                if (this.addMark) {
                    this.addModel.disabled = false
                } else {
                    this.addModel.disabled = true
                    this.addModel.text = ''
                }
            },
            enableDisableChangeModel() {
                if (this.changeMark) {
                    this.changeModel.disabled = false
                } else {
                    this.changeModel.disabled = true
                    this.changeModel.text = ''
                }
            }
        }
    }
</script>

<style>

</style>