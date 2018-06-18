package de.salomscala.projectarchitectureplugin.plugin.rules;

import java.util.Set;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependency;

public final class ForbiddenDependencyException extends RuntimeException {
    private static final long serialVersionUID = 8003030628963621353L;

    public ForbiddenDependencyException(final Set<Dependency> forbiddenDependencies) {
        super(errorMessag(forbiddenDependencies));
    }

    private static String errorMessag(final Set<Dependency> forbiddenDependencies) {
        final StringBuilder builder = new StringBuilder("The build contains the following forbidden dependencies:\r\n");
        forbiddenDependencies
                .forEach((final Dependency d) -> builder.append("   ").append(d.toString()).append("\r\n"));
        return builder.toString();
    }
}
