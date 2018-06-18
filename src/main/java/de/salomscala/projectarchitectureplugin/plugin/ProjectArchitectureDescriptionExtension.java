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
package de.salomscala.projectarchitectureplugin.plugin;

import org.gradle.api.Action;
import org.gradle.api.model.ObjectFactory;

import de.salomscala.projectarchitectureplugin.plugin.builddependencies.BuildDependencies;
import de.salomscala.projectarchitectureplugin.plugin.dimensions.Dimensions;
import de.salomscala.projectarchitectureplugin.plugin.rules.Rules;

public class ProjectArchitectureDescriptionExtension {
    private final Dimensions dimensions;
    private final Rules rules;
    private final BuildDependencies buildDependencies;

    @javax.inject.Inject
    public ProjectArchitectureDescriptionExtension(final ObjectFactory objectFactory) {
        this.dimensions = objectFactory.newInstance(Dimensions.class);
        this.rules = objectFactory.newInstance(Rules.class, this.dimensions);
        this.buildDependencies = objectFactory.newInstance(BuildDependencies.class);
    }

    public void dimensions(final Action<Dimensions> action) {
        action.execute(this.dimensions);
    }

    public void rules(final Action<Rules> action) {
        action.execute(this.rules);
    }

    public Dimensions getDimensions() {
        return this.dimensions;
    }

    public Rules getRules() {
        return this.rules;
    }

    public BuildDependencies getBuildDependencies() {
        return this.buildDependencies;
    }
}
