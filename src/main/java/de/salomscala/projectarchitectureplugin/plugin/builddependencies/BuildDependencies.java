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
package de.salomscala.projectarchitectureplugin.plugin.builddependencies;

import java.util.LinkedHashSet;
import java.util.Set;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependencies;
import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependency;

/**
 * Manages the actual build dependencies of the project.
 */
public class BuildDependencies {
    /**
     * Default constructor.
     */
    public BuildDependencies() {
    }

    private final Set<Dependency> actualBuildDependencies = new LinkedHashSet<>();

    /**
     * Adds the specified dependencies to the actual build dependencies.
     *
     * @param dependencies The dependencies to add.
     */
    public void apply(final Dependencies dependencies) {
        this.actualBuildDependencies.addAll(dependencies.getDependencies());
    }

    /**
     * Returns the collected actual build dependencies.
     *
     * @return A {@link Dependencies} object containing the current dependencies.
     */
    public Dependencies getActualBuildDependencies() {
        return new Dependencies(this.actualBuildDependencies);
    }
}
