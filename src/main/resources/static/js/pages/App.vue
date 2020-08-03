<template>
    <v-app>
        <v-navigation-drawer v-model="drawer" app clipped>
            <v-container>
                <v-autocomplete v-model="mark" outlined label="Марка" placeholder="Любая" clearable dense @change="enableDisableModel"></v-autocomplete>
                <v-autocomplete v-model="model.text" :disabled="model.disabled" outlined label="Модель" placeholder="Любая" clearable dense></v-autocomplete>
                <v-row no-gutters>
                    <v-col>
                        <v-text-field outlined class="rounded-r-0" label="Цена от" placeholder="0" dense></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field outlined class="rounded-l-0" label="до, руб." placeholder="max" dense></v-text-field>
                    </v-col>
                </v-row>
                <v-row no-gutters>
                    <v-col>
                        <v-text-field outlined class="rounded-r-0" label="Год от" placeholder="1960" dense></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field
                            outlined
                            class="rounded-l-0"
                            label="до"
                            :placeholder="new Date().getFullYear().toString()"
                            dense>
                        </v-text-field>
                    </v-col>
                </v-row>
                <span>Коробка передач</span>
                <v-row justify="space-around">
                    <v-checkbox label="автомат"></v-checkbox>
                    <v-checkbox label="механика"></v-checkbox>
                </v-row>
                <span>Тип двигателя</span>
                <v-row justify="space-around">
                    <v-col>
                    <v-checkbox label="бензин"></v-checkbox>
                    <v-checkbox label="дизель"></v-checkbox>
                    </v-col>
                    <v-col>
                    <v-checkbox label="гибрид"></v-checkbox>
                    <v-checkbox label="электро"></v-checkbox>
                    </v-col>
                </v-row>
            </v-container>
            <v-row justify="space-around" style="position: sticky; bottom: 0;" class="indigo">
                <v-btn outlined class="white--text" style="margin-bottom: 5px; margin-top: 5px">Найти объявления</v-btn>
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
            <span v-if="profile" class="white--text mx-2">{{ profile.name }}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
            <v-btn
                    outlined
                    v-if="profile && profile.email === 'riezenmark@gmail.com'"
                    @click="adminPage"
                    class="white--text ml-2"
            >ADMIN</v-btn>
        </v-app-bar>
        <v-main>
            <v-container fluid>
                <router-view></router-view>
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
            drawer: null,
            mark: '',
            model: {
                text: '',
                disabled: true
            }
        }),
        computed: mapState(['profile']),
        methods: {
            enableDisableModel() {
                if (this.mark) {
                    this.model.disabled = false
                } else {
                    this.model.disabled = true
                    this.model.text = ''
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
            }
        },
        props: {
            source: String,
        }
    }
</script>

<style>

</style>
