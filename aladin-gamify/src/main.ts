import Vue from 'vue'
import App from './App.vue'
import { SkillsDirective, SkillsConfiguration } from '@skilltree/skills-client-vue';

Vue.config.productionTip = false

Vue.use(SkillsDirective);
SkillsConfiguration.configure({
    serviceUrl: 'http://localhost:8080',
    projectId: 'movies',
    authenticator: 'http://localhost:8090/api/users/user4@email.com/token',
});

new Vue({
    render: h => h(App),
}).$mount('#app')
