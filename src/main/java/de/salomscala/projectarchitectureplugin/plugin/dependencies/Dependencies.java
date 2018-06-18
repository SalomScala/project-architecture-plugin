package de.salomscala.projectarchitectureplugin.plugin.dependencies;

import java.util.LinkedHashSet;
import java.util.Set;

public class Dependencies {
    private final Set<Dependency> dependencies;

    public Dependencies() {
        this(new LinkedHashSet<>());
    }

    public Dependencies(final Set<Dependency> dependencies) {
        super();
        this.dependencies = new LinkedHashSet<>(dependencies);
    }

    public Set<Dependency> getDependencies() {
        return this.dependencies;
    }
}
