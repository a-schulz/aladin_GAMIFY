/**
 * Copyright 2020 SkillTree
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package skills.examples.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subject {
    private String id;
    private String name;
    private String iconClass;
    private List<Group> groups;
    private List<Skill> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Group> getGroups() {
        if (this.groups == null) {
            return Collections.emptyList();
        }
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Skill> getSkills() {
        if (this.skills == null) {
            return Collections.emptyList();
        }
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void createUsedMethodsSkills(){
        List<Skill> clonedSkills = new ArrayList<>();
        for (Skill skill : this.getSkills()) {
            if (skill.getCloneForUsedMethods()) {
                Skill clonedSkill = new Skill();
                try{
                clonedSkill = (Skill) skill.clone();
                }catch(CloneNotSupportedException e){
                    System.out.println("Clone not supported");
                }

                // Modify cloned skill
                clonedSkill.setName(skill.getName() + " (with methods)");
                clonedSkill.setPointIncrement((int)Math.ceil(skill.getPointIncrement() / 2.0));

                // Add cloned skill to list
                clonedSkills.add(clonedSkill);
            }
        }
            // Now add the cloned skills
            this.skills.addAll(clonedSkills);
    }
}
