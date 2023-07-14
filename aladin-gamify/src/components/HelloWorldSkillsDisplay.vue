<script setup lang="ts">
import {
  SkillsConfiguration,
  SkillsDisplayJS,
  SkillsReporter,
} from "@skilltree/skills-client-js/dist/skills-client-js.esm.min.js";

SkillsConfiguration.afterConfigure().then(() => {
  const clientDisplay = new SkillsDisplayJS();
  clientDisplay.attachTo(document.querySelector("#skills-client-container"));
});
</script>

<template>
  <div id="skills-client-container"></div>
</template>

<style scoped>

</style>