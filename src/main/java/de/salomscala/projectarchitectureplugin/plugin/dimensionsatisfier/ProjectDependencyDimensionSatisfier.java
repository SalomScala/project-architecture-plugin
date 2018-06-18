package de.salomscala.projectarchitectureplugin.plugin.dimensionsatisfier;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Element;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.ProjectElement;

public abstract class ProjectDependencyDimensionSatisfier implements DimensionSatisfier {

    @Override
    public boolean approves(final Element dependency) {
        if (dependency instanceof ProjectElement) {
            final ProjectElement projectDependency = (ProjectElement) dependency;
            return this.approves(projectDependency);
        }
        return false;
    }

    public abstract boolean approves(final ProjectElement dependency);
}
