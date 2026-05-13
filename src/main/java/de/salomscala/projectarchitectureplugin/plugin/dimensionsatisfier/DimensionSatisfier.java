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
package de.salomscala.projectarchitectureplugin.plugin.dimensionsatisfier;

import de.salomscala.projectarchitectureplugin.plugin.dependencies.Element;

/**
 * Interface for conditions that check whether an element belongs to a dimension.
 */
public interface DimensionSatisfier {
    /**
     * Checks whether the specified element satisfies the condition.
     *
     * @param dependency The element to check.
     * @return {@code true} if the element satisfies the condition, otherwise {@code false}.
     */
    boolean approves(final Element dependency);
}
