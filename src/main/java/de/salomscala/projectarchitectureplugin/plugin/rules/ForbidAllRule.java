package de.salomscala.projectarchitectureplugin.plugin.rules;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependencies;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependency;
import de.salomscala.projectarchitectureplugin.plugin.dimensions.Dimension;

public class ForbidAllRule implements Rule {

    private final Set<Dependency> edges = new LinkedHashSet<>();
    private final Set<Dimension> dimensions;

    public ForbidAllRule(final Collection<Dimension> dimensions) {
        this.dimensions = new LinkedHashSet<>(dimensions);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("Forbid All Rule");
        if (!this.edges.isEmpty()) {
            builder.append(" Forbidden Dependencies:\r\n");
            builder.append("      " + this.edges.stream().map((final Dependency p) -> p.getFrom() + "-/->" + p.getTo())
                    .reduce((a, b) -> a + "\r\n      " + b).orElse(""));
        }
        return builder.toString();
    }

    @Override
    public void apply() {
        forbidAllDependencies(this.dimensions, ForbidAllRule.this.edges);
    }

    private static void forbidAllDependencies(final Collection<Dimension> dimensions, final Set<Dependency> edges) {
        dimensions.stream()
                .forEach((final Dimension firstDim) -> dimensions.stream().forEach((final Dimension secondDim) -> {
                    if (!firstDim.equals(secondDim)) {
                        ForbidRule.forbid(firstDim, secondDim, edges);
                    }
                }));
    }

    @Override
    public Dependencies getForbiddenDependencies() {
        return new Dependencies(this.edges);
    }

    @Override
    public Dependencies getAllowedDependencies() {
        return new Dependencies(new LinkedHashSet<>());
    }
}
