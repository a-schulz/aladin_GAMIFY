<script lang="ts" setup>
import {
  SkillsConfiguration,
  SkillsDisplayJS,
  SkillsLevelJS,
  SkillsReporter,
} from "@skilltree/skills-client-js/dist/skills-client-js.esm.min.js";
import {onMounted, ref} from "vue";
import * as bootstrap from 'bootstrap'

// Feedback for the user
const toastHeader = ref("Beim nächsten Mal!");
const toastBody = ref("");
const toastSuccess = ref(false);

onMounted(() => {
  SkillsConfiguration.afterConfigure().then(() => {
    const clientDisplay = new SkillsDisplayJS();
    clientDisplay.attachTo(document.querySelector("#skills-client-container"));
    const skillsLevel = new SkillsLevelJS();
    skillsLevel.attachTo(document.querySelector("#skills-level-container"));
  });

  // Define feedback for the user
  SkillsReporter.configure({notifyIfSkillNotApplied: true});
  SkillsReporter.addSuccessHandler((result) => {
    console.log(result);
    toastHeader.value = "Diesmal leider nicht!";
    toastSuccess.value = false;
    if (result["skillApplied"] == true) {
      toastSuccess.value = true;
      toastHeader.value = "Gut gemacht!";
      toastBody.value = "Du hast " + result["pointsEarned"] + " Punkt(e) bei " + result["name"] + " verdient!";
    } else {
      toastBody.value = result["explanation"];
    }
    toastBootstrap.show();
  });
  const toastLiveExample = document.getElementById('liveToast')
  const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
});


const skillName = ref("");
const projectId = ref("");

const changeProject = () => {
  console.log(projectId.value)
  SkillsConfiguration.configure({
    serviceUrl: import.meta.env.VITE_API_URL,
    projectId: projectId.value,
    authenticator: import.meta.env.VITE_AUTHENTICATION_URL + "/api/users/bill@email.org/" + projectId.value + "/token",
  });
  // remount the skills display
  const clientDisplay = new SkillsDisplayJS();
  clientDisplay.attachTo(document.querySelector("#skills-client-container"));
};

const reportSkill = () => {
  if (SkillsConfiguration.isInitialized()) {
    SkillsReporter.reportSkill(skillName.value);
  }
};
</script>

<template>
  <input v-model="skillName" type="text"/>
  <button class="btn btn-primary" @click="reportSkill">Add Skill!</button>
  <input v-model="projectId" type="text"/>
  <button class="btn btn-primary" @click="changeProject">Change Project!</button>
  <div id="skills-client-container"></div>
  <div id="skills-level-container"></div>
  <div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast" aria-atomic="true" aria-live="assertive" class="toast" role="alert">
      <div class="toast-header" :class="toastSuccess ?'bg-success': 'bg-danger'">
        <strong class="me-auto">{{ toastHeader }}</strong>
        <button aria-label="Close" class="btn-close" data-bs-dismiss="toast" type="button"></button>
      </div>
      <div class="toast-body">
        {{ toastBody }}
      </div>
    </div>
  </div>

</template>

<style scoped>

</style>