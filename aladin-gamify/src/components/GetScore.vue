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
interface IAttribute {
  name: string,
  timestamp: number,
  value: string,
  score?: number
}

interface IComponent {
  name: number,
  attribute: IAttribute[]
  score?: number
}

interface IComponents {
  attribute?: IAttribute,
  component?: IComponent
}

interface INode {
  nodeId: number,
  components: { [key: string]: IComponents }
  score?: number
}

interface INodes {
  [key: string]: INode;
}

// const example : INodes = {
//   "1": {
//     id: 1,
//     components: {
//       "fillZeros": {
//         attribute: {
//           name: "fillZeros",
//           timestamp: 123,
//           value: "true",
//           score: 0.5
//         },
//         component: {
//           name: 1,
//           attribute: [
//             {
//               name: "fillZeros",
//               timestamp: 123,
//               value: "true",
//               score: 0.5
//             }
//           ],
//           score: 0.5
//         }
//       },
//       "showSolution": {
//         attribute: {
//           name: "showSolution",
//           timestamp: 123,
//           value: "true",
//           score: 0
//         },
//         component: {
//           name: 1,
//           attribute: [
//             {
//               name: "showSolution",
//               timestamp: 123,
//               value: "true",
//               score: 0
//             }
//           ],
//           score: 0
//         }
//       },
//       "copyToClipboard": {
//         attribute: {
//           name: "copyToClipboard",
//           timestamp: 123,
//           value: "true",
//           score: 1
//         },
//         component: {
//           name: 1,
//           attribute: [
//             {
//               name: "copyToClipboard",
//               timestamp: 123,
//               value: "true",
//               score: 1
//             }
//           ],
//           score: 1
//         }
//       }
//     },
//     score: 0.5
//   }
// }

const calculateScore = (steps: ISteps[]) => {
  let result = 0;
  // Schrittweise Berechnung pro Node und pro Component separat
  // unteres ist dann jeweils die aggregation -> Datawarehouse??
  const nodes = <INodes>{};
  steps.forEach((step: ISteps) => {
    if ((/nodes__\d+__components__\d+/).test(step.path)) {
      const path = step.path.split("__");
      nodes[path[1]] = {
        nodeId: path[1],
        components: {
          [path[3]]: {},
        },
        score: 0,
      };
      // FixMe: Attribute werden aktuell noch überschrieben
      if (!(/nodes__\d+__components__\d+__component/).test(step.path)) {
        nodes[path[1]].components[path[3]].attribute = {
          name: path[4],
          timestamp: step.timestamp,
          value: step.value,
          score: 0,
        };
      } else {
        console.log("component", path);
        nodes[path[1]].components[path[3]].component = {
          name: path[4],
          attribute: [
            {
              name: path[5],
              timestamp: step.timestamp,
              value: step.value,
              score: 0,
            },
          ],
          score: 0,
        };
      }
    }
  });
  console.log(nodes);
  score.value = result;
};

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