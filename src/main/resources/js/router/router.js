import Vue from 'vue'
import VueRouter from 'vue-router'
import UserList from 'pages/UserList.vue'
import Admin from 'pages/Admin.vue'
import Models from 'pages/Models.vue'
import CarAdding from 'pages/CarAdding.vue'
import Cars from 'pages/Cars.vue'
import CarUpdating from 'pages/CarUpdating.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/admin', component: Admin },
    { path: '/admin/users', component: UserList },
    { path: '/admin/models', component: Models },
    { path: '/add', component: CarAdding },
    { path: '/update/:id', component: CarUpdating },
    { path: '/', component: Cars }
]

export default new VueRouter({
    mode: 'history',
    routes
})