import Vue from 'vue'
import VueRouter from 'vue-router'
import UserList from 'pages/UserList.vue'
import Login from 'pages/Login.vue'
import SignUp from 'pages/SignUp.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/users', component: UserList },
    { path: '/login', component: Login },
    { path: '/signup', component: SignUp }
]

export default new VueRouter({
    mode: 'history',
    routes
})