var userApi = Vue.resource('/users{/id}');

Vue.component('user-form', {
    props: ['users'],
    data: function() {
        return {
            username: '',
            password: '',
            email: ''
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Write username" v-model="username"/>' +
            '<input type="text" placeholder="Write password" v-model="password"/>' +
            '<input type="text" placeholder="Write email" v-model="email"/>' +
            '<input type="button" value="Save" @click="save"/>' +
        '</div>',
    methods: {
        save: function () {
            var user = {
                username: this.username,
                password: this.password,
                email: this.email
            };

            userApi.save({}, user).then(result =>
                result.json().then(data => {
                    this.users.push(data);
                    this.username = '';
                    this.password = '';
                    this.email = ''
                })
            )
        }
    }
});

Vue.component('user-row', {
    props: ['user'],
    template: '<div><i>({{ user.id }})</i> {{ user.username }} {{ user.password }} {{ user.email }}</div>'
});

Vue.component('user-list', {
    props: ['users'],
    template:
        '<div>' +
            '<user-form :users="users"/>' +
            '<user-row v-for="user in users" :key="user.id" :user="user"/>' +
        '</div>',
    created: function () {
        userApi.get().then(result =>
            result.json().then(data =>
                data.forEach(user => this.users.push(user))
            )
        )
    }
});

var app = new Vue({
    el: '#app',
    template: '<user-list :users="users"/>',
    data: {
        users: []
    }
});
