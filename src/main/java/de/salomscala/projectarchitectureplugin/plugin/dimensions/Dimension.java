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
package de.salomscala.projectarchitectureplugin.plugin.dimensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Element;
import de.salomscala.projectarchitectureplugin.plugin.dimensionsatisfier.DimensionSatisfier;
import de.salomscala.projectarchitectureplugin.plugin.dimensionsatisfier.ProjectDimensionSatisfier;
import de.salomscala.projectarchitectureplugin.plugin.dimensionsatisfier.ProjectNamePatternDimensionSatisfier;

public class Dimension {

    private final String name;

    private final List<DimensionSatisfier> satisfiers = new ArrayList<>();
    private final Set<Element> dependencies = new LinkedHashSet<>();

    public Dimension(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(this.name);
        if (!this.dependencies.isEmpty()) {
            builder.append(" Dependencies:\r\n");
            builder.append("   "
                    + this.dependencies.stream().map(Element::toString).reduce((a, b) -> a + "\r\n   " + b).orElse(""));
        }
        return builder.toString();
    }

    public void projectNamePattern(final String srcPattern) {
        this.satisfiers.add(new ProjectNamePatternDimensionSatisfier(srcPattern));
    }

    public void projectCondition(final ProjectDimensionSatisfier satisfier) {
        this.satisfiers.add(satisfier);
    }

    public void apply(final Set<? extends Element> dependenciesSoFar) {
        dependenciesSoFar.stream().filter(
                (final Element d) -> this.satisfiers.stream().anyMatch((final DimensionSatisfier s) -> s.approves(d)))
                .forEach(this.dependencies::add);
    }

    public Collection<Element> getDependencies() {
        return this.dependencies;
    }
}
