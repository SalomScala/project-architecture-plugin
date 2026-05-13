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
package de.salomscala.projectarchitectureplugin.plugin.dependencies;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents a set of dependencies.
 */
public class Dependencies {
    private final Set<Dependency> dependencies;

    /**
     * Default constructor initializing an empty set of dependencies.
     */
    public Dependencies() {
        this(new LinkedHashSet<>());
    }

    /**
     * Constructor taking a set of dependencies.
     *
     * @param dependencies The set of initial dependencies.
     */
    public Dependencies(final Set<Dependency> dependencies) {
        super();
        this.dependencies = new LinkedHashSet<>(dependencies);
    }

    /**
     * Returns the set of dependencies.
     *
     * @return A set of {@link Dependency} objects.
     */
    public Set<Dependency> getDependencies() {
        return this.dependencies;
    }
}
