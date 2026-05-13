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

package de.salomscala.projectarchitectureplugin.plugin.dimensions;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.gradle.api.Action;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Element;

/**
 * Container for all defined dimensions of the project.
 */
public class Dimensions {
    /**
     * Default constructor.
     */
    public Dimensions() {
    }

    private final Map<String, Dimension> dimensions = new LinkedHashMap<>();

    /**
     * Defines a new dimension or configures an existing one.
     *
     * @param name The name of the dimension.
     * @param action The configuration action for the {@link Dimension}.
     */
    public void dimension(final String name, final Action<Dimension> action) {
        Dimension dimension = this.dimensions.get(name);
        if (dimension == null) {
            dimension = new Dimension(name);
            this.dimensions.put(name, dimension);
        }
        action.execute(dimension);
    }

    /**
     * Searches for a dimension by its name.
     *
     * @param name The name of the searched dimension.
     * @return The {@link Dimension} instance or {@code null} if none exists with this name.
     */
    public Dimension getDimensionByName(final String name) {
        return this.dimensions.get(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Dimensions:\r\n");
        this.dimensions.values().forEach((final Dimension d) -> builder.append(d.toString() + "\r\n"));
        return builder.toString();
    }

    /**
     * Applies all defined dimensions to a set of elements.
     *
     * @param dependenciesSoFar The elements to check.
     */
    public void apply(final Set<? extends Element> dependenciesSoFar) {
        this.dimensions.values().forEach((final Dimension d) -> d.apply(dependenciesSoFar));
    }

    /**
     * Returns all defined dimensions.
     *
     * @return A collection of all {@link Dimension} objects.
     */
    public Collection<Dimension> getAll() {
        return this.dimensions.values();
    }
}
