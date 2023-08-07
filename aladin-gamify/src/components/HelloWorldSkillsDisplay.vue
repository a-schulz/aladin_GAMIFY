<script lang="ts" setup>
import {
  SkillsConfiguration,
  SkillsDisplayJS,
  SkillsLevelJS,
  SkillsReporter,
} from "@skilltree/skills-client-js/dist/skills-client-js.esm.min.js";
import { ref } from "vue";

SkillsConfiguration.afterConfigure().then(() => {
  const clientDisplay = new SkillsDisplayJS();
  clientDisplay.attachTo(document.querySelector("#skills-client-container"));
  const skillsLevel = new SkillsLevelJS();
  skillsLevel.attachTo(document.querySelector("#skills-level-container"));
});

const skillName = ref("");

const reportSkill = () => {
  if (SkillsConfiguration.isInitialized()) {
    SkillsReporter.reportSkill(skillName.value);
  }
};
</script>

<template>
  <input v-model="skillName" type="text" />
  <button class="btn btn-primary" @click="reportSkill">Add Skill!</button>
  <div id="skills-client-container"></div>
  <div id="skills-level-container"></div>
</template>

<style scoped>

</style>