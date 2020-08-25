<template>
  <div>
    <div v-if="profile && profile.authorities.includes('ADMIN')">
      <v-row dense>
        <v-col class="col-12 col-md-4">
          <v-combobox v-model="addMark" label="Новая марка" clearable
                      @change="enableDisableAddModel" :items="makerNames"></v-combobox>
        </v-col>
        <v-col class="col-12 col-md-2">
          <v-btn :disabled="addMakerBtnDisabled" outlined class="indigo--text" @click="addNewCarMakerIfNotExists">
            Добавить
          </v-btn>
        </v-col>
        <v-col class="col-12 col-md-2">
          <v-autocomplete v-model="changeMark" label="Марка" clearable
                          @change="enableDisableChangeModel" :items="makerNames"></v-autocomplete>
        </v-col>
        <v-col class="col-12 col-md-2">
          <v-btn outlined :disabled="changeMakerBtnDisabled" class="indigo--text" @click="setNewMakerParameters">
            Изменить
          </v-btn>
        </v-col>
        <v-col class="col-12 col-md-2">
          <v-btn :disabled="deleteMakerBtnDisabled" outlined class="indigo--text" @click="deleteMaker">Удалить</v-btn>
        </v-col>
      </v-row>
      <v-row dense>
        <v-col class="col-12 col-md-4">
          <v-combobox v-model="addModel.text" :disabled="addModel.disabled" label="Новая модель машины"
                      clearable @change="enableDisableAddModelBtn"></v-combobox>
        </v-col>
        <v-col class="col-12 col-md-2">
          <v-btn :disabled="addModelBtnDisabled" outlined class="indigo--text" @click="addNewCarModel">Добавить</v-btn>
        </v-col>
        <v-col class="col-12 col-md-2">
          <v-autocomplete v-model="changeModel.text" :disabled="changeModel.disabled"
                          label="Модель" clearable @change="enableDisableChangeAndDeleteModelBtn"
                          :items="modelNames"></v-autocomplete>
        </v-col>
        <v-col class="col-12 col-md-2">
          <v-btn outlined :disabled="changeModelBtnDisabled" class="indigo--text" @click="setNewModelParameters">
            Изменить
          </v-btn>
        </v-col>
        <v-col class="col-12 col-md-2">
          <v-btn :disabled="deleteModelBtnDisabled" outlined class="indigo--text" @click="deleteModel">Удалить</v-btn>
        </v-col>
      </v-row>
      <v-divider></v-divider>
      <v-row>
        <v-col class="col-12 col-md-3">
          <v-text-field v-model="makerParam.text" :disabled="makerParam.disabled"></v-text-field>
        </v-col>
        <v-col class="col-12 col-md-3">
          <v-btn :disabled="submitChangesMakerBtnDisabled" outlined class="indigo--text" @click="updateMaker">
            Изменить
          </v-btn>
        </v-col>
        <v-col class="col-12 col-md-3">
          <v-text-field v-model="modelParam.text" :disabled="modelParam.disabled"></v-text-field>
        </v-col>
        <v-col class="col-12 col-md-3">
          <v-btn :disabled="submitChangesModelBtnDisabled" outlined class="indigo--text" @click="updateModel">
            Изменить
          </v-btn>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import {mapState} from "vuex";

