import Vue from 'vue'
import VueRouter from 'vue-router'
import UserList from 'static/js/pages/UserList.vue'
import Admin from 'static/js/pages/Admin.vue'
import Models from 'static/js/pages/Models.vue'
import CarAdding from 'static/js/pages/CarAdding.vue'
import Cars from 'static/js/pages/Cars.vue'
import CarUpdating from 'static/js/pages/CarUpdating.vue'

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