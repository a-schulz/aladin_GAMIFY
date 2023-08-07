import {Project} from "./interfaces/interfaces";
import axios from 'axios';
import {serviceURL, authorizationHeaders} from "./config";

// Import the JSON data directly
import projectsJson from '../data/projects.json';

// Use the JSON data as an array of Project objects
const projects: Project[] = projectsJson as Project[];

const serviceUrl = serviceURL;

const headers = authorizationHeaders;

for (const project of projects) {
    if ((await axios.get(`${serviceUrl}/app/projects`, {headers})).data.includes(project.id)) {
        console.log(`Project [${project.name}] already exists!`);
        break;
    }

    const project_id = project.id;
    await axios.post(`${serviceUrl}/app/projects/${project_id}`, {name: project.name}, {headers});

    console.log(`\nStarting to create schema for project [${project_id}]\n`
        + `  [${project.subjects.length}] subjects\n`
        + `  [${project.badges.length}] badges`);

    const projectUrl = `${serviceUrl}/admin/projects/${project_id}`;
    // Implement addSubjects, addBadges, post, and other functions here
    // ...

    // Pin the project on the root user's admin view and enable production mode
    await axios.post(`${serviceUrl}/root/pin/${project_id}`, null, {headers});
    await axios.post(`${serviceUrl}/admin/projects/${project_id}/settings/production.mode.enabled`,
        {projectId: project_id, key: 'production.mode.enabled', value: 'true'}, {headers});
    await axios.post(`${serviceUrl}/api/myprojects/${project_id}`, null, {headers});
    // Implement achieveBadges, reportSkills, approveAndRejectSomePendingApprovals, and other functions here
    // ...

    console.log(`Project [${project_id}] was created!`);
}
