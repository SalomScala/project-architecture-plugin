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
package de.salomscala.projectarchitectureplugin.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import de.salomscala.projectarchitectureplugin.plugin.builddependencies.ApplyBuildDependenciesTask;
import de.salomscala.projectarchitectureplugin.plugin.dimensions.ApplyDimensionsTask;
import de.salomscala.projectarchitectureplugin.plugin.dimensions.ListDimensionsTask;
import de.salomscala.projectarchitectureplugin.plugin.rules.ApplyRulesTask;
import de.salomscala.projectarchitectureplugin.plugin.rules.CheckRulesTask;
import de.salomscala.projectarchitectureplugin.plugin.rules.ListRulesTask;

public class ProjectArchitectureDescriptionPlugin implements Plugin<Project> {
    private static final String EXTENSION_NAME = "projectArchitectureDescription";

    @Override
    public void apply(final Project project) {
        final ProjectArchitectureDescriptionExtension extension = project.getExtensions().create(EXTENSION_NAME,
                ProjectArchitectureDescriptionExtension.class, project.getObjects());

        final ListDimensionsTask listDimensionsTask = project.getTasks().create("listDimensions",
                ListDimensionsTask.class);
        final ApplyDimensionsTask applyDimensionsTask = project.getTasks().create("applyDimensions",
                ApplyDimensionsTask.class);
        listDimensionsTask.mustRunAfter(applyDimensionsTask);
        final ListRulesTask listRulesTask = project.getTasks().create("listRules", ListRulesTask.class);
        final ApplyRulesTask applyRulesTask = project.getTasks().create("applyRules", ApplyRulesTask.class);
        listRulesTask.mustRunAfter(applyRulesTask);
        applyRulesTask.dependsOn(applyDimensionsTask);
        final ApplyBuildDependenciesTask applyBuildDependenciesTask = project.getTasks()
                .create("applyBuildDependencies", ApplyBuildDependenciesTask.class);
        final CheckRulesTask checkRulesTask = project.getTasks().create("checkRules", CheckRulesTask.class);
        checkRulesTask.dependsOn(applyRulesTask);
        checkRulesTask.dependsOn(applyBuildDependenciesTask);
    }
}