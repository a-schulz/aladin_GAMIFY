<script lang="ts" setup>
import {ref} from "vue";
import savedPaths from "../test_data/savedPaths.json";
import recording from "../test_data/615251051.json";
import {IReplay, IState, ISteps} from "../../../../CARPET/src/interfaces/TaskGraphInterface.ts";

/**
 * Removes the numbers from the path, so that the path can be compared with the savedPaths
 * @param path
 */
const normalizePath = (path: string) => path.replaceAll(/__[0-9]/g, "");

/**
 * multiply the score for this component with these values e.g. showSolution -> score * 0 = 0
 */
const impactMethodsToScore = {
  "fillZeros": 0.5,
  "showSolution": 0,
  "copyToClipboard": 1,
};

/**
 * Converts an array of steps to an object, where the path is the key and the value is the value
 * @param steps
 */
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
        // If it's the last key, add the value along with the timestamp to the property
        if (!currentObject[key]) {
          currentObject[key] = {};
        }
        currentObject[key].value = step.value;
        currentObject[key].timestamp = step.timestamp;
      } else {
        // Otherwise, create a nested object and move to the next level
        if (!currentObject[key]) {
          currentObject[key] = {};
        }
        currentObject = currentObject[key];
      }
    }
  });
  console.log(result)
  return result;
};
// ToDO: define more metrics
const metricsCalculation = {

  // define the time for each node in s (key = node) [should be specific to difficulty from task - can be statistically calculated from user data]
  timeForNode: {
    0: undefined,
    6: 600,
    7: 1200
  },
  // define the maximum score for each node (key = node) [should be specific to difficulty from task]
  totalScore: {
    0: 5,
    6: 10,
    7: 20
  }
}

/**
 *
 * @param inputSteps
 */
const calculateScore = (inputSteps: ISteps[]) => {
  // Step 1: Parse the inputSteps into a nested object
  const parsedData = steps2object(inputSteps);

  // Step 2: Calculate the score for each node
  const nodeScores: Record<string, number> = {};
  for (const nodeKey in metricsCalculation.totalScore) {
    const nodeData = parsedData["nodes"][nodeKey];
    if (nodeData) {
      const timeForNode = metricsCalculation.timeForNode[nodeKey];
      const totalScore = metricsCalculation.totalScore[nodeKey];
      let componentScores: Record<string, number> = {};
      for (let componentKey in nodeData["components"]) {
        const componentData = nodeData["components"][componentKey];
        if (componentData) {
          // Score for each component is calculated in percent
          let componentScore = 1;
          // TODO
          // Calculate the score for each component
          // ... your scoring logic goes here ...
          componentScores[componentKey] = componentScore
        }
      }
      let nodeScore = Object.keys(componentScores).map((key) => {
        return componentScores[key];
      }).reduce((a: number, b: number) => {
        return (a + b) / 2;
      })
      // calculate the absolute score for each node
      nodeScore *= totalScore;
      // ToDo: Add timestamp to equation
      nodeScores[nodeKey] = {
        components: componentScores,
        score: nodeScore
      };
    }
  }

  // Step 3: Multiply the calculated score by the impact factor of used methods
  const finalScores: Record<string, number> = {};
  for (const nodeKey in nodeScores) {
    const nodeScore = nodeScores[nodeKey];
    let finalScore = nodeScore;

    // Apply impact factor if available for each component
    for (const component in Object.keys(parsedData["nodes"][nodeKey]["components"])) {
      // Apply impact factor if available
      const impactMethods: string[] = parsedData["nodes"][nodeKey]["components"][component]?.["contextMenu"]?.["usedMethods"];
      for (const impactMethod in impactMethods) {
        if (impactMethod && impactMethodsToScore.hasOwnProperty(impactMethod)) {
          finalScore *= impactMethodsToScore[impactMethod];
        }
      }
      finalScores[nodeKey] = finalScore;
    }
  }

  console.log(finalScores)
  return finalScores;
};

const relevantSteps: ISteps[] = <IReplay>recording.steps.filter((step: ISteps) => savedPaths.paths.includes(normalizePath(step.path)));
const score = ref(0);

</script>

<template>
  <p class="d-inline-flex gap-1">
    <button aria-controls="collapseExample" aria-expanded="false" class="btn btn-primary"
            data-bs-target="#collapseExample"
            data-bs-toggle="collapse" type="button">
      Show relevant paths
    </button>
    <button class="btn btn-primary" type="button" @click="() => {calculateScore(relevantSteps)}">
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