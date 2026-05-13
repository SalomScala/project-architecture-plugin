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
package de.salomscala.projectarchitectureplugin.plugin.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.gradle.api.DomainObjectSet;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependencies;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependency;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.ProjectElement;

/**
 * Utility class for working with Gradle tasks and calculating dependencies.
 */
public class Task {
    private final org.gradle.api.Task task;

    /**
     * Creates a new instance of the task utility for the specified Gradle task.
     *
     * @param task The Gradle task for which calculations should be performed.
     */
    public Task(final org.gradle.api.Task task) {
        super();
        this.task = task;
    }

    /**
     * Determines all project elements affected by this task, including their dependencies.
     *
     * @return A set of {@link ProjectElement} instances.
     */
    public final Set<ProjectElement> getProjectElements() {
        final Set<ProjectElement> projectElements = new LinkedHashSet<>();
        calculateElementsFromProjectDependencies(this.task.getProject(), projectElements);
        projectElements.add(new ProjectElement(this.task.getProject()));
        return projectElements;
    }

    /**
     * Recursively calculates project elements from project dependencies.
     *
     * @param p The project whose dependencies should be analyzed.
     * @param dependenciesSoFar The set of dependencies found so far.
     */
    private static void calculateElementsFromProjectDependencies(final Project p,
            final Set<ProjectElement> dependenciesSoFar) {
        p.getConfigurations().all((final Configuration c) -> {
            final DomainObjectSet<org.gradle.api.artifacts.ProjectDependency> projectDependencies = c.getDependencies()
                    .withType(org.gradle.api.artifacts.ProjectDependency.class);
            projectDependencies.forEach((final org.gradle.api.artifacts.ProjectDependency p2) -> {
                final Project dependencyProject = p.project(p2.getPath());
                final boolean newDependency = dependenciesSoFar.add(new ProjectElement(dependencyProject));
                if (newDependency) {
                    calculateElementsFromProjectDependencies(dependencyProject, dependenciesSoFar);
                }
            });
        });
    }

    /**
     * Calculates the transitive dependencies for the project of this task.
     *
     * @return A {@link Dependencies} object containing all transitive dependencies.
     */
    public Dependencies calculateTransitiveDependencies() {
        final Set<Dependency> dependencies = new LinkedHashSet<>();
        calculateTransitiveDependencies(new ProjectElement(this.task.getProject()), new ArrayList<ProjectElement>(),
                new LinkedHashSet<>(), dependencies);
        return new Dependencies(dependencies);
    }


    /**
     * Recursive helper method for calculating transitive dependencies.
     *
     * @param actual The project element currently being considered.
     * @param done The list of already processed project elements.
     * @param fromElements The set of source elements for the dependency relationship.
     * @param dependencies The set in which the found dependencies are collected.
     */
    private static void calculateTransitiveDependencies(final ProjectElement actual, final List<ProjectElement> done,
            final Set<ProjectElement> fromElements, final Set<Dependency> dependencies) {

        if (done.contains(actual)) {
            return;
        }

        done.add(actual);

        actual.getProject().getConfigurations().all((final Configuration c) -> {
            final DomainObjectSet<org.gradle.api.artifacts.ProjectDependency> projectDependencies = c.getDependencies()
                    .withType(org.gradle.api.artifacts.ProjectDependency.class);
            projectDependencies.forEach((final org.gradle.api.artifacts.ProjectDependency p2) -> {
                for (final ProjectElement e : fromElements) {
                    dependencies.add(new Dependency(e, actual));
                }
                final Set<ProjectElement> newFromElements = new LinkedHashSet<>();
                newFromElements.add(actual);
                newFromElements.addAll(fromElements);
                calculateTransitiveDependencies(new ProjectElement(actual.getProject().project(p2.getPath())), done, newFromElements,
                        dependencies);
            });
        });
    }
}
