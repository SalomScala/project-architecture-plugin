package de.salomscala.projectarchitectureplugin.plugin.dimensionsatisfier;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Element;

public interface DimensionSatisfier {
    boolean approves(final Element dependency);
}
