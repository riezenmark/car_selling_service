import Vue from 'vue'
import VueRouter from 'vue-router'
import UserList from 'pages/UserList.vue'
import Admin from 'pages/Admin.vue'
import Makers from 'pages/Makers.vue'
import CarAdding from 'pages/CarAdding.vue'
import Cars from 'pages/Cars.vue'
import CarUpdating from 'pages/CarUpdating.vue'
import UserUpdating from 'pages/UserUpdating.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/admin', component: Admin },
    { path: '/admin/users', component: UserList },
    { path: '/admin/makers', component: Makers },
    { path: '/add', component: CarAdding },
    { path: '/update/:id', component: CarUpdating },
    { path: '/', component: Cars },
    { path: '/admin/users/:id', component: UserUpdating },
]

export default new VueRouter({
    mode: 'history',
    routes
})
