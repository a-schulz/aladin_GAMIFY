<script lang="ts" setup>
import {ref} from "vue";
import {savedPaths} from "../test_data/savedPaths";
import {definition} from "../test_data/definition";
import recording from "../test_data/data.json";
import {IReplay, IState, ISteps} from "../../../../CARPET/src/interfaces/TaskGraphInterface.ts";

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
const calculateScore = (inputSteps: ISteps[]) => {
  const projectId = definition[currentTask]["projectId"];
  const steps = inputSteps.sort((a, b) => a.timestamp - b.timestamp);
  for (const step of steps) {
    const skillId = definition[currentTask]["skillsMapping"][Object.keys(definition[currentTask]["skillsMapping"]).find((key) => step.path.startsWith(key))]["skillId"];
    console.log(skillId)
    //   ToDo mapping to TaskDefinition
    //   Calculation of the score
  }
  return null
};

const relevantSteps: ISteps[] = <IReplay>recording.steps.filter((step: ISteps) => savedPaths.paths.includes(normalizePath(step.path)));
const currentTask = recording.steps.filter((step: ISteps) => step.path === "currentTask")[0]["value"];
const taskParameters = recording.steps.filter((step: ISteps) => step.path === "taskParameters")[0]["value"]["parameters"];

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
            @click="() => {score = Object.values(calculateScore(relevantSteps)).map((node) => node['score']).reduce((a,b) => a+b)}">
      Calculate score
    </button>
    <h4>Score: {{ score }}</h4>
  </p>
  <div id="collapseExample" class="collapse show">
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