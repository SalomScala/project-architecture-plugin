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

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Dependencies;

/**
 * Interface for architecture rules.
 * A rule defines allowed and forbidden dependencies.
 */
public interface Rule {
    /**
     * Applies the rule and calculates the affected dependencies.
     */
    public abstract void apply();

    /**
     * Returns the dependencies defined as forbidden by this rule.
     *
     * @return The forbidden {@link Dependencies}.
     */
    public abstract Dependencies getForbiddenDependencies();

    /**
     * Returns the dependencies explicitly defined as allowed by this rule.
     *
     * @return The allowed {@link Dependencies}.
     */
    public abstract Dependencies getAllowedDependencies();
}
