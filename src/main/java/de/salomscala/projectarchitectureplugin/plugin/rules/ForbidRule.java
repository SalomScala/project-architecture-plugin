/*******************************************************************************
 * Copyright  2018 Marius Schultchen
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

import java.util.LinkedHashSet;
import java.util.Set;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependencies;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependency;
import de.salomscala.projectarchitectureplugin.plugin.dimensions.Dimension;

public class ForbidRule implements Rule {

    private final Dimension dim1;
    private final Dimension dim2;

    private final Set<Dependency> edges = new LinkedHashSet<>();

    public ForbidRule(final Dimension dim1, final Dimension dim2) {
        this.dim1 = dim1;
        this.dim2 = dim2;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(this.dim1.getName() + "-/->" + this.dim2.getName());
        if (!this.edges.isEmpty()) {
            builder.append(" Forbidden Dependencies:\r\n");
            builder.append("      " + this.edges.stream().map((final Dependency p) -> p.getFrom() + "-/->" + p.getTo())
                    .reduce((a, b) -> a + "\r\n      " + b).orElse(""));
        }
        return builder.toString();
    }

    @Override
    public void apply() {
        final Dimension firstDim = this.dim1;
        final Dimension secondDim = this.dim2;
        final Set<Dependency> forbiddenEdges = this.edges;
        forbid(firstDim, secondDim, forbiddenEdges);
    }

    public static void forbid(final Dimension firstDim, final Dimension secondDim,
            final Set<Dependency> forbiddenEdges) {
        firstDim.getDependencies().forEach((a) -> {
            secondDim.getDependencies().forEach((b) -> {
                forbiddenEdges.add(new Dependency(a, b));
            });
        });
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
