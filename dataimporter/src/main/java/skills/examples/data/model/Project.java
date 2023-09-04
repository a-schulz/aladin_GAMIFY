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

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Project {
    private String id;
    private List<Subject> subjects;
    private List<Badge> badges;
    private String name;
    private Map<String, Level> levels;

    public Map<String, Level> getLevels() {
        return levels;
    }

    public void setLevels(Map<String, Level> levels) {
        this.levels = levels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Subject> getSubjects() {
        if (this.subjects == null) {
            return Collections.emptyList();
        }
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Badge> getBadges() {
        if (this.badges == null) {
            return Collections.emptyList();
        }
        return badges;
    }

    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }
}
