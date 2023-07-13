import { createApp } from 'vue'
import App from './App.vue'
import { SkillsDirective, SkillsConfiguration } from '@skilltree/skills-client-vue';

// Vue.config.productionTip = false

const app = createApp(App)
  .use(SkillsDirective)
  .mount('#app');

SkillsConfiguration.configure({
    serviceUrl: 'http://localhost:8080',
    projectId: 'movies',
    authenticator: 'http://localhost:8090/api/users/user4@email.com/token',
});
