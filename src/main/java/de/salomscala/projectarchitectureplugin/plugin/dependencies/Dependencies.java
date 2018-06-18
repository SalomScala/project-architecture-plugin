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
package de.salomscala.projectarchitectureplugin.plugin.dependencies;

import java.util.LinkedHashSet;
import java.util.Set;

public class Dependencies {
    private final Set<Dependency> dependencies;

    public Dependencies() {
        this(new LinkedHashSet<>());
    }

    public Dependencies(final Set<Dependency> dependencies) {
        super();
        this.dependencies = new LinkedHashSet<>(dependencies);
    }

    public Set<Dependency> getDependencies() {
        return this.dependencies;
    }
}
