package de.salomscala.projectarchitectureplugin.plugin.builddependencies;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;

import de.salomscala.projectarchitectureplugin.plugin.ProjectArchitectureDescriptionExtension;
import de.salomscala.projectarchitectureplugin.plugin.util.Task;

public class ApplyBuildDependenciesTask extends DefaultTask {

    private final BuildDependencies buildDependencies;

    @Inject
    public ApplyBuildDependenciesTask() {
        final ProjectArchitectureDescriptionExtension extension = this.getProject().getExtensions()
                .getByType(ProjectArchitectureDescriptionExtension.class);
        this.buildDependencies = extension.getBuildDependencies();

        this.doLast((final org.gradle.api.Task t) -> {
            this.buildDependencies.apply(new Task(t).calculateTransitiveDependencies());
        });
    }
}
