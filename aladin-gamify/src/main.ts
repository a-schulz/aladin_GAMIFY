import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { SkillsConfiguration } from '@skilltree/skills-client-js';

import App from './App.vue'
import router from './router'


SkillsConfiguration.configure({
  serviceUrl: 'http://localhost:8080',
  projectId: 'movies',
  authenticator: 'http://localhost:8090/api/users/user4@email.com/token',
});

const app = createApp(App)

app.use(createPinia())
app.use(router)
// app.use(SkillsDirective)

app.mount('#app')
