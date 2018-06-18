package de.salomscala.projectarchitectureplugin.plugin.dimensions;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;

import de.salomscala.projectarchitectureplugin.plugin.ProjectArchitectureDescriptionExtension;
import de.salomscala.projectarchitectureplugin.plugin.util.Task;

public class ApplyDimensionsTask extends DefaultTask {

    private final Dimensions dimensions;

    @Inject
    public ApplyDimensionsTask() {
        final ProjectArchitectureDescriptionExtension extension = this.getProject().getExtensions()
                .getByType(ProjectArchitectureDescriptionExtension.class);
        this.dimensions = extension.getDimensions();

        this.doLast((final org.gradle.api.Task t) -> {
            this.dimensions.apply(new Task(t).getProjectElements());
        });
    }
}
