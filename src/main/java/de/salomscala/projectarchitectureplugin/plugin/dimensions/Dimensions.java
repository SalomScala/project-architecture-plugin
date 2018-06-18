/*******************************************************************************
 * Copyright  2018 Mairus Schultchen
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

public class Dimensions {

    private final Map<String, Dimension> dimensions = new LinkedHashMap<>();

    public void dimension(final String name, final Action<Dimension> action) {
        Dimension dimension = this.dimensions.get(name);
        if (dimension == null) {
            dimension = new Dimension(name);
            this.dimensions.put(name, dimension);
        }
        action.execute(dimension);
    }

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

    public void apply(final Set<? extends Element> dependenciesSoFar) {
        this.dimensions.values().forEach((final Dimension d) -> d.apply(dependenciesSoFar));
    }

    public Collection<Dimension> getAll() {
        return this.dimensions.values();
    }
}
