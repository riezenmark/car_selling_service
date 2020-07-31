import Vue from 'vue'
import VueRouter from 'vue-router'
import UserList from 'pages/UserList.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/users/list', component: UserList }
]

export default new VueRouter({
    mode: 'history',
    routes
})