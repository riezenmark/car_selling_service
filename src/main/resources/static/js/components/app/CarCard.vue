<template>
    <v-col class="col-12 col-md-4">
        <v-card>
            <v-img :src="/img/+car.filename">
            </v-img>
            <v-card-title>{{car.maker.name}}<v-spacer></v-spacer>{{car.price}}</v-card-title>
            <v-card-subtitle>{{car.model.name}}<v-spacer></v-spacer>{{car.yearOfProduction}}</v-card-subtitle>
            <v-card-actions>
                <v-btn outlined class="indigo--text">Send Mail</v-btn>
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
    export default {
        props: ['car'],
        data() {
            return {
                show: false
            }
        }
    }
</script>

<style>

</style>