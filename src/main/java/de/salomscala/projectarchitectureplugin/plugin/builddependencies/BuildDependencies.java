package de.salomscala.projectarchitectureplugin.plugin.builddependencies;

import java.util.LinkedHashSet;
import java.util.Set;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependencies;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependency;

public class BuildDependencies {

    private final Set<Dependency> actualBuildDependencies = new LinkedHashSet<>();

    public void apply(final Dependencies dependencies) {
        this.actualBuildDependencies.addAll(dependencies.getDependencies());
    }

    public Dependencies getActualBuildDependencies() {
        return new Dependencies(this.actualBuildDependencies);
    }
}
