/*******************************************************************************
 * Copyright  2018 Marius Schultchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package de.salomscala.projectarchitectureplugin.plugin.dependencies;

import org.gradle.api.Project;

public class ProjectElement implements Element {

    public ProjectElement(final Project project) {
        super();
        this.project = project;
    }

    private final Project project;

    public Project getProject() {
        return this.project;
    }

    @Override
    public int hashCode() {
        return this.project.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof ProjectElement) {
            return this.project.equals(((ProjectElement) obj).project);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.project.getDisplayName();
    }
}
