import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        profile: frontendData.profile,
        makers: frontendData.makers
    },
    mutations: {
        addChosenCarMutation(state, chosenCar) {
            state.profile.chosenCars = [
                ...state.profile.chosenCars,
                chosenCar
            ]
        },
        removeChosenCarMutation(state, chosenCar) {
            const deletionIndex = state.profile.chosenCars.findIndex(item => item.id === chosenCar.id)

            if (deletionIndex > -1) {
                state.profile.chosenCars = [
                    ...state.profile.chosenCars.slice(0, deletionIndex),
                    ...state.profile.chosenCars.slice(deletionIndex + 1)
                ]
            }
        }
    },
    actions: {
        async addChosenCarAction({commit, state}, chosenCar) {
            //todo заменить chosencarapi
            const result = await chosenCarApi.add(chosenCar)
            const data = await result.json()
            const index = state.profile.chosenCars.findIndex(item => item.id === chosenCar.id)

            if (index <= -1) {
                commit('addChosenCarMutation', data)
            }
        },
        async removeChosenCarAction({commit}, chosenCar) {
            const result = await chosenCarApi.remove(chosenCar.id)

            if (result.ok) {
                commit('addChosenCarMutation', chosenCar)
            }
        }
    }
})