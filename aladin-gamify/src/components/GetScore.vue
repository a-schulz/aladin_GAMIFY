<script lang="ts" setup>
import {ref} from "vue";
import savedPaths from "../test_data/savedPaths.json"
import recording from "../test_data/615251051.json"
import { uuid } from 'uuidv4';
const normalizePath = (path: string) => path.replaceAll(/__[0-9]/g, '')

const relevantSteps = recording.steps.filter((step: any) => savedPaths.paths.includes(normalizePath(step.path)))
console.log(relevantSteps)
const getPropertyFromPath = (path: any) => {
  console.log("path", path.value)
}
// const getPropertyFromPath: (state: IState) => (path: string) => {
//   const splitPath = path.split("__");
//   return splitPath.reduce((value, key) => {
//     if (value && Object.keys(value).includes(key)) return value[key];
//     else return null;
//   }, state);
// }

</script>

<template>
  <p class="d-inline-flex gap-1">
    <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
      Show relevant paths
    </button>
  </p>
  <div class="collapse" id="collapseExample">
    <div class="card card-body">
      <table>
        <tr>
          <th>Path</th>
          <th>Value</th>
        </tr>
        <tr v-for="step in relevantSteps" :key="step.timestamp">
          <td>{{step.path}}</td>
          <td>{{step.value}}</td>
        </tr>
      </table>
    </div>
  </div>
  <br/>
  <button class="btn btn-primary" type="button">
    Calculate score
  </button>
</template>

<style scoped>

</style>