export default {
  data() {
    return {
      makerBuffer: '',
      modelBuffer: '',
      makerNames: [],
      modelNames: [],
      models: [],
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
      changeMakerBtnDisabled: true,
      changeModelBtnDisabled: true,
      addMakerBtnDisabled: true,
      addModelBtnDisabled: true,
      deleteMakerBtnDisabled: true,
      deleteModelBtnDisabled: true,
      submitChangesModelBtnDisabled: true,
      submitChangesMakerBtnDisabled: true,
      makerParam: {
        text: '',
        disabled: true
      },
      modelParam: {
        text: '',
        disabled: true
      }
    }
  },
  computed: mapState(['makers', 'profile']),
  methods: {
    addNewCarMakerIfNotExists() {
      if (!this.makerNames.includes(this.addMark)) {
        const maker = {
          name: this.addMark
        }
        this.$resource('/api/makers').save(maker)
      }
      this.addMark = ''
      this.enableDisableAddMakerBtn()
    },
    enableDisableAddModelBtn() {
      this.addModelBtnDisabled = !this.addModel.text;
    },
    getModels() {
      let makerId
      this.makers.forEach(maker => {
        if (maker.name === this.changeMark) {
          makerId = maker.id
        }
      })
      this.$resource('/api/models').get({maker: makerId}).then(result =>
          result.json().then(data =>
              data.forEach(model => {
                this.modelNames.push(model.name)
                this.models.push(model)
              })
          )
      )
    },
    enableDisableAddModel() {
      this.enableDisableAddMakerBtn()
      if (this.addMark) {
        this.addModel.disabled = false
      } else {
        this.addModel.disabled = true
        this.addModel.text = ''
      }
    },
    enableDisableChangeModel() {
      this.changeModel.text = ''
      this.modelNames = []
      if (this.changeMark) {
        this.changeModel.disabled = false
        this.changeMakerBtnDisabled = false
        this.deleteMakerBtnDisabled = false
        this.getModels()
      } else {
        this.changeModel.text = ''
        this.changeModel.disabled = true
        this.changeMakerBtnDisabled = true
        this.deleteMakerBtnDisabled = true
        this.enableDisableChangeAndDeleteModelBtn()
      }
    },
    enableDisableAddMakerBtn() {
      if (this.addMark) {
        this.addMakerBtnDisabled = false
      } else {
        this.addMakerBtnDisabled = true
        this.addModel.disabled = true
        this.addModel.text = ''
      }
    },
    enableDisableChangeAndDeleteModelBtn() {
      if (this.changeModel.text) {
        this.changeModelBtnDisabled = false
        this.deleteModelBtnDisabled = false
      } else {
        this.changeModelBtnDisabled = true
        this.deleteModelBtnDisabled = true
        this.changeModel.text = ''
      }
    },
    addNewCarModel() {
      let makerId
      this.makers.forEach(maker => {
        if (maker.name === this.addMark) {
          makerId = maker.id
        }
      })
      const model = {
        maker: {
          id: makerId
        },
        name: this.addModel.text
      }

      this.$resource('/api/models').save(model)

      this.addMark = ''
      this.addModel.text = ''
      this.addModel.disabled = true
      this.addModelBtnDisabled = true
      this.addMakerBtnDisabled = true
    },
    setNewModelParameters() {
      this.modelParam.text = this.changeModel.text
      this.makerBuffer = this.changeMark
      this.modelBuffer = this.changeModel.text
      this.changeMark = ''
      this.modelParam.disabled = false
      this.submitChangesModelBtnDisabled = false
      this.enableDisableChangeModel()
    },
    updateModel() {
      let makerId
      this.makers.forEach(maker => {
        if (maker.name === this.makerBuffer) {
          makerId = maker.id
        }
      })
      let modelId
      this.models.forEach(model => {
        if (model.name === this.modelBuffer) {
          modelId = model.id
        }
      })
      this.models = []
      const model = {
        maker: {
          id: makerId
        },
        name: this.modelParam.text
      }
      this.$resource('/api/models{/id}').update({id: modelId}, model)
      this.modelBuffer = ''
      this.makerBuffer = ''
      this.modelParam.text = ''
      this.modelParam.disabled = true
      this.submitChangesModelBtnDisabled = true
    },
    deleteModel() {
      let modelId
      this.models.forEach(model => {
        if (model.name === this.changeModel.text) {
          modelId = model.id
        }
      })
      this.models = []

      this.$resource('/api/models{/id}').remove({id: modelId})
      this.changeMark = ''
      this.enableDisableChangeModel()
    },
    setNewMakerParameters() {
      this.makerParam.text = this.changeMark
      this.makerParam.disabled = false
      this.submitChangesMakerBtnDisabled = false
      this.makerBuffer = this.changeMark
      this.changeMark = ''
      this.enableDisableChangeModel()
    },
    deleteMaker() {
      let makerId
      this.makers.forEach(maker => {
        if (maker.name === this.changeMark) {
          makerId = maker.id
        }
      })
      this.$resource('/api/makers{/id}').remove({id: makerId})
      this.changeMark = ''
      this.enableDisableChangeModel()
    },
    updateMaker() {
      let makerId
      this.makers.forEach(maker => {
        if (maker.name === this.makerBuffer) {
          makerId = maker.id
        }
      })
      this.makerBuffer = ''

      const maker = {
        name: this.makerParam.text
      }

      this.$resource('/api/makers{/id}').update({id: makerId}, maker)
      this.makerParam.text = ''
      this.makerParam.disabled = true
      this.submitChangesMakerBtnDisabled = true
    }
  },
  created() {
    this.makers.forEach(maker => this.makerNames.push(maker.name))
  },
}
</script>

<style>

</style>