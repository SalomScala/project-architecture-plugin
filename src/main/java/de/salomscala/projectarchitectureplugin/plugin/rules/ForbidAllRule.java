/*******************************************************************************
 * Copyright  2026 Marius Schultchen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package de.salomscala.projectarchitectureplugin.plugin.rules;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependencies;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependency;
import de.salomscala.projectarchitectureplugin.plugin.dimensions.Dimension;

/**
 * A rule that forbids any dependencies between a set of dimensions.
 */
public class ForbidAllRule implements Rule {

    private final Set<Dependency> edges = new LinkedHashSet<>();
    private final Set<Dimension> dimensions;

    /**
     * Constructor for a global prohibition rule.
     *
     * @param dimensions The collection of dimensions between which no dependencies are allowed.
     */
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

    /**
     * Applies the rule by marking all possible dependencies between the dimensions
     * as forbidden.
     */
    @Override
    public void apply() {
        forbidAllDependencies(this.dimensions, ForbidAllRule.this.edges);
    }

    /**
     * Helper method for recursively forbidding all dependencies.
     *
     * @param dimensions The dimensions to check.
     * @param edges The set in which the forbidden dependencies are collected.
     */
    private static void forbidAllDependencies(final Collection<Dimension> dimensions, final Set<Dependency> edges) {
        dimensions.stream()
                .forEach((final Dimension firstDim) -> dimensions.stream().forEach((final Dimension secondDim) -> {
                    if (!firstDim.equals(secondDim)) {
                        ForbidRule.forbid(firstDim, secondDim, edges);
                    }
                }));
    }

    /**
     * Returns the forbidden dependencies defined by this rule.
     *
     * @return The set of forbidden {@link Dependencies}.
     */
    @Override
    public Dependencies getForbiddenDependencies() {
        return new Dependencies(this.edges);
    }

    /**
     * Returns the allowed dependencies.
     * Since this is a prohibition rule, an empty set is returned.
     *
     * @return Empty {@link Dependencies}.
     */
    @Override
    public Dependencies getAllowedDependencies() {
        return new Dependencies(new LinkedHashSet<>());
    }
}
