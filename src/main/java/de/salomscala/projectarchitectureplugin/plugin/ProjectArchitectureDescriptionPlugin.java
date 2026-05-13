/*******************************************************************************
 * Copyright  2026 Marius Schultchen
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
import org.gradle.api.tasks.TaskProvider;

import de.salomscala.projectarchitectureplugin.plugin.builddependencies.ApplyBuildDependenciesTask;
import de.salomscala.projectarchitectureplugin.plugin.dimensions.ApplyDimensionsTask;
import de.salomscala.projectarchitectureplugin.plugin.dimensions.ListDimensionsTask;
import de.salomscala.projectarchitectureplugin.plugin.rules.ApplyRulesTask;
import de.salomscala.projectarchitectureplugin.plugin.rules.CheckRulesTask;
import de.salomscala.projectarchitectureplugin.plugin.rules.ListRulesTask;

/**
 * The main plugin for describing and checking the project architecture.
 * Registers the necessary tasks and the extension.
 */
public class ProjectArchitectureDescriptionPlugin implements Plugin<Project> {
    /**
     * Default constructor.
     */
    public ProjectArchitectureDescriptionPlugin() {
    }
    private static final String EXTENSION_NAME = "projectArchitectureDescription";

    /**
     * Applies the plugin to the specified project.
     * Registers the extension and all relevant tasks.
     *
     * @param project The Gradle project to which the plugin is applied.
     */
    @Override
    public void apply(final Project project) {
        final ProjectArchitectureDescriptionExtension extension = project.getExtensions().create(EXTENSION_NAME,
                ProjectArchitectureDescriptionExtension.class, project.getObjects());

        final TaskProvider<ListDimensionsTask> listDimensionsTask = project.getTasks().register("listDimensions",
                ListDimensionsTask.class);
        final TaskProvider<ApplyDimensionsTask> applyDimensionsTask = project.getTasks().register("applyDimensions",
                ApplyDimensionsTask.class);
        listDimensionsTask.configure(task -> task.mustRunAfter(applyDimensionsTask));
        final TaskProvider<ListRulesTask> listRulesTask = project.getTasks().register("listRules", ListRulesTask.class);
        final TaskProvider<ApplyRulesTask> applyRulesTask = project.getTasks().register("applyRules", ApplyRulesTask.class);
        listRulesTask.configure(task -> task.mustRunAfter(applyRulesTask));
        applyRulesTask.configure(task -> task.dependsOn(applyDimensionsTask));
        final TaskProvider<ApplyBuildDependenciesTask> applyBuildDependenciesTask = project.getTasks()
                .register("applyBuildDependencies", ApplyBuildDependenciesTask.class);
        final TaskProvider<CheckRulesTask> checkRulesTask = project.getTasks().register("checkRules", CheckRulesTask.class);
        checkRulesTask.configure(task -> {
            task.dependsOn(applyRulesTask);
            task.dependsOn(applyBuildDependenciesTask);
        });
    }
}