package de.salomscala.projectarchitectureplugin.plugin.rules;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependencies;

public interface Rule {
    public abstract void apply();

    public abstract Dependencies getForbiddenDependencies();

    public abstract Dependencies getAllowedDependencies();
}
