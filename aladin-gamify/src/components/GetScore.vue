<script lang="ts" setup>
import { ref } from "vue";
import savedPaths from "../test_data/savedPaths.json";
import recording from "../test_data/615251051.json";
import { IReplay, IState, ISteps } from "../../../../CARPET/src/interfaces/TaskGraphInterface.ts";

const normalizePath = (path: string) => path.replaceAll(/__[0-9]/g, "");


const relevantSteps: ISteps[] = <IReplay>recording.steps.filter((step: ISteps) => savedPaths.paths.includes(normalizePath(step.path)));
const score = ref(0);

/**
 * multiply the score for this component with these values e.g. showSolution -> score * 0 = 0
 */
const impactMethodsToScore = {
  "fillZeros": 0.5,
  "showSolution": 0,
  "copyToClipboard": 1,
};

// Methode mit Paul absprechen ob es so auch generisch genug ist, damit hier dann der Score berechnet werden kann.

const calculateScore = (object: Object) => {
  let result = 0;
  score.value = result;
};

const steps2object = (steps: ISteps[]) => {
  let result: Record<string, any> = {};
  steps.forEach((step: ISteps) => {
    // Split the inputString by "__"
    const keys: string[] = step.path.split("__");
    // Reference to the current object being modified
    let currentObject: Record<string, any> = result;
    // Iterate through the keys array and construct the nested object
    for (let i = 0; i < keys.length; i++) {
      const key = keys[i];

      if (i === keys.length - 1) {
        // If it's the last key, assign the value to the property
        currentObject[key] = step.value;
      } else {
        // Otherwise, create a nested object and move to the next level
        if (!currentObject[key]) {
          currentObject[key] = {};
        }
        currentObject = currentObject[key];
      }
    }
  });

  return result;
};

console.log(steps2object(relevantSteps));

// TODO: create mapping to define which paths from the object are necessary values for the score
// Prüfen, muss ich die TimeStamps noch einfügen?
</script>

<template>
  <p class="d-inline-flex gap-1">
    <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample"
            aria-expanded="false" aria-controls="collapseExample">
      Show relevant paths
    </button>
    <button class="btn btn-primary" type="button" @click="() => calculateScore(relevantSteps)">
      Calculate score
    </button>
    <h4>Score: {{ score }}</h4>
  </p>
  <div class="collapse show" id="collapseExample">
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