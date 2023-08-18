<script lang="ts" setup>
import {ref} from "vue";
import {savedPaths} from "../test_data/savedPaths";
import {definition} from "../test_data/definition";
import recording from "../test_data/data.json";
import {IReplay, IState, ISteps} from "../../../../CARPET/src/interfaces/TaskGraphInterface.ts";
import {SkillsConfiguration, SkillsReporter} from "@skilltree/skills-client-js/dist/skills-client-js.esm.min";

const reportSkill = (skillId: string) => {
  if (SkillsConfiguration.isInitialized()) {
    SkillsReporter.reportSkill(skillId);
  }
};

/**
 * Removes the numbers from the path, so that the path can be compared with the savedPaths
 * @param path
 */
const normalizePath = (path: string) => path.replaceAll(/__[0-9]/g, "");


/**
 * Recursively extract all timestamps from the given object
 * @param obj - The object to traverse
 * @returns An array of all timestamps found in the object
 */
const extractTimestamps = (obj: any): number[] => {
  const timestamps: number[] = [];

  const extractTimestampsRecursive = (data: any) => {
    if (data && typeof data === "object") {
      if (data.timestamp !== undefined) {
        timestamps.push(data.timestamp);
      } else {
        for (const key in data) {
          extractTimestampsRecursive(data[key]);
        }
      }
    }
  };
  extractTimestampsRecursive(obj);
  return timestamps;
};

/**
 *
 * @param inputSteps
 */
const calculateScore = (step: ISteps) => {
  const skillId = getSkillId(step.path);
  if (!skillId) return
  //   ToDo mapping to TaskDefinition
  //   Calculation of the score
  // Report the skill
  reportSkill(skillId);
  return
};

const getSkillId = (path: string): string | null => {
  console.log(path)
  const usefulKeys = Object.keys(skillsMapping).filter((key) => path.startsWith(key));
  if (!usefulKeys) return null
  // Order keys by length to get the most specific key -> try it first
  const orderedKeys = usefulKeys.sort((a, b) => b.length - a.length)
  for (const key of orderedKeys) {
    const skillId = skillsMapping[key]["skillId"]
    if (skillId) return skillId
  }
  return null
};

const relevantSteps: ISteps[] = <IReplay>recording.steps.filter((step: ISteps) => savedPaths.paths.includes(normalizePath(step.path))).sort((a, b) => a.timestamp - b.timestamp);
const currentTask: string = recording.steps.filter((step: ISteps) => step.path === "currentTask")[0]["value"];
const taskParameters = recording.steps.filter((step: ISteps) => step.path === "taskParameters")[0]["value"]["parameters"];
console.log(relevantSteps)
const projectId = definition[currentTask]["projectId"];
const skillsMapping = definition[currentTask]["skillsMapping"];

const score = ref(0);

</script>

<template>
  <p class="d-inline-flex gap-1">
    <button aria-controls="collapseExample" aria-expanded="false" class="btn btn-primary"
            data-bs-target="#collapseExample"
            data-bs-toggle="collapse" type="button">
      Show relevant paths
    </button>
    <button class="btn btn-primary" type="button"
            @click="() => {calculateScore(relevantSteps.shift())}">
      Nächsten Pfad berechnen und Score aktualisieren
    </button>
    <h4>Score: {{ score }}</h4>
  </p>
  <div id="collapseExample" class="collapse">
    <div class="card card-body">
      <table>
        <tr>
          <th>Path</th>
          <th>Value</th>
        </tr>
        <tr v-for="step in relevantSteps" :key="step.timestamp">
          <td>{{ step.path }}</td>
          <td>{{ step.value }}</td>
          <td>{{ step.timestamp }}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<style scoped>

</style>