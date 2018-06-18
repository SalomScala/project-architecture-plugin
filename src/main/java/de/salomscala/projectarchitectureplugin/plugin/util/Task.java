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
package de.salomscala.projectarchitectureplugin.plugin.util;

import java.util.LinkedHashSet;
import java.util.Set;

import org.gradle.api.DomainObjectSet;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependencies;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependency;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.ProjectElement;

public class Task {
    private final org.gradle.api.Task task;

    public Task(final org.gradle.api.Task task) {
        super();
        this.task = task;
    }

    public final Set<ProjectElement> getProjectElements() {
        final Set<ProjectElement> projectElements = new LinkedHashSet<>();
        calculateElementsFromProjectDependencies(this.task.getProject(), projectElements);
        projectElements.add(new ProjectElement(this.task.getProject()));
        return projectElements;
    }

    private static void calculateElementsFromProjectDependencies(final Project p,
            final Set<ProjectElement> dependenciesSoFar) {
        p.getConfigurations().all((final Configuration c) -> {
            final DomainObjectSet<org.gradle.api.artifacts.ProjectDependency> projectDependencies = c.getDependencies()
                    .withType(org.gradle.api.artifacts.ProjectDependency.class);
            projectDependencies.forEach((final org.gradle.api.artifacts.ProjectDependency p2) -> {
                final boolean newDependency = dependenciesSoFar.add(new ProjectElement(p2.getDependencyProject()));
                if (newDependency) {
                    calculateElementsFromProjectDependencies(p2.getDependencyProject(), dependenciesSoFar);
                }
            });
        });
    }

    public Dependencies calculateTransitiveDependencies() {
        final Set<Dependency> dependencies = new LinkedHashSet<>();
        calculateTransitiveDependencies(new ProjectElement(this.task.getProject()), new LinkedHashSet<>(),
                dependencies);
        return new Dependencies(dependencies);
    }

    private static void calculateDirectDependencies(final ProjectElement from, final Set<Dependency> dependencies) {
        from.getProject().getConfigurations().all((final Configuration c) -> {
            final DomainObjectSet<org.gradle.api.artifacts.ProjectDependency> projectDependencies = c.getDependencies()
                    .withType(org.gradle.api.artifacts.ProjectDependency.class);
            projectDependencies.forEach((final org.gradle.api.artifacts.ProjectDependency p2) -> {
                final ProjectElement to = new ProjectElement(p2.getDependencyProject());
                final boolean newDependency = dependencies.add(new Dependency(from, to));
                if (newDependency) {
                    calculateDirectDependencies(to, dependencies);
                }
            });
        });
    }

    private static void calculateTransitiveDependencies(final ProjectElement actual,
            final Set<ProjectElement> fromElements, final Set<Dependency> dependencies) {
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
                calculateTransitiveDependencies(new ProjectElement(p2.getDependencyProject()), newFromElements,
                        dependencies);
            });
        });
    }
}
