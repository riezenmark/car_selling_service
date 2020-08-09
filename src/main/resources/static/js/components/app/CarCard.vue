<template>
    <v-col class="col-12 col-md-4">
        <v-card>
            <v-img :src="/img/+car.filename">
            </v-img>
            <v-card-title>{{car.maker.name}}<v-spacer></v-spacer>{{car.price}}</v-card-title>
            <v-card-subtitle>{{car.model.name}}<v-spacer></v-spacer>{{car.yearOfProduction}}</v-card-subtitle>
            <v-card-actions>
                <v-btn outlined class="indigo--text">Send Mail</v-btn>
                <div v-if="showButtons">
                    <v-btn icon class="indigo--text mx-2">
                        <v-icon>mdi-menu</v-icon>
                    </v-btn>
                    <v-btn icon class="indigo--text">
                        <v-icon>mdi-trash-can-outline</v-icon>
                    </v-btn>
                </div>
                <v-spacer></v-spacer>
                <v-btn icon @click="show = !show">
                    <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                </v-btn>
            </v-card-actions>
            <v-expand-transition>
                <div v-show="show">
                    <v-divider></v-divider>
                    <v-card-text>{{car.transmission}}, {{car.engineType}}</v-card-text>
                </div>
            </v-expand-transition>
        </v-card>
    </v-col>
</template>

<script>
    import {mapState} from "vuex";

    export default {
        props: ['car'],
        data() {
            return {
                show: false,
                showButtons: false
            }
        },
        computed: mapState(['profile']),
        created() {
            if (this.profile) {
                if (this.car.user.id && this.car.user.id === this.profile.id) {
                    this.showButtons = true
                }
                if (this.car.user === this.profile.id) {
                    this.showButtons = true
                }
            }
        }
    }
</script>

<style>

</style>