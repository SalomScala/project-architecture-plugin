package de.salomscala.projectarchitectureplugin.plugin.rules;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;

import de.salomscala.projectarchitectureplugin.plugin.ProjectArchitectureDescriptionExtension;
import de.salomscala.projectarchitectureplugin.plugin.builddependencies.BuildDependencies;

public class CheckRulesTask extends DefaultTask {

    private final Rules rules;
    private final BuildDependencies buildDependencies;

    @Inject
    public CheckRulesTask() {
        final ProjectArchitectureDescriptionExtension extension = this.getProject().getExtensions()
                .getByType(ProjectArchitectureDescriptionExtension.class);
        this.rules = extension.getRules();
        this.buildDependencies = extension.getBuildDependencies();

        this.doLast((final org.gradle.api.Task t) -> {
            this.rules.check(this.buildDependencies.getActualBuildDependencies());
        });
    }
}
