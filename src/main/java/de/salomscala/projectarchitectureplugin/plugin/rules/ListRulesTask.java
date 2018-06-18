package de.salomscala.projectarchitectureplugin.plugin.rules;

import javax.inject.Inject;

import org.gradle.api.DefaultTask;
import org.gradle.api.Task;

import de.salomscala.projectarchitectureplugin.plugin.ProjectArchitectureDescriptionExtension;

public class ListRulesTask extends DefaultTask {

    private final Rules rules;

    @Inject
    public ListRulesTask() {
        final ProjectArchitectureDescriptionExtension extension = this.getProject().getExtensions()
                .getByType(ProjectArchitectureDescriptionExtension.class);
        this.rules = extension.getRules();

        this.doLast((final Task t) -> {
            t.getLogger().lifecycle(ListRulesTask.this.rules.toString());
        });
    }
}
