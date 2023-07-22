<script setup lang="ts">
import {ref} from "vue";

const path = ref("")
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
  <input v-model="path">
  <button @click="getPropertyFromPath">Add 1</button>
</template>

<style scoped>

</style>