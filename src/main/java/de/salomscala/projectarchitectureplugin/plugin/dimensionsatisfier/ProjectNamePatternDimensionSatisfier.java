package de.salomscala.projectarchitectureplugin.plugin.dimensionsatisfier;

import java.util.regex.Pattern;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.ProjectElement;

public final class ProjectNamePatternDimensionSatisfier extends ProjectDependencyDimensionSatisfier {

    private final Pattern pattern;

    public ProjectNamePatternDimensionSatisfier(final String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public boolean approves(final ProjectElement dependency) {
        return this.pattern.matcher(dependency.getProject().getName()).matches();
    }
}
