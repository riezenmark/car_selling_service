<template>
    <v-app>
        <v-navigation-drawer v-model="drawer" app clipped width="300">
            <v-container>
                <v-autocomplete v-model="mark" outlined label="Марка" placeholder="Любая" clearable dense
                                @change="enableDisableModel" :items="makerNames"></v-autocomplete>
                <v-autocomplete v-model="model.text" :disabled="model.disabled" outlined label="Модель"
                                placeholder="Любая" clearable dense :items="modelNames"></v-autocomplete>
                <v-row no-gutters>
                    <v-col>
                        <v-text-field v-model="priceFrom" outlined class="rounded-r-0" label="Цена от" placeholder="0"
                                      dense></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field v-model="priceTo" outlined class="rounded-l-0" label="до, руб." placeholder="max"
                                      dense></v-text-field>
                    </v-col>
                </v-row>
                <v-row no-gutters>
                    <v-col>
                        <v-autocomplete v-model="yearFrom" outlined class="rounded-r-0" label="Год от"
                                        placeholder="1960" dense :items="years"></v-autocomplete>
                    </v-col>
                    <v-col>
                        <v-autocomplete
                                v-model="yearTo"
                                outlined
                                class="rounded-l-0"
                                label="до"
                                :placeholder="new Date().getFullYear().toString()"
                                dense :items="years">
                        </v-autocomplete>
                    </v-col>
                </v-row>
                <span>Коробка передач</span>
                <v-row justify="space-around">
                    <v-checkbox v-model="AUTOMATIC" label="автомат"></v-checkbox>
                    <v-checkbox v-model="MECHANIC" label="механика"></v-checkbox>
                </v-row>
                <span>Тип двигателя</span>
                <v-row justify="space-around">
                    <v-col>
                        <v-checkbox v-model="GASOLINE" label="бензин"></v-checkbox>
                        <v-checkbox v-model="DIESEL" label="дизель"></v-checkbox>
                    </v-col>
                    <v-col>
                        <v-checkbox v-model="HYBRID" label="гибрид"></v-checkbox>
                        <v-checkbox v-model="ELECTRO" label="электро"></v-checkbox>
                    </v-col>
                </v-row>
            </v-container>
            <v-row justify="space-around" style="position: sticky; bottom: 0;" class="indigo">
                <v-btn outlined :disabled="findBtnDisabled" class="white--text"
                       style="margin-bottom: 5px; margin-top: 5px" @click="searchForCars">
                    Найти объявления
                </v-btn>
            </v-row>
        </v-navigation-drawer>
        <v-app-bar app class="indigo" clipped-left>
            <v-app-bar-nav-icon @click.stop="drawer = !drawer" class="white--text"></v-app-bar-nav-icon>
            <v-btn class="ml-2" icon @click="mainPage">
                <v-icon large class="white--text">mdi-car-sports</v-icon>
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn icon class="white--text" @click="carAddingPage(profile)">
                <v-icon>mdi-plus</v-icon>
            </v-btn>
            <v-btn v-if="!profile" text class="white--text" href="/login">Вход и регистрация</v-btn>
            <v-btn v-if="profile" outlined class="white--text mx-2" @click="usersCars">{{ profile.name }}</v-btn>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
            <v-btn
                    outlined
                    v-if="profile && profile.email === 'riezenmark@gmail.com'"
                    @click="adminPage"
                    class="white--text ml-2"
            >ADMIN
            </v-btn>
        </v-app-bar>
        <v-main>
            <v-container fluid>
                <router-view :cars="cars"></router-view>
            </v-container>
        </v-main>
        <v-footer app color="indigo" class="white--text" inset>
            <span>CarSelling Company</span>
            <v-spacer></v-spacer>
            <span>&copy; {{ new Date().getFullYear() }}</span>
        </v-footer>
    </v-app>
</template>

<script>
    import {mapState} from 'vuex'

    export default {

        data: () => ({
            makerNames: [],
            modelNames: [],
            priceFrom: 0,
            priceTo: null,
            yearFrom: 1960,
            yearTo: new Date().getFullYear(),
            years: [],
            drawer: null,
            mark: '',
            model: {
                text: '',
                disabled: true
            },
            cars: [],
            findBtnDisabled: true,
            GASOLINE: '',
            DIESEL: '',
            HYBRID: '',
            ELECTRO: '',
            AUTOMATIC: '',
            MECHANIC: ''
        }),
        computed: mapState(['profile', 'makers', "maximumPrice"]),
        methods: {
            getModels() {
                this.$resource('/models').get({makerName: this.mark}).then(result =>
                    result.json().then(data =>
                        data.forEach(model => this.modelNames.push(model.name))
                    )
                )
            },
            enableDisableModel() {
                this.model.text = ''
                this.modelNames = []
                if (this.mark) {
                    this.model.disabled = false
                    this.findBtnDisabled = false
                    this.getModels()
                } else {
                    this.model.disabled = true
                    this.findBtnDisabled = true
                }
            },
            adminPage() {
                this.$router.push('/admin')
            },
            carAddingPage(profile) {
                if (profile) {
                    this.$router.push('/add')
                } else {
                    window.location.replace('/login')
                }
            },
            mainPage() {
                if (this.$route !== '/') {
                    this.$router.push('/')
                }
            },
            searchForCars() {
                const transmission = []
                if (this.MECHANIC === true)
                    transmission.push('MECHANIC')
                if (this.AUTOMATIC === true)
                    transmission.push('AUTOMATIC')
                const engineType = []
                if (this.GASOLINE === true)
                    engineType.push('GASOLINE')
                if (this.DIESEL === true)
                    engineType.push('DIESEL')
                if (this.HYBRID === true)
                    engineType.push('HYBRID')
                if (this.ELECTRO === true)
                    engineType.push('ELECTRO')
                this.cars = []
                this.$resource('/cars').get(
                    {
                        manufacturer: this.mark,
                        model: this.model.text,
                        priceFrom: this.priceFrom,
                        priceTo: this.priceTo,
                        yearFrom: this.yearFrom,
                        yearTo: this.yearTo,
                        transmission: transmission,
                        engineType: engineType
                    }
                ).then(
                    result => result.json().then(
                        data => data.forEach(car => this.cars.push(car))
                    )
                )
            },
            usersCars() {
                if (this.$route.path !== '/') {
                    this.$router.push('/')
                }
                if (this.profile) {
                    this.cars = []
                    this.$resource('/cars/' + this.profile.id).get().then(result =>
                        result.json().then(
                            data => data.forEach(
                                car => this.cars.push(car)
                            )
                        )
                    )
                }
            }
        },
        props: {
            source: String,
        },
        created() {
            this.makers.forEach(maker => this.makerNames.push(maker.name))
            const year = new Date().getFullYear()
            for (let i = 1960; i <= year; i++) {
                this.years.push(i)
            }

            this.priceTo = this.maximumPrice

            this.$resource('/cars').get().then(result =>
                result.json().then(data =>
                    data.forEach(car => this.cars.push(car))
                )
            )
        },
        watch: {
            'priceFrom'(value) {
                this.$nextTick(() =>
                    this.priceFrom = value.replace(/[^0-9]/g, '')
                )
            },
            'priceTo'(value) {
                this.$nextTick(() =>
                    this.priceTo = value.replace(/[^0-9]/g, '')
                )
            }
        }
    }
</script>

<style>

</style>